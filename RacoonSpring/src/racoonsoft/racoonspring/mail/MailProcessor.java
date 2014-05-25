package racoonsoft.racoonspring.mail;

import racoonsoft.racoonspring.helper.Helper;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Logger;

public class MailProcessor implements Runnable
{
    private Thread T;
    private boolean IsRunning = false;

    private Session mailSession;
    private Transport transport;

    private String smtpHost;
    private String smtpLogin;
    private String smtpPassword;
    private Boolean enableTls;

    public MailProcessor(String smtpHost,String smtpLogin,String smtpPassword, boolean enableTls)
    {
        this.smtpHost = smtpHost;
        this.smtpLogin = smtpLogin;
        this.smtpPassword = smtpPassword;
        this.enableTls = enableTls;
    }
    public void run()
    {
        while(IsRunning)
        {
            try
            {
                MailMessage mess = MessageStorage.getMail();
                if(mess!=null)
                {
                    boolean isSent = sendMail(mess);
                    if(!isSent)
                    {
                        if(mess.canRetry())
                        {
                            Logger.getAnonymousLogger().warning("Mail message moved to low priority queue.");
                            MessageStorage.addMailLowPriority(mess);
                        }
                    }
                    else
                    {
                        Logger.getAnonymousLogger().info("Mail message sent successfully.");
                    }
                }
                else
                {
                    Thread.sleep(1000);
                }
            }
            catch(Exception ex)
            {
                Logger.getAnonymousLogger().severe(Helper.getStackTraceString(ex));
            }
        }
    }
    public void start() throws InterruptedException
    {
        stop();
        IsRunning = true;
        T = new Thread(this);
        T.start();
    }
    public void stop() throws InterruptedException
    {
        IsRunning = false;
        if(T!=null)
        {
            T.join();
        }
    }

    public boolean send(MailMessage message) throws Exception
    {
        if(Helper.isUnix())
        {
            System.out.println("Linux");
            return sendMailLinux(message);
        }
        System.out.println("Not linux");
        return sendMail(message);
    }
    public boolean sendMailLinux(MailMessage message) throws Exception
    {
        try
        {
            String to = message.to()[0];
            for(int i=1; i<message.to().length; i++)
            {
                to+=","+message.to()[i];
            }
            String command = "sudo php -r \"mail('"+to+"','"+message.subject()+"', '"+message.getText()+"', 'FROM: "+message.from()+"');";
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s = "";
            while ((s = stdInput.readLine()) != null) {
                Logger.getAnonymousLogger().info(s);
            }
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
            return false;
        }
    }
    public boolean sendMail(MailMessage message)
    {
        try
        {
            if(message.isAuthorized())
            {
                smtpHost = message.smtpHost();
                smtpLogin = message.login();
                smtpPassword = message.password();
		        enableTls = message.enableTls();
            }

            
            Properties props = new Properties();
            props.setProperty("mail.smtp.starttls.enable", String.valueOf(enableTls));
            props.setProperty("mail.from", message.from());
            props.setProperty("mail.password", smtpPassword);
            props.setProperty("mail.smtp.auth","true");
            props.setProperty("mail.mime.charset","utf-8");

            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.host", smtpHost);
            mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);
            transport = mailSession.getTransport();
            MimeMessage mess = new MimeMessage(mailSession);

            mess.setSubject(message.subject());
            String text = message.getText();
            if(text == null)
            {
                transport.close();
                return false;
            }
            Multipart multipart = new MimeMultipart("alternative");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message.getText(),"text/html; charset=utf-8");
            multipart.addBodyPart(messageBodyPart);// Add message
            for(int i=0; i<message.getAttachmentsFullNames().size(); i++)
            {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(message.getAttachmentsFullNames().get(i));
                messageBodyPart.setDataHandler(new DataHandler(source));

                messageBodyPart.setFileName(message.getAttachmentsNames().get(i));
                multipart.addBodyPart(messageBodyPart);
            }

            mess.setContent(multipart, "text/html; charset=utf-8");
            for(int i=0; i<message.to().length; i++)
            {
                mess.addRecipient(Message.RecipientType.TO,
                new InternetAddress(message.to()[i]));
            }
            mess.setFrom(new InternetAddress(message.from(),message.from()));
            transport.connect(smtpLogin, smtpPassword);
            transport.sendMessage(mess,
            mess.getRecipients(Message.RecipientType.TO));
            transport.close();
            Logger.getAnonymousLogger().fine("Mail message successfully sent. "+message.toString());
            return true;
        }
        catch(Exception ex)
        {
            Logger.getAnonymousLogger().warning("Mail message wasn`t sent. "+Helper.getStackTraceString(ex));
            return false;
        }
    }
}

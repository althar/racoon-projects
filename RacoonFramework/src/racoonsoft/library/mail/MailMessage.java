package racoonsoft.library.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MailMessage
{
    private HashMap<String,Object> Parameters = new HashMap<String, Object>();
    private String From;
    private String[] To;
    private String Subject;
    private String TemplateBody;
    private int RetryCount = 0;
    private String SmtpHost;
    private String Login;
    private String Password;
    private boolean IsAuthorized;
    private boolean EnableTls;
    private ArrayList<String> AttachmentFileFullNames = new ArrayList<String>();
    private ArrayList<String> AttachmentFileNames = new ArrayList<String>();
    //private HashMap<String,String> AttachmentFileNames = new HashMap<String,String>();

    public MailMessage(String from,String[] to,String subject,String templateBody,HashMap<String,Object> params,String smtpHost,String login,String password,boolean enableTLS)
    {
        EnableTls = enableTLS;
        From = from;
        To = to;
        Subject = subject;
        TemplateBody = templateBody;
        Parameters = params;
        SmtpHost = smtpHost;
        Login = login;
        Password = password;
        IsAuthorized = true;
        if(SmtpHost == null||Login == null||Password == null)
        {
            IsAuthorized = false;
        }
    }
    public MailMessage(String from,String to,String subject,String templateBody,HashMap<String,Object> params,String smtpHost,String login,String password,boolean enableTLS)
    {
	    EnableTls = enableTLS;
        From = from;
        To = new String[1];
	    To[0] = to;
        Subject = subject;
        TemplateBody = templateBody;
        Parameters = params;
        SmtpHost = smtpHost;
        Login = login;
        Password = password;
        IsAuthorized = true;
        if(SmtpHost == null||Login == null||Password == null)
        {
            IsAuthorized = false;
        }
    }
    public MailMessage(String from,String to,String subject,String templateBody,HashMap<String,Object> params)
    {
        From = from;
        To = new String[1];
	    To[0] = to;
        Subject = subject;
        TemplateBody = templateBody;
        Parameters = params;
        IsAuthorized = false;
    }
    public MailMessage(String from,String[] to,String subject,String templateBody,HashMap<String,Object> params)
    {
        From = from;
        To = to;
        Subject = subject;
        TemplateBody = templateBody;
        Parameters = params;
        IsAuthorized = false;
    }
    public void addParameter(String name,String value)
    {
        Parameters.put(name, value);
    }
    public String smtpHost()
    {
        return SmtpHost;
    }
    public boolean enableTls()
    {
        if(IsAuthorized)
        {
            return EnableTls;
        }
        else
        {
            return false;
        }
    }
    public String login()
    {
        return Login;
    }
    public String password()
    {
        return Password;
    }
    public boolean isAuthorized()
    {
        return IsAuthorized;
    }
    public String from()
    {
        return From;
    }
    public String[] to()
    {
        return To;
    }
    public String subject()
    {
        return Subject;
    }
    public HashMap<String,Object> parameters()
    {
        return Parameters;
    }
    public boolean canRetry()
    {
        RetryCount++;
        return RetryCount<3;
    }
    public String getText()
    {
        String finalText = new String();
        finalText = TemplateBody;

        Iterator<String> keys = Parameters.keySet().iterator();
        while(keys.hasNext())
        {
            String key = keys.next();
            String value = (String)Parameters.get(key);
            finalText = finalText.replace("<"+key+">", value);
        }
        return finalText;
    }
    public void addAttachment(String filePath,String attachmentName)
    {
        AttachmentFileFullNames.add(filePath+"\\"+attachmentName);
        AttachmentFileNames.add(attachmentName);
    }
    public ArrayList<String> getAttachmentsFullNames()
    {
        return AttachmentFileFullNames;
    }
    public ArrayList<String> getAttachmentsNames()
    {
        return AttachmentFileNames;
    }
}

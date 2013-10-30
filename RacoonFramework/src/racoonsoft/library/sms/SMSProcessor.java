package racoonsoft.library.sms;

import racoonsoft.library.http.HTTPClient;
import racoonsoft.library.settings.Settings;
import java.io.IOException;
import java.net.URLEncoder;


public class SMSProcessor
{
    public String Login,Password;
    public String BaseURL = "http://smsc.ru/sys/send.php?login=<login>&psw=<password>&phones=<phones>&mes=<message>&sender=<sender>";
    public SMSProcessor(String login,String password)
    {
        Login = login;
        Password = password;
        //BaseURL+="&charset=<charset>";
    }
    public SMSProcessor()
    {
        if(Settings.getStringSetting("sms_login")!=null
            &&Settings.getStringSetting("sms_password")!=null)
        {
            Login = Settings.getStringSetting("sms_login");
            Password = Settings.getStringSetting("sms_password");
        }
    }

    public String sendSMS(SMSMessage mess) throws IOException
    {
        if(Login==null||Password==null)
        {
            Login = Settings.getStringSetting("sms_login");
            Password = Settings.getStringSetting("sms_password");
        }
        try
        {
            String url_string = BaseURL.replace("<login>", Login);
            url_string = url_string.replace("<password>", Password);
            url_string = url_string.replace("<phones>", mess.getPhone());
            url_string = url_string.replace("<message>", URLEncoder.encode(mess.getText(),"windows-1251"));
            url_string = url_string.replace("<sender>", mess.getSender());
            url_string = url_string.replace("<charset>", "utf-8");
            System.out.println(url_string);
            String result = HTTPClient.sendHTTPRequest("", url_string);
            return result;
        }
        catch(Exception ex)
        {
            return ex.toString();
        }
    }
}

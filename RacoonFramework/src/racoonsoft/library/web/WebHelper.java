package racoonsoft.library.web;

import racoonsoft.library.settings.Settings;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WebHelper
{
    public static void setCookie(HttpServletResponse response,String name,String value,Integer duration)
    {
        String cookie = name+"="+value+"; path=/; Expires="+getCookieExpires(duration);
        response.setHeader("Set-Cookie", cookie);
    }
    public static String getCookieExpires(Integer duration)
    {
        if(Settings.getIntegerSetting("session_lifetime")!=null)
        {
            // 100 days
            duration = 8640000;
        }
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.add(Calendar.SECOND, duration);
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMMM yyyy HH:mm:ss",Locale.ENGLISH);
        String exp = format.format(c.getTime())+" GMT";
        System.out.println(exp);
        return exp;
    }
    public static String getCookie(HttpServletRequest request,String name)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies==null)
        {
            return null;
        }
        String sid = null;
        for(int i=0; i<cookies.length; i++)
        {
            if(cookies[i].getName().equalsIgnoreCase(name))
            {
                sid = cookies[i].getValue();
                break;
            }
        }
        if(sid==null)
        {
            return null;
        }
        return sid;
    }
}

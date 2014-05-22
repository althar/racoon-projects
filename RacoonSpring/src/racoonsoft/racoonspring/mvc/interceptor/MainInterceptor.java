package racoonsoft.racoonspring.mvc.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.racoonspring.access.AccessProcessor;
import racoonsoft.racoonspring.data.database.DatabaseProcessor;
import racoonsoft.racoonspring.data.structure.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;

@Controller
public abstract class MainInterceptor implements HandlerInterceptor
{
    @Autowired
    public DatabaseProcessor dbProc;

    @Autowired
    public AccessProcessor accessProc;

    @Override
    public abstract boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception;

    @Override
    public abstract void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception;

    @Override
    public abstract void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception;

    public MainInterceptor(DatabaseProcessor dbProc, AccessProcessor accessProc)
    {
        this.dbProc = dbProc;
        this.accessProc = accessProc;
    }
    public void setDbProc(DatabaseProcessor dbProc)
    {
        this.dbProc = dbProc;
    }

    public Long id(HttpServletRequest request)
    {
        return (Long)request.getAttribute("user_id");
    }
    public User user(HttpServletRequest request)
    {
        return (User)request.getAttribute("user");
    }
    public ArrayList<String> roles(HttpServletRequest request)
    {
        return (ArrayList<String>)request.getAttribute("roles");
    }
    public Boolean hasRole(HttpServletRequest request,String role)
    {
        return ((ArrayList<String>)request.getAttribute("roles")).contains(role);
    }
    private static String getCookieExpires(Integer duration)
    {
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.add(Calendar.SECOND, duration);
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMMM yyyy HH:mm:ss",Locale.ENGLISH);
        String exp = format.format(c.getTime())+" GMT";
        //System.out.println(exp);
        return exp;
    }
    public static void setCookie(HttpServletResponse response,String name,String value,Integer duration)
    {
        String cookie = name+"="+value+"; path=/; Expires="+getCookieExpires(duration);
        //response.setHeader("Set-Cookie", cookie);
        response.addHeader("Set-Cookie",cookie);
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
                return sid;
            }
        }
        return sid;
    }
    public static String getHeader(String name,HttpServletRequest request)
    {
        Enumeration<String> names = request.getHeaderNames();
        while(names.hasMoreElements())
        {
            String n = names.nextElement();
            String nameToCompareHeader = n.replace("_","").replace("-","").toLowerCase();
            String nameToCompareRequest = name.replace("_","").replace("-","").toLowerCase();
            if(nameToCompareHeader.equalsIgnoreCase(nameToCompareRequest))
            {
                return request.getHeader(n);
            }
        }
        return null;
    }
    public static String getParameter(String name,HttpServletRequest request)
    {
        Enumeration<String> names = request.getParameterNames();
        while(names.hasMoreElements())
        {
            String n = names.nextElement();
            String nameToCompareHeader = n.replace("_", "").replace("-","").toLowerCase();
            String nameToCompareRequest = name.replace("_","").replace("-","").toLowerCase();
            if(nameToCompareHeader.equalsIgnoreCase(nameToCompareRequest))
            {
                return request.getParameter(n);
            }
        }
        return null;
    }
}

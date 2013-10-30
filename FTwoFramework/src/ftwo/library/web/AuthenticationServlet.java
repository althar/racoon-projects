package ftwo.library.web;

import ftwo.library.access.Session;
import ftwo.library.access.User;
import ftwo.library.database.DBRecord;
import ftwo.library.exceptions.SessionCreationException;
import ftwo.library.json.JSONProcessor;
import ftwo.library.settings.Settings;
import ftwo.library.sms.SMSMessage;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public abstract class AuthenticationServlet extends HttpServlet
{
    private BaseServlet Base;

    public static Integer REGISTRATION_NO_LOGIN = -91;
    public static Integer REGISTRATION_NO_PASSWORD = -92;

    public static Integer AUTHENTICATION_NO_LOGIN = -1;
    public static Integer AUTHENTICATION_NO_PASSWORD = -2;
    public static Integer AUTHENTICATION_WRONG_LOGIN_OR_PASSWORD = -3;
    public static Integer AUTHORIZATION_FAILED = -4;
    public static Integer LOGOUT_FAILED = -5;
    public static Integer RECOVER_NO_SUCH_USER = -6;

    public static Integer AUTHENTICATION_SUCCESSFUL = 1;
    public static Integer AUTHORIZATION_SUCCESSFUL = 2;
    public static Integer LOGOUT_SUCCESSFUL = 3;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        try
        {
            if(cmd == null)
            {
                out.print(BaseServlet.generateResponseXML(BaseServlet.BASE_COMMAND_UNDEFINED, cmd, null));
            }
            else if(cmd.equalsIgnoreCase("create_registration"))
            {
                String login = request.getParameter("login");
                String pass = request.getParameter("password");
                if(login!=null)
                {
                    HashMap<String,Object> params = new HashMap<String, Object>();
                    params.put("login",login);
                    params.put("pass",pass);
                    BaseServlet.dbProc().registerUser(params);
                }
                else
                {
                    out.print(BaseServlet.generateResponseXML(REGISTRATION_NO_LOGIN, cmd));
                }
            }
            else if(cmd.equalsIgnoreCase("authentication"))
            {
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                String admin = request.getParameter("admin");
                if(login == null)
                {
                    out.print(BaseServlet.generateResponseXML(AUTHENTICATION_NO_LOGIN, cmd, null));
                    out.close();
                    return;
                }
                else if(password == null)
                {
                    out.print(BaseServlet.generateResponseXML(AUTHENTICATION_NO_PASSWORD, cmd, null));
                    out.close();
                    return;
                }
                else
                {
                    String session_id = authentication(login, password);
                    if(session_id==null)
                    {
                        out.print(BaseServlet.generateResponseXML(AUTHENTICATION_WRONG_LOGIN_OR_PASSWORD, cmd, "session_id",session_id));
                        out.close();
                        return;
                    }
                    else
                    {
                        if(admin!=null)
                        {
                            int role_id = BaseServlet.sessProc().getSession(session_id).getUser().getIntValue("role_id");
                            if(role_id==0)
                            {
                                String expires = getSessionExpires();
                                String cookie = "admin_session_id="+session_id+"; path=/; Expires="+expires;
                                response.setHeader("Set-Cookie", cookie);
                                out.print(BaseServlet.generateResponseXML(AUTHENTICATION_SUCCESSFUL, cmd, "session_id",session_id));
                                out.close();
                            }
                            else
                            {
                                out.print(BaseServlet.generateResponseXML(AUTHENTICATION_WRONG_LOGIN_OR_PASSWORD, cmd, "session_id",session_id));
                                out.close();
                                return;
                            }
                        }
                        else
                        {
                            String expires = getSessionExpires();
                            String cookie = "session_id="+session_id+"; path=/; Expires="+expires;
                            response.setHeader("Set-Cookie", cookie);
                            out.print(BaseServlet.generateResponseXML(AUTHENTICATION_SUCCESSFUL, cmd, "session_id",session_id));
                            out.close();
                        }
                        return;
                    }
                }
            }
            else if(cmd.equalsIgnoreCase("authorization"))
            {
                User user = null;
                if(request.getParameter("admin")!=null)
                {
                    user = adminAuthorization(request);
                }
                else
                {
                    user = authorization(request);
                }

                if(user!=null)
                {
                    out.print(BaseServlet.generateResponseXML(AUTHORIZATION_SUCCESSFUL, cmd,"login",user.getStringValue("login")));
                    out.close();
                    return;
                }
                else
                {
                    out.print(BaseServlet.generateResponseXML(AUTHORIZATION_FAILED, cmd, null));
                    out.close();
                    return;
                }
            }
            else if(cmd.equalsIgnoreCase("recover"))
            {
                String phone = request.getParameter("phone");
                DBRecord rec = BaseServlet.dbProc().getRecord("SELECT password FROM users WHERE login='"+phone.replace("'", "")+"'");
                if(rec!=null)
                {
                    String pass = rec.getStringValue("password");
                    BaseServlet.smsProc().sendSMS(new SMSMessage(phone, "Здравствуйте! Ваш пароль на 88005551073.ru "+pass, "BonusKarta"));
                    out.print(BaseServlet.generateResponseXML(BaseServlet.REQUEST_PROCESSED_SUCCESSFULLY, cmd));
                    out.close();
                    return;
                }
                else
                {
                    out.print(BaseServlet.generateResponseXML(RECOVER_NO_SUCH_USER, cmd, null));
                    out.close();
                    return;
                }
            }
            else if(cmd.equalsIgnoreCase("logout"))
            {
                User user = authorization(request);

                if(user!=null)
                {
                    Session s = getSession(request);
                    Boolean was_session = false;
                    if(s!=null)
                    {
                         was_session = BaseServlet.SessionProc.removeSession(s.getKey());
                    }
                    out.print(BaseServlet.generateResponseXML(LOGOUT_SUCCESSFUL, cmd,"was_session",was_session.toString()));
                    out.close();
                    return;
                }
                else
                {
                    out.print(BaseServlet.generateResponseXML(LOGOUT_FAILED, cmd, null));
                    out.close();
                    return;
                }
            }
        }
        catch (Exception ex)
        {
            out.print(BaseServlet.generateResponseExceptionXML(BaseServlet.BASE_UNKNOWN_COMMAND, cmd, ex));
        }
        finally
        {
            out.close();
        }
    }
    public static String authentication(String login,String password) throws SessionCreationException,NumberFormatException,SQLException
    {
        User u = BaseServlet.dbProc().authenticateUser(login,password);
        if(u!=null)
        {
            Long session_lifetime = 864000l;
            if(Settings.getIntegerSetting("session_lifetime")!=null)
            {
                session_lifetime = Settings.getIntegerSetting("session_lifetime").longValue();
            }
            String key = BaseServlet.sessProc().createSession(u, session_lifetime).getKey();
            return key;
        }
        return null;
    }
    public static String authenticationAnonymous() throws SessionCreationException,NumberFormatException,SQLException
    {

        Long session_lifetime = 864000l;
        if(Settings.getIntegerSetting("session_lifetime")!=null)
        {
            session_lifetime = Settings.getIntegerSetting("session_lifetime").longValue();
        }

        String key = BaseServlet.sessProc().createAnonimousSession(session_lifetime).getKey();
        return key;
    }
    public static String getCookie(String name,HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies==null)
        {
            return null;
        }
        String cookie_val = null;
        for(int i=0; i<cookies.length; i++)
        {
            if(cookies[i].getName().equalsIgnoreCase(name))
            {
                cookie_val = cookies[i].getValue();
                break;
            }
        }
        return cookie_val;
    }
    public static User authorization(HttpServletRequest request)
    {
        Session s = getSession(request);
        if(s!=null)
        {
            return (User)s.getUser();
        }
        return null;
    }
    public static User authorization(String session_id)
    {
        Session session = BaseServlet.sessProc().getSession(session_id);
        System.out.println("session: "+session);
        if(session==null)
        {
            return null;
        }
        return session.getUser();
    }
    public static User adminAuthorization(HttpServletRequest request)
    {
        Session s = getAdminSession(request);
        if(s!=null)
        {
            return (User)s.getUser();
        }
        return null;
    }
    public static Session getSession(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies==null)
        {
            return null;
        }
        String sid = null;
        for(int i=0; i<cookies.length; i++)
        {
            if(cookies[i].getName().equalsIgnoreCase("session_id"))
            {
                sid = cookies[i].getValue();
                System.out.println("sid: "+sid);
                break;
            }
        }
        if(sid==null)
        {
            return null;
        }
        Session session = BaseServlet.sessProc().getSession(sid);
        System.out.println("session: "+session);
        if(session==null)
        {
            return null;
        }
        return session;
    }

    public static Session getAdminSession(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies==null)
        {
            return null;
        }
        String sid = null;
        for(int i=0; i<cookies.length; i++)
        {
            if(cookies[i].getName().equalsIgnoreCase("admin_session_id"))
            {
                sid = cookies[i].getValue();
                break;
            }
        }
        if(sid==null)
        {
            return null;
        }
        Session session = BaseServlet.sessProc().getSession(sid);
        if(session==null)
        {
            return null;
        }
        return session;
    }
    public static String getSessionExpires()
    {
        Integer duration = 864000;
        if(Settings.getIntegerSetting("session_lifetime")!=null)
        {
            duration = Settings.getIntegerSetting("session_lifetime");
        }
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.add(Calendar.MILLISECOND, duration);
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMMM yyyy HH:mm:ss",Locale.ENGLISH);
        String exp = format.format(c.getTime())+" GMT";
        System.out.println(exp);
        return exp;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws java.io.IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws java.io.IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

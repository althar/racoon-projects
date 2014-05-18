package racoonsoft.racoonspring.access;

import racoonsoft.racoonspring.data.database.DatabaseProcessor;
import racoonsoft.racoonspring.data.structure.ActionResult;
import racoonsoft.racoonspring.data.structure.ActionResultCode;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;
import racoonsoft.racoonspring.data.structure.User;
import racoonsoft.racoonspring.helper.Helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserProcessor
{
    private static HashMap<String,UserSession> sessions = new HashMap<String, UserSession>();
    private static String loginColumnName = "login";
    private static String passwordColumnName = "password";
    private static String userTableName = "\"user\"";
    private static Long sessionPeriod = 8640000l;


    //<editor-fold desc="Init">
    public static String getLoginColumnName() {
        return loginColumnName;
    }
    public static void setLoginColumnName(String loginColumnName) {
        UserProcessor.loginColumnName = loginColumnName;
    }
    public static String getPasswordColumnName() {
        return passwordColumnName;
    }
    public static void setPasswordColumnName(String passwordColumnName) {
        UserProcessor.passwordColumnName = passwordColumnName;
    }
    public static String getUserTableName() {
        return userTableName;
    }
    public static void setUserTableName(String userTableName) {
        UserProcessor.userTableName = userTableName;
    }
    //</editor-fold>

    //<editor-fold desc="Main">
    public static ActionResult logout(HttpServletRequest request)
    {
        String sessionId = getCookie(request,"session_id");
        sessions.remove(sessionId);
        ActionResult res = new ActionResult(ActionResultCode.ACTION_FAILED.ACTION_SUCCESSFUL);
        return res;
    }
    public static ActionResult registration(DatabaseProcessor dbProc,HttpServletRequest request, HttpServletResponse response, HashMap<String,Object> parameters,String[] roles) throws SQLException
    {
        try
        {
            String login  = request.getParameter("login");
            String password  = request.getParameter("password");
            logout(request);
            // Check existence
            if(login==null)
            {
                return new ActionResult(ActionResultCode.REGISTRATION_FAILED_NO_LOGIN);
            }
            DatabaseStructure existent = dbProc.selectQuery("SELECT id FROM "+userTableName+" WHERE "+loginColumnName+"='"+login.replace("'","")+"'").selectOne();
            if(existent!=null)
            {
                return new ActionResult(ActionResultCode.REGISTRATION_FAILED_ALREADY_EXISTS);
            }
            // Insert user
            parameters.put(loginColumnName,login);
            parameters.put(passwordColumnName,password);
            Long id = dbProc.executeInsert(userTableName,parameters);
            DatabaseStructure sessionRec = dbProc.selectQuery("SELECT md5(" + loginColumnName + "||" + passwordColumnName + "||random())||md5(" + loginColumnName + "||" + passwordColumnName + "||random()) AS session_id FROM " + userTableName + " WHERE id=" + id).selectOne();
            String sessionId = sessionRec.getStringValue("session_id");

            // Insert user roles
            for(String role:roles)
            {
                if(role==null)
                {
                    return new ActionResult(ActionResultCode.REGISTRATION_FAILED_LACK_OF_DATA);
                }
                dbProc.executeNonQuery("INSERT INTO user_role (user_id,role) VALUES ("+id+",'"+role.replace("'","")+"')");
            }

            // Add cookie
            setCookie(response,"session_id",sessionId,8640000);

            // Add session
            UserSession session = new UserSession(8640000,id,sessionId);
            cleanSessions();
            sessions.put(sessionId,session);

            // Get registered user
            User u = getUser(id,dbProc);

            // Return result
            ActionResult res = new ActionResult(ActionResultCode.REGISTRATION_SUCCESSFUL);
            res.setData("sessionId",sessionId);
            res.setUser(u);
            return res;
        }
        catch(Exception ex)
        {
            ActionResult res = new ActionResult(ActionResultCode.REGISTRATION_FAILED_UNKNOWN);
            res.addEvent("exception");
            res.setData("exception", Helper.getStackTraceString(ex));
            return res;
        }
    }
    public static ActionResult authorization(HttpServletRequest request,HttpServletResponse response,DatabaseProcessor dbProc) throws SQLException
    {
        try
        {
            ActionResult result = new ActionResult(ActionResultCode.AUTHORIZATION_SUCCESSFUL);
            String sessionId = getCookie(request,"session_id");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if(sessionId==null&&request.getParameter("session_id")!=null)
            {
                sessionId = request.getParameter("session_id");
            }

            // Auth by login & password
            if(login!=null&&password!=null)
            {
                logout(request);
                User u = getUser(login,password,dbProc);
                UserSession session = null;
                if(u==null)
                {
                    User anonymous = new User();
                    anonymous.set("id",-1l);
                    DatabaseStructure sessionRec = dbProc.selectQuery("SELECT md5(CAST(random() AS VARCHAR))||md5(CAST(random() AS VARCHAR)) AS session_id").selectOne();
                    sessionId = sessionRec.getStringValue("session_id");
                    setCookie(response,"session_id",sessionId,8640000);
                    setCookie(response,"u_id","-1",8640000);
                    // Add session
                    session = new UserSession(8640000,anonymous.getLongValue("id"),sessionId);
                    session.setAnonymous(anonymous);
                    cleanSessions();
                    sessions.put(sessionId,session);
                    result.setResult(ActionResultCode.AUTHORIZATION_FAILED_WRONG_LOGIN_PASSWORD);
                    result.setUser(anonymous);
                    result.setData("session_id",sessionId);
                    return result;
                }
                else
                {
                    DatabaseStructure sessionRec = dbProc.selectQuery("SELECT md5(" + loginColumnName + "||" + passwordColumnName + "||random())||md5(" + loginColumnName + "||" + passwordColumnName + "||random()) AS session_id FROM " + userTableName + " WHERE id=" + u.getLongValue("id")).selectOne();
                    sessionId = sessionRec.getStringValue("session_id");
                    UserSession s = new UserSession(8640000,u.getLongValue("id"),sessionId);
                    sessions.put(sessionId,s);
                    setCookie(response,"session_id",sessionId,8640000);
                    setCookie(response,"u_id",u.getLongValue("id").toString(),8640000);
                    result.setUser(u);
                    result.setData("session_id",sessionId);
                }
                return result;
            }
            else
            {
                if(sessionId==null)
                {
                    sessionId="";
                }
                UserSession session = sessions.get(sessionId);
                if(session==null)
                {
                    User anonymous = new User();
                    anonymous.set("id",-1l);
                    DatabaseStructure sessionRec = dbProc.selectQuery("SELECT md5(CAST(random() AS VARCHAR))||md5(CAST(random() AS VARCHAR)) AS session_id").selectOne();
                    sessionId = sessionRec.getStringValue("session_id");
                    setCookie(response,"session_id",sessionId,8640000);
                    setCookie(response,"u_id","-1",8640000);
                    // Add session
                    session = new UserSession(8640000,anonymous.getLongValue("id"),sessionId);
                    session.setAnonymous(anonymous);
                    cleanSessions();
                    sessions.put(sessionId,session);
                    result.setUser(anonymous);
                    result.setData("session_id",sessionId);
                }
                else
                {
                    User u = getUser(session.getUserId(),dbProc);
                    if(u!=null)
                    {
                        setCookie(response,"u_id",u.getLongValue("id").toString(),8640000);
                    }
                    if(session.isAnonymous()||u==null)
                    {
                        u = new User();
                        u.set("id",session.getUserId());
                    }
                    result.setData("session_id",sessionId);
                    result.setUser(u);
                }
                return result;
            }
        }
        catch(Exception ex)
        {
            ActionResult res = new ActionResult(ActionResultCode.AUTHORIZATION_FAILED_UNKNOWN);
            res.addEvent("exception");
            res.setData("exception",Helper.getStackTraceString(ex));
            return res;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Helpers">
    private static User getUser(Long id, DatabaseProcessor dbProc) throws Exception
    {
        DatabaseStructure rec = dbProc.selectQuery("SELECT * FROM "+userTableName+" WHERE id=" + id).selectOne();
        if(rec == null)
        {
            return null;
        }
        ArrayList<DatabaseStructure> roleRecs = dbProc.selectQuery("SELECT role FROM user_role WHERE user_id="+id).select();
        User u = new User(rec);
        ArrayList<String> roles = new ArrayList<String>();
        for(DatabaseStructure role:roleRecs)
        {
            roles.add(role.getStringValue("role"));
        }
        u.setRoles(roles);
        return u;
    }
    private static User getUser(String login,String password, DatabaseProcessor dbProc) throws Exception
    {
        DatabaseStructure rec = dbProc.selectQuery("SELECT * FROM "+userTableName+" WHERE "+loginColumnName+"='" + login.replace("'","") + "' AND "+passwordColumnName+"='" + password.replace("'","") + "'").selectOne();
        if(rec == null)
        {
            return null;
        }
        Long id = rec.getLongValue("id");
        ArrayList<DatabaseStructure> roleRecs = dbProc.selectQuery("SELECT role FROM user_role WHERE user_id="+id).select();
        User u = new User(rec);
        ArrayList<String> roles = new ArrayList<String>();
        for(DatabaseStructure role:roleRecs)
        {
            roles.add(role.getStringValue("role"));
        }
        u.setRoles(roles);
        return u;
    }
    private static void setCookie(HttpServletResponse response,String name,String value,Integer duration)
    {
        String cookie = name+"="+value+"; path=/; Expires="+getCookieExpires(duration);
        //response.setHeader("Set-Cookie", cookie);
        response.addHeader("Set-Cookie",cookie);
    }
    private static String getCookieExpires(Integer duration)
    {
        duration = sessionPeriod.intValue();
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.add(Calendar.SECOND, duration);
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMMM yyyy HH:mm:ss",Locale.ENGLISH);
        String exp = format.format(c.getTime())+" GMT";
        //System.out.println(exp);
        return exp;
    }
    public static String getSessionId(HttpServletRequest request)
    {
        return getCookie(request,"session_id");
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
    private static void cleanSessions()
    {
        Long now = new Date().getTime();
        Iterator<String> keyIter = sessions.keySet().iterator();
        while(keyIter.hasNext())
        {
            String key = keyIter.next();
            UserSession sess = sessions.get(key);
            if(sess.expired(now))
            {
                sessions.remove(key);
            }
        }
    }
    //</editor-fold>
}
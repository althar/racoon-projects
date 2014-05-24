package racoonsoft.racoonspring.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
public class AccessProcessor
{
    @Autowired
    private DatabaseProcessor dbProc;

    private HashMap<String, AccessSession> sessions = new HashMap<String, AccessSession>();
    private String loginColumnName = "login";
    private String passwordColumnName = "password";
    private String userTableName = "\"user\"";
    private String confirmationLinkColumnName = "confirmation_link";
    private String confirmedColumnName = "\"confirmed\"";
    private Long sessionPeriod = 8640000l;

    //<editor-fold desc="Init">

    //</editor-fold>

    //<editor-fold desc="Main">
    public ActionResult logout(HttpServletRequest request)
    {
        String sessionId = getCookie(request, "session_id");
        sessions.remove(sessionId);
        ActionResult res = new ActionResult(ActionResultCode.ACTION_FAILED.ACTION_SUCCESSFUL);
        System.out.println("LOGOUT");
        return res;
    }
    public ActionResult registration(HttpServletRequest request, HttpServletResponse response, DatabaseStructure parameters, String[] roles,Boolean needConfirmation) throws SQLException
    {
        try
        {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            logout(request);
            // Check existence
            if(login == null)
            {
                return new ActionResult(ActionResultCode.REGISTRATION_FAILED_NO_LOGIN);
            }
            DatabaseStructure existent = dbProc.selectQuery("SELECT id FROM " + userTableName + " WHERE " + loginColumnName + "='" + login.replace("'", "") + "'").selectOne();
            if(existent != null)
            {
                return new ActionResult(ActionResultCode.REGISTRATION_FAILED_ALREADY_EXISTS);
            }
            // Insert user
            parameters.set(loginColumnName, login);
            parameters.set(passwordColumnName, password);
            Long id = dbProc.executeInsert(userTableName, parameters);
//            DatabaseStructure sessionRec = dbProc.selectQuery("SELECT md5(" + loginColumnName + "||" + passwordColumnName + "||random())||md5(" + loginColumnName + "||" + passwordColumnName + "||random()) AS session_id FROM " + userTableName + " WHERE id=" + id).selectOne();
//            String sessionId = sessionRec.getStringValue("session_id");

            // Insert user roles
            for(String role : roles)
            {
                if(role == null)
                {
                    return new ActionResult(ActionResultCode.REGISTRATION_FAILED_LACK_OF_DATA);
                }
                dbProc.executeNonQuery("INSERT INTO user_role (user_id,role) VALUES (" + id + ",'" + role.replace("'", "") + "')");
            }

            // Authorization
            authorization(request,response,needConfirmation);

            // Add cookie
            //setCookie(response, "session_id", sessionId, 8640000);

            // Add session
            //AccessSession session = new AccessSession(8640000, id, sessionId);
            cleanSessions();
            //sessions.put(sessionId, session);

            // Get registered user
            User u = getUser(id);

            // Return result
            ActionResult res = new ActionResult(ActionResultCode.REGISTRATION_SUCCESSFUL);
            String link = dbProc.selectQuery("SELECT "+confirmationLinkColumnName+" FROM "+userTableName +" WHERE id="+id).selectOne().getStringValue("confirmation_link");
            res.setData("confirmation_link",link);
            //res.setData("sessionId", sessionId);
            //res.setUser(u);
            return res;
        }
        catch (Exception ex)
        {
            ActionResult res = new ActionResult(ActionResultCode.REGISTRATION_FAILED_UNKNOWN);
            res.addEvent("exception");
            res.setData("exception", Helper.getStackTraceString(ex));
            return res;
        }
    }
    public ActionResult confirm(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            String link = request.getParameter(confirmationLinkColumnName);
            if(link == null || link.equalsIgnoreCase(""))
            {
                return new ActionResult(ActionResultCode.CONFIRMATION_FAILED_WRONG_CONFIRMATION_LINK);
            }
            HashMap<String, Object> pars = new HashMap<String, Object>();
            pars.put(confirmedColumnName, true);
            Long count = dbProc.executeUpdate(userTableName, pars, confirmationLinkColumnName + "='" + link.replace("'", "`") + "'");

            if(count > 0)
            {
                ActionResult result = new ActionResult(ActionResultCode.CONFIRMATION_SUCCESSFUL);
                DatabaseStructure user = dbProc.selectQuery("SELECT * FROM " + userTableName + " WHERE " + confirmationLinkColumnName + "='" + link.replace("'", "`") + "'").selectOne();
                result = auth(user.getStringValue("login"), user.getStringValue("password"), request, response, result);
                return result;
            }
            else
            {
                return new ActionResult(ActionResultCode.CONFIRMATION_FAILED_WRONG_CONFIRMATION_LINK);
            }
        }
        catch (Exception ex)
        {
            return new ActionResult(ActionResultCode.CONFIRMATION_FAILED_UNKNOWN);
        }
    }
    private ActionResult auth(String login, String password, HttpServletRequest request, HttpServletResponse response, ActionResult result) throws Exception
    {
        logout(request);
        User uWithoutConfirmation = getUser(login, password, false);
        User u = getUser(login, password, false);
        AccessSession session = null;
        if(u == null)
        {
            User anonymous = new User();
            anonymous.set("id", -1l);
            DatabaseStructure sessionRec = dbProc.selectQuery("SELECT md5(CAST(random() AS VARCHAR))||md5(CAST(random() AS VARCHAR)) AS session_id").selectOne();
            String sessionId = sessionRec.getStringValue("session_id");
            setCookie(response, "session_id", sessionId, 8640000);
            setCookie(response, "u_id", "-1", 8640000);
            // Add session
            session = new AccessSession(8640000, anonymous.getLongValue("id"), sessionId);
            session.setAnonymous(anonymous);
            cleanSessions();
            sessions.put(sessionId, session);
            result.setResult(ActionResultCode.AUTHORIZATION_FAILED_WRONG_LOGIN_PASSWORD);
            if(uWithoutConfirmation != null)
            {
                result.setResult(ActionResultCode.AUTHORIZATION_FAILED_NEED_CONFIRMATION);
            }
            result.setUser(anonymous);
            result.setData("session_id", sessionId);

            request.setAttribute("anonymous", true);
            request.setAttribute("user_id", u.getLongValue("id"));
            request.setAttribute("user", u);
            request.setAttribute("roles", null);

            return result;
        }
        else
        {
            DatabaseStructure sessionRec = dbProc.selectQuery("SELECT md5(" + loginColumnName + "||" + passwordColumnName + "||random())||md5(" + loginColumnName + "||" + passwordColumnName + "||random()) AS session_id FROM " + userTableName + " WHERE id=" + u.getLongValue("id")).selectOne();
            String sessionId = sessionRec.getStringValue("session_id");
            AccessSession s = new AccessSession(8640000, u.getLongValue("id"), sessionId);
            sessions.put(sessionId, s);
            setCookie(response, "session_id", sessionId, 8640000);
            setCookie(response, "u_id", u.getLongValue("id").toString(), 8640000);
            result.setUser(u);
            result.setData("session_id", sessionId);

            request.setAttribute("anonymous", false);
            request.setAttribute("user_id", u.getLongValue("id"));
            request.setAttribute("user", u);
            request.setAttribute("roles", u.getRoles());
        }
        return result;

    }
    public ActionResult authorization(HttpServletRequest request, HttpServletResponse response,String login, String password,Boolean needConfirmation) throws Exception
    {
        try
        {
            ActionResult result = new ActionResult(ActionResultCode.AUTHORIZATION_SUCCESSFUL);
            if(login == null)
            {
                login = request.getParameter("login");
            }
            if(password == null)
            {
                password = request.getParameter("password");
            }

            // Auth by login & password
            if(login != null && password != null)
            {
                logout(request);
                User uWithoutConfirmation = getUser(login, password, false);
                User u = getUser(login, password, needConfirmation);
                AccessSession session = null;
                if(u == null)
                {
                    if(uWithoutConfirmation!=null)
                    {
                        result.setResult(ActionResultCode.AUTHORIZATION_FAILED_NEED_CONFIRMATION);
                    }
                    else
                    {
                        result.setResult(ActionResultCode.AUTHORIZATION_FAILED_NO_SUCH_USER);
                    }

                    return result;
                }
                else
                {
                    DatabaseStructure sessionRec = dbProc.selectQuery("SELECT md5(" + loginColumnName + "||" + passwordColumnName + "||random())||md5(" + loginColumnName + "||" + passwordColumnName + "||random()) AS session_id FROM " + userTableName + " WHERE id=" + u.getLongValue("id")).selectOne();
                    String sessionId = sessionRec.getStringValue("session_id");
                    AccessSession s = new AccessSession(8640000, u.getLongValue("id"), sessionId);
                    sessions.put(sessionId, s);
                    setCookie(response, "session_id", sessionId, 8640000);
                    setCookie(response, "u_id", u.getLongValue("id").toString(), 8640000);
                    result.setUser(u);
                    result.setData("session_id", sessionId);

                    request.setAttribute("anonymous", false);
                    request.setAttribute("user_id", u.getLongValue("id"));
                    request.setAttribute("user", u);
                    request.setAttribute("roles", u.getRoles());
                }
                return result;
            }
            else
            {
                result.setResult(ActionResultCode.REGISTRATION_FAILED_NO_LOGIN);
                return result;
            }
        }
        catch (Exception ex)
        {
            ActionResult res = new ActionResult(ActionResultCode.AUTHORIZATION_FAILED_UNKNOWN);
            res.addEvent("exception");
            res.setData("exception", Helper.getStackTraceString(ex));
            return res;
        }
    }
    public ActionResult authorization(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return authorization(request, response, false);
    }
    public ActionResult authorization(HttpServletRequest request, HttpServletResponse response, Boolean needConfirmation) throws Exception
    {
        return authorization(request,response,null,null,needConfirmation);
    }
    public void checkAuthorization(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String sessionId = getCookie(request, "session_id");
        if(sessionId == null && request.getParameter("session_id") != null)
        {
            sessionId = request.getParameter("session_id");
        }
        if(sessionId == null)
        {
            sessionId = "";
        }
        AccessSession session = sessions.get(sessionId);
        if(session == null)
        {
            User anonymous = new User();
            anonymous.set("id", -1l);
            DatabaseStructure sessionRec = dbProc.selectQuery("SELECT md5(CAST(random() AS VARCHAR))||md5(CAST(random() AS VARCHAR)) AS session_id").selectOne();
            sessionId = sessionRec.getStringValue("session_id");
            setCookie(response, "session_id", sessionId, 8640000);
            setCookie(response, "u_id", "-1", 8640000);
            // Add session
            session = new AccessSession(8640000, anonymous.getLongValue("id"), sessionId);
            session.setAnonymous(anonymous);
            cleanSessions();
            sessions.put(sessionId, session);

            request.setAttribute("anonymous", true);
            request.setAttribute("user_id", anonymous.getLongValue("id"));
            request.setAttribute("user", anonymous);
            request.setAttribute("roles", anonymous.getRoles());
        }
        else
        {
            User u = getUser(session.getUserId());
            if(u != null)
            {
                setCookie(response, "u_id", u.getLongValue("id").toString(), 8640000);
            }
            if(session.isAnonymous() || u == null)
            {
                u = new User();
                u.set("id", session.getUserId());
            }
            request.setAttribute("anonymous", u.isAnonymous());
            request.setAttribute("user_id", u.getLongValue("id"));
            request.setAttribute("user", u);
            request.setAttribute("roles", u.getRoles());
        }
    }
    //</editor-fold>

    //<editor-fold desc="Helpers">
    private User getUser(Long id) throws Exception
    {
        DatabaseStructure rec = dbProc.selectQuery("SELECT * FROM " + userTableName + " WHERE id=" + id).selectOne();
        if(rec == null)
        {
            return null;
        }
        ArrayList<DatabaseStructure> roleRecs = dbProc.selectQuery("SELECT role FROM user_role WHERE user_id=" + id).select();
        User u = new User(rec);
        ArrayList<String> roles = new ArrayList<String>();
        for(DatabaseStructure role : roleRecs)
        {
            roles.add(role.getStringValue("role"));
        }
        u.setRoles(roles);
        return u;
    }
    private User getUser(String login, String password, Boolean needConfirmation) throws Exception
    {
        String confCondition = " AND " + confirmedColumnName + " = TRUE";
        String queryString = "SELECT * FROM " + userTableName + " WHERE " + loginColumnName + "='" + login.replace("'", "") + "' AND " + passwordColumnName + "='" + password.replace("'", "") + "'";
        if(needConfirmation)
        {
            queryString += confCondition;
        }
        DatabaseStructure rec = dbProc.selectQuery(queryString).selectOne();
        if(rec == null)
        {
            return null;
        }
        Long id = rec.getLongValue("id");
        ArrayList<DatabaseStructure> roleRecs = dbProc.selectQuery("SELECT role FROM user_role WHERE user_id=" + id).select();
        User u = new User(rec);
        ArrayList<String> roles = new ArrayList<String>();
        for(DatabaseStructure role : roleRecs)
        {
            roles.add(role.getStringValue("role"));
        }
        u.setRoles(roles);
        return u;
    }
    private void setCookie(HttpServletResponse response, String name, String value, Integer duration)
    {
        String cookie = name + "=" + value + "; path=/; Expires=" + getCookieExpires(duration);
        //response.setHeader("Set-Cookie", cookie);
        response.addHeader("Set-Cookie", cookie);
    }
    private String getCookieExpires(Integer duration)
    {
        duration = sessionPeriod.intValue();
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.add(Calendar.SECOND, duration);
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMMM yyyy HH:mm:ss", Locale.ENGLISH);
        String exp = format.format(c.getTime()) + " GMT";
        //System.out.println(exp);
        return exp;
    }
    public String getSessionId(HttpServletRequest request)
    {
        return getCookie(request, "session_id");
    }
    public String getCookie(HttpServletRequest request, String name)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies == null)
        {
            return null;
        }
        String sid = null;
        for(int i = 0; i < cookies.length; i++)
        {
            if(cookies[i].getName().equalsIgnoreCase(name))
            {
                sid = cookies[i].getValue();
                break;
            }
        }
        if(sid == null)
        {
            return null;
        }
        return sid;
    }
    private void cleanSessions()
    {
        Long now = new Date().getTime();
        Iterator<String> keyIter = sessions.keySet().iterator();
        while(keyIter.hasNext())
        {
            String key = keyIter.next();
            AccessSession sess = sessions.get(key);
            if(sess.expired(now))
            {
                sessions.remove(key);
            }
        }
    }
    //</editor-fold>
}

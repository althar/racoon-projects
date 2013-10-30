package ftwo.library.access;

import ftwo.library.database.DBProcessor;
import ftwo.library.exceptions.SessionCreationException;
import ftwo.library.exceptions.UserInitializationException;
import ftwo.library.web.BaseServlet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import sun.misc.BASE64Encoder;

public class SessionProcessor
{
    private static Long Seed1;
    private static int CurrentAnonimousID = -1;
    private HashMap<String,Session> Sessions;
    private HashMap<Integer,User> Users;
    public SessionProcessor() throws SQLException
    {
        Sessions = new HashMap<String, Session>();
	Users = new HashMap<Integer, User>();
    }
    public Session createSession(User user,long lifetime) throws SessionCreationException,SQLException
    {
	String key = getSessionKey();
        Session s = new Session(key, user,lifetime);
        Sessions.put(key, s);
	Users.put(user.getID(), user);
	BaseServlet.DBProc.sequenceSetCurrentValue("session_id_seq", Seed1);
        return s;
    }
    public Session createAnonimousSession(long lifetime) throws SessionCreationException,SQLException
    {
	CurrentAnonimousID--;
	String key = getSessionKey();
	User user = new User(new HashMap<String, Object>());
	user.setValue("id", CurrentAnonimousID);
        Session s = new Session(key, user,lifetime);
        Sessions.put(key, s);
	Users.put(user.getID(), user);
	BaseServlet.DBProc.sequenceSetCurrentValue("session_id_seq", Seed1);
        return s;
    }
    public static String getSessionKey() throws SQLException
    {
	Seed1 = BaseServlet.DBProc.sequenceGetNextValue("session_id_seq");
        Random r = new Random();
        Seed1+= r.nextInt(1000);
        String key = new BASE64Encoder().encode(Seed1.toString().getBytes());
	Seed1+= r.nextInt(1000);
        key += new BASE64Encoder().encode(Seed1.toString().getBytes());
	Seed1+= r.nextInt(1000);
        key += new BASE64Encoder().encode(Seed1.toString().getBytes());
	Seed1+= r.nextInt(1000);
        key += new BASE64Encoder().encode(Seed1.toString().getBytes());
	Seed1+= r.nextInt(1000);
        key = key.replace("=", "_");
	return key;
    }
    public User getUser(int id)
    {
	return Users.get(id);
    }
    public Session getSession(String key)
    {
        Session s = Sessions.get(key);
        if(s!=null)
        {
            if(s.isDead())
            {
                Sessions.remove(key);
                return null;
            }
            return s;
        }
        return null;
    }
    public boolean updateSession(String key,String command)
    {
        Session s = getSession(key);
        if(s!=null)
        {
            s.ping(command);
            return true;
        }
        return false;
    }
    public boolean removeSession(String key)
    {
        Session s = getSession(key);

        if(s!=null)
        {
	    Users.remove(s.getUser().getID());
            Sessions.remove(key);
            return true;
        }
        return false;
    }
    public void reloadUsers(DBProcessor proc) throws SQLException,UserInitializationException
    {
        Iterator<Session> sessIter = Sessions.values().iterator();
        while(sessIter.hasNext())
        {
            Session s = sessIter.next();
            s.getUser().loadUserFromDB(proc);
        }
	Iterator<User> userIter = Users.values().iterator();
        while(userIter.hasNext())
        {
            User u = userIter.next();
            u.loadUserFromDB(proc);
        }
    }
    public boolean containsSession(String key)
    {
	return getSession(key)!=null;
    }
    public int size()
    {
	return Sessions.size();
    }
    @Override
    public String toString()
    {
	return Sessions.toString();
    }
}

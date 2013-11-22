package racoonsoft.library.access;

import java.util.ArrayList;
import java.util.HashMap;

public class ActionResult
{
    public static int ACTION_SUCCESSFUL = 9000;

    public static int REGISTRATION_SUCCESSFUL = 1000;
    public static int REGISTRATION_FAILED_ALREADY_EXISTS = -1001;
    public static int REGISTRATION_FAILED_LACK_OF_DATA = -1002;
    public static int REGISTRATION_FAILED_UNKNOWN = -1003;

    public static int AUTHORIZATION_SUCCESSFUL = 2000;
    public static int AUTHORIZATION_FAILED_NO_AUTHORIZATION_DATA = -2001;
    public static int AUTHORIZATION_FAILED_WRONG_SESSION_ID = -2002;
    public static int AUTHORIZATION_FAILED_WRONG_LOGIN_PASSWORD = -2003;
    public static int AUTHORIZATION_FAILED_NO_SUCH_USER = -2004;
    public static int AUTHORIZATION_FAILED_UNKNOWN = -2005;


    private int result;
    private ArrayList<String> events = new ArrayList<String>();
    private HashMap<String,Object> data = new HashMap<String, Object>();

    public ActionResult(int result)
    {
        this.result = result;
    }
    public ActionResult(int result,String event)
    {
        this.result = result;
        this.events.add(event);
    }
    public ActionResult(int result,String event,String dataName,Object data)
    {
        this.result = result;
        this.events.add(event);
        this.data.put(dataName,data);
    }

    public User getUser()
    {
        try
        {
            return (User)data.get("user");
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public void setUser(User user)
    {
        setData("user",user);
    }
    public int getResult()
    {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<String> events) {
        this.events = events;
    }
    public void addEvent(String event)
    {
        this.events.add(event);
    }

    public HashMap<String, Object> getData() {
        return data;
    }
    public Object getData(String name)
    {
        return data.get(name);
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
    public void setData(String dataName,Object data)
    {
        this.data.put(dataName,data);
    }

    public boolean success()
    {
        return result>0;
    }
}

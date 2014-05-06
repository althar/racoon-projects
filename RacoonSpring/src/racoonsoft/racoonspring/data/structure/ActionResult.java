package racoonsoft.racoonspring.data.structure;

import java.util.ArrayList;
import java.util.HashMap;

public class ActionResult
{
//    public static int ACTION_SUCCESSFUL = 9000;
//
//    public static int REGISTRATION_SUCCESSFUL = 1000;
//    public static int REGISTRATION_FAILED_ALREADY_EXISTS = -1001;
//    public static int REGISTRATION_FAILED_LACK_OF_DATA = -1002;
//    public static int REGISTRATION_FAILED_UNKNOWN = -1003;
//
//    public static int AUTHORIZATION_SUCCESSFUL = 2000;
//    public static int AUTHORIZATION_FAILED_NO_AUTHORIZATION_DATA = -2001;
//    public static int AUTHORIZATION_FAILED_WRONG_SESSION_ID = -2002;
//    public static int AUTHORIZATION_FAILED_WRONG_LOGIN_PASSWORD = -2003;
//    public static int AUTHORIZATION_FAILED_NO_SUCH_USER = -2004;
//    public static int AUTHORIZATION_FAILED_UNKNOWN = -2005;


    private ActionResultCode result;
    private ArrayList<String> events = new ArrayList<String>();
    private HashMap<String,Object> data = new HashMap<String, Object>();

    public ActionResult(ActionResultCode result) {
        this.result = result;
    }
    public ActionResult(ActionResultCode result,String event) {
        this.result = result;
        this.events.add(event);
    }
    public ActionResult(ActionResultCode result,String event,String dataName,Object data) {
        this.result = result;
        this.events.add(event);
        this.data.put(dataName,data);
    }

    public boolean hasRole(String role) {
        return getUser().hasRole(role);
    }
    public ArrayList<String> getRoles() {
        if(getUser()==null)
        {
            return null;
        }
        return getUser().getRoles();
    }
    public User getUser() {
        try
        {
            return (User)data.get("user");
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public void setUser(User user) {
        setData("user",user);
    }
    public ActionResultCode getResult() {
        return result;
    }
    public boolean anonymous() {
        if(getUser()==null)
        {
            return true;
        }
        return getUser().isAnonymous();
    }
    public void setResult(ActionResultCode result) {
        this.result = result;
    }
    public ArrayList<String> getEvents() {
        return events;
    }
    public void setEvents(ArrayList<String> events) {
        this.events = events;
    }
    public void addEvent(String event) {
        this.events.add(event);
    }
    public HashMap<String, Object> getData() {
        return data;
    }
    public Object getData(String name) {
        return data.get(name);
    }
    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
    public void setData(String dataName,Object data) {
        this.data.put(dataName,data);
    }
    public boolean success() {
        return (result==ActionResultCode.ACTION_SUCCESSFUL||result==ActionResultCode.AUTHORIZATION_SUCCESSFUL||result==ActionResultCode.REGISTRATION_SUCCESSFUL);
    }
}

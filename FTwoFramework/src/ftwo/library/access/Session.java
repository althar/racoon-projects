package ftwo.library.access;

import ftwo.library.exceptions.SessionCreationException;
import java.util.Calendar;

public class Session
{
    private String Key;
    private User CurrentUser;
    private Long LastUpdate;
    private Long Created;
    private String LastCommand;
    private Long Lifetime;

    public Session(String key,User user,long lifetime) throws SessionCreationException
    {
        if(user==null)
        {
            throw new SessionCreationException("User must be NOT null.");
        }
        if(key==null)
        {
            throw new SessionCreationException("Session key must be NOT null.");
        }
        CurrentUser = user;
        Key = key;
        Created = Calendar.getInstance().getTimeInMillis();
        LastUpdate = Calendar.getInstance().getTimeInMillis();
        Lifetime = lifetime;
    }
    public void ping(String last_command)
    {
        LastCommand = last_command;
        LastUpdate = Calendar.getInstance().getTimeInMillis();
    }
    public void ping()
    {
        ping("");
    }
    public String getKey()
    {
        return Key;
    }
    public User getUser()
    {
        return CurrentUser;
    }
    public Long getCreated()
    {
        return Created;
    }
    public Long getLastUpdate()
    {
        return LastUpdate;
    }
    public Long getTimeWithoutUpdate()
    {
        return Calendar.getInstance().getTimeInMillis()-LastUpdate;
    }
    public String getLastCommane()
    {
        return LastCommand;
    }
    public long getLifetime()
    {
        return Lifetime;
    }
    public boolean isAnonymous()
    {
	    return getUser().isAnonymous();
    }
    public boolean isDead()
    {
        return getTimeWithoutUpdate()>Lifetime;
    }
}

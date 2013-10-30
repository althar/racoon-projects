package racoonsoft.library.access;

import racoonsoft.library.database.DBRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class User extends DBRecord
{
    private ArrayList<String> roles = new ArrayList<String>();

    public User(HashMap<String,Object> fields)
    {
        super(fields);
    }
    public User(DBRecord record)
    {
        this(record.getFields());
    }
    public User()
    {
        super();
    }
    public boolean isAnonymous()
    {
	    return getID()<0;
    }

    public boolean hasRole(String role)
    {
        return roles.contains(role);
    }
    public ArrayList<String> getRoles()
    {
        return roles;
    }

    public void setRoles(ArrayList<String> roles)
    {
        this.roles = roles;
    }

    @Override
    public String toString()
    {
        return super.getString();
    }
}

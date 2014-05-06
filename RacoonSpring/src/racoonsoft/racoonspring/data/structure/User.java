package racoonsoft.racoonspring.data.structure;

import java.util.ArrayList;
import java.util.HashMap;

public class User extends DatabaseStructure
{
    private ArrayList<String> roles = new ArrayList<String>();

    public User()
    {
        super();
    }
    public User(DatabaseStructure val)
    {
        fields = val.fields;
    }
    public boolean isAnonymous()
    {
	    return getLongValue("id")<0;
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
        return super.toString();
    }
}

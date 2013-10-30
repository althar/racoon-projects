package ftwo.library.access;

import ftwo.library.database.DBProcessor;
import ftwo.library.database.DBRecord;
import ftwo.library.exceptions.UserInitializationException;
import java.sql.SQLException;
import java.util.HashMap;

public class User extends DBRecord
{
    public User(HashMap<String,Object> fields)
    {
        super(fields);
    }
    public User(DBRecord record)
    {
        this(record.getFields());
    }
    public boolean isAnonymous()
    {
	return getID()<0;
    }
    public void loadUserFromDB(DBProcessor proc) throws SQLException,UserInitializationException
    {
        User u = new User(proc.getRecord("users", "id="+String.valueOf(this.getID()), null));
        if(u!=null)
        {
            this.Fields = u.Fields;
        }
    }
    public void saveUserToDB(DBProcessor proc) throws SQLException
    {
        proc.executeUpdate("users", Fields, "id="+this.getID());
    }
    @Override
    public String toString()
    {
        return super.getString();
    }
}

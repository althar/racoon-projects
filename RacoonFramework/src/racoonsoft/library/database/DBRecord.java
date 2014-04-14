package racoonsoft.library.database;

import racoonsoft.library.exceptions.UserInitializationException;
import racoonsoft.library.xml.XMLProcessor;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class DBRecord extends Object
{
    //private int ID;
    public HashMap<String,Object> fields;

    public DBRecord()
    {
        fields = new HashMap<String, Object>();
    }
    public DBRecord(DBRecord rec)
    {
	    this(rec.fields);
    }
    public DBRecord(HashMap<String,Object> fields)
    {
        Object id = fields.get("id");
        this.fields = fields;
    }
    public DBRecord(Integer id,HashMap<String,Object> fields)
    {
        this.fields = fields;
	    setValue("id", id);
    }
    public HashMap<String,Object> getFields()
    {
        return fields;
    }
    public Object getValue(String name)
    {
        return fields.get(name);
    }
    public Integer getIntValue(String name)
    {
        try
        {
            return (Integer)getValue(name);
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public Long getLongValue(String name)
    {
        try
        {
            if(getValue(name) instanceof BigInteger)
            {
                return ((BigInteger)getValue(name)).longValue();
            }
            return (Long)getValue(name);
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public String getStringValue(String name)
    {
        try
        {
            return (String)getValue(name);
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public Double getDoubleValue(String name)
    {
        try
        {
            return (Double)getValue(name);
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public Date getDateValue(String name)
    {
        try
        {
            return (Date)getValue(name);
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public Boolean getBooleanValue(String name)
    {
        try
        {
            return Boolean.valueOf(getValue(name).toString());
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public ArrayList<DBRecord> getRecords(String name)
    {
        try
        {
            return (ArrayList<DBRecord>)getValue(name);
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public DBRecord getRecord(String name)
    {
        try
        {
            return (DBRecord)getValue(name);
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public String[] getNames()
    {
        return fields.keySet().toArray(new String[fields.size()]);
    }
    public Object[] getValues()
    {
        return fields.values().toArray(new String[fields.size()]);
    }
    public void setValue(String name,Object value)
    {
        fields.put(name, value);
    }
    public boolean hasName(String name)
    {
        return fields.containsKey(name);
    }
    public Long getID()
    {
        return getLongValue("id");
    }
    public String getString()
    {
        return fields.toString();
    }
    public String toXML() throws IOException,ParserConfigurationException,SAXException
    {
        XMLProcessor proc = new XMLProcessor();
        proc.addNodes("root", fields);
        String result = proc.toXMLString();
        return result;
    }
    @Override
    public String toString()
    {
	return fields.toString();
    }
    public void loadFromDB(DBProcessor proc) throws SQLException,UserInitializationException
    {
        DBRecord u = new DBRecord(proc.getRecord("users", "id="+String.valueOf(this.getID()), null));
        if(u!=null)
        {
            this.fields = u.fields;
        }
    }
    public void loadFromDB(DBProcessor proc, String condition) throws SQLException,UserInitializationException
    {
        DBRecord u = new DBRecord(proc.getRecord("users", condition, null));
        if(u!=null)
        {
            this.fields = u.fields;
        }
    }
    public Long createInDB(DBProcessor proc) throws SQLException
    {
        return proc.executeInsert("users", fields);
    }
    public void saveToDB(DBProcessor proc) throws SQLException
    {
        proc.executeUpdate("users", fields, "id="+this.getID());
    }
}

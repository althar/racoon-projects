package ftwo.library.database;

import ftwo.library.xml.XMLProcessor;
import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class DBRecord extends Object
{
    //private int ID;
    public HashMap<String,Object> Fields;

    public DBRecord(DBRecord rec)
    {
	    this(rec.Fields);
    }
    public DBRecord(HashMap<String,Object> fields)
    {
        Object id = fields.get("id");
        Fields = fields;
    }
    public DBRecord(Integer id,HashMap<String,Object> fields)
    {
        Fields = fields;
	    setValue("id", id);
    }
    public HashMap<String,Object> getFields()
    {
        return Fields;
    }
    public Object getValue(String name)
    {
        return Fields.get(name);
    }
    public Integer getIntValue(String name)
    {
        try
        {

            return (Integer.valueOf(getValue(name).toString()));
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
            return (Long.valueOf(getValue(name).toString()));
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
            if(getValue(name).toString().equalsIgnoreCase("1"))
            {
                return true;
            }
            return (Boolean.valueOf(getValue(name).toString()));
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
            return getValue(name).toString();
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
            return (Double.valueOf(getValue(name).toString()));
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public String[] getNames()
    {
        return Fields.keySet().toArray(new String[Fields.size()]);
    }
    public Object[] getValues()
    {
        return Fields.values().toArray(new String[Fields.size()]);
    }
    public void setValue(String name,Object value)
    {
        Fields.put(name, value);
    }
    public boolean hasName(String name)
    {
        return Fields.containsKey(name);
    }
    public int getID()
    {
        return getIntValue("id");
    }
    public String getString()
    {
        return Fields.toString();
    }
    public String toXML() throws IOException,ParserConfigurationException,SAXException
    {
        XMLProcessor proc = new XMLProcessor();
        proc.addNodes("root",Fields);
        String result = proc.toXMLString();
        return result;
    }
    @Override
    public String toString()
    {
        return Fields.toString();
    }
}

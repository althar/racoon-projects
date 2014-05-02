package racoonsoft.racoonspring.data.structure;

import org.xml.sax.SAXException;
import racoonsoft.racoonspring.data.xml.XMLProcessor;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class DatabaseStructure
{
    private HashMap<String,Object> fields = new HashMap<String, Object>();

    public void clear() throws Exception
    {
        Field[] fs = this.getClass().getDeclaredFields();
        for(Field f:fs)
        {
            try
            {
                f.set(this,null);
            }
            catch (Exception ex)
            {

            }
        }
        fields.clear();
    }
    public void set(String name, Object value) throws Exception
    {
        Field f = this.getClass().getDeclaredField(name);
        if(f!=null)
        {
            f.set(this,value);
            return;
        }
        fields.put(name,value);
    }
    public Object getValue(String name) throws Exception
    {
        Field f = this.getClass().getDeclaredField(name);
        if(f!=null)
        {
            return f.get(this);
        }
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
    public boolean hasName(String name) throws Exception
    {
        Field f = this.getClass().getDeclaredField(name);
        if(f!=null)
        {
            return true;
        }
        return fields.containsKey(name);
    }
    public Long id()
    {
        return getLongValue("id");
    }
    public String toXML() throws IOException,ParserConfigurationException,SAXException
    {
        XMLProcessor proc = new XMLProcessor();
        proc.addNodes("root", fields);
        String result = proc.toXMLString();
        return result;
    }
}

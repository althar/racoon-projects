package racoonsoft.racoonspring.data.structure;

import org.xml.sax.SAXException;
import racoonsoft.racoonspring.data.annotation.DataStructureFieldExclude;
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
    @DataStructureFieldExclude
    protected HashMap<String,Object> fields = new HashMap<String, Object>();

    public HashMap<String,Object> fields()
    {
        return fields;
    }
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
    public DatabaseStructure set(String name, Object value) throws Exception
    {
        try
        {
            Field f = this.getClass().getDeclaredField(name);
            if(f!=null)
            {
                f.setAccessible(true);
                f.set(this,value);
                return this;
            }
        }
        catch (Exception ex)
        {

        }
        fields.put(name,value);
        return this;
    }
    public Object getValue(String name) throws Exception
    {
        try
        {
            Field f = this.getClass().getDeclaredField(name);
            if(f!=null)
            {
                f.setAccessible(true);
                return f.get(this);
            }
        }
        catch (Exception ex)
        {

        }
        return fields.get(name);
    }
    public Object get(String name) throws Exception
    {
        return getValue(name);
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
            return (String)getValue(name).toString();
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
    public String toXML() throws Exception
    {
        XMLProcessor proc = new XMLProcessor();
        proc.addNodes("root", fields);
        String result = proc.toXMLString();
        return result;
    }
    public HashMap<String,Object> toHashmap(boolean excludeAnnotated) throws Exception
    {
        HashMap<String,Object> result = new HashMap<String, Object>();
        Field[] fs = this.getClass().getDeclaredFields();
        for(Field f:fs)
        {
            if(!excludeAnnotated||!f.isAnnotationPresent(DataStructureFieldExclude.class))
            {
                f.setAccessible(true);
                String name = f.getName();
                Object value = f.get(this);
                result.put(name,value);
            }
        }
        return result;
    }
}

package racoonsoft.library.json;

import org.json.JSONObject;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;
import racoonsoft.library.annotations.DataStructureValue;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.xml.XMLProcessor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


public class JSONProcessor
{
    private String BodyString;
    private HashMap<String,Object> BodyStructure = new HashMap<String, Object>();

    public JSONProcessor(String body) throws Exception
    {
		BodyString = body.trim();
		extractBody();
        System.out.println(BodyString);
    }
	public JSONProcessor(HashMap<String,Object> structure) throws Exception
    {
		BodyStructure = structure;
    }
    private void extractBody() throws Exception
    {
		if(BodyString.charAt(0)!='{'||BodyString.charAt(BodyString.length()-1)!='}')
		{
			throw new Exception("Wrong JSON format");
		}
		HashMap<String,String> pairs = StringAnalyzer.extractJSONPairs(BodyString);
		BodyStructure = StringAnalyzer.extractPairs(pairs);
    }
    
    public HashMap<String,Object> getStructure()
    {
		return BodyStructure;
    }
    public Object getValue(String path)
    {
		Object current_leaf = BodyStructure;
		String[] leafs = path.split("\\.");
		for(int i=0; i<leafs.length; i++)
		{
			if(current_leaf instanceof HashMap)// Object
			{
				current_leaf = ((HashMap)current_leaf).get(leafs[i]);
			}
            else if(current_leaf instanceof DBRecord)// Object
            {
                current_leaf = ((DBRecord)current_leaf).Fields.get(leafs[i]);
            }
			else if(current_leaf instanceof ArrayList)
			{
				current_leaf = ((ArrayList)current_leaf).get(Integer.valueOf(leafs[i]));
			}
			else
			{
				return null;
			}
		}
		return current_leaf;
    }
    public Integer getIntValue(String path)
    {
		try
		{
			return Integer.valueOf(getValue(path).toString());
		}
		catch(Exception ex)
		{
			return null;
		}
    }
    public Double getDoubleValue(String path)
    {
		try
		{
			return Double.valueOf(getValue(path).toString());
		}
		catch(Exception ex)
		{
			return null;
		}
    }

    public XMLProcessor toXMLProcessor() throws IOException,ParserConfigurationException,SAXException
    {
		XMLProcessor proc = new XMLProcessor();
		proc.addNodes("root", BodyStructure);
		return proc;
    }
	public String toXMLString() throws IOException,ParserConfigurationException,SAXException
	{
		return toXMLProcessor().toXMLString();
	}
    private String jsonValue(Object val) throws Exception
    {
        StringBuilder builder = new StringBuilder();
        if(val==null)
        {
            return "\"null\"";
        }
        if(val instanceof DBRecord)
        {
            val = ((DBRecord)val).Fields;
        }
        Field[] fs = val.getClass().getDeclaredFields();
        for(Field f:fs)
        {
            f.setAccessible(true);
            if(f.isAnnotationPresent(DataStructureValue.class))
            {
                Object value = f.get(val);
                return jsonValue(value);
            }
        }
        if(val instanceof HashMap)
        {
            builder.append("{");
            Iterator<String> keyIter = ((HashMap<String,Object>)val).keySet().iterator();
            while(keyIter.hasNext())
            {
                Object key = keyIter.next();
                Object value = ((HashMap<String,Object>)val).get(key);
                builder.append("\""+key+"\"");
                builder.append(":");
                builder.append(jsonValue(value));
                if(keyIter.hasNext())
                {
                    builder.append(",");
                }
            }
            builder.append("}");
        }
        else if(val instanceof List)
        {
            builder.append("[");
            for(int i=0; i<((List)val).size(); i++)
            {
                builder.append(jsonValue(((List)val).get(i)));
                if(i<((List)val).size()-1)
                {
                    builder.append(",");
                }
            }
            builder.append("]");
        }
        else if(val.getClass().isAnnotationPresent(DataStructure.class))
        {
            Field[] fields = val.getClass().getFields();
            builder.append("{");
            for(int i=0; i<fields.length; i++)
            {
                Field f = fields[i];
                if(f.isAnnotationPresent(DataStructureField.class))
                {
                    DataStructureField ff = (DataStructureField)f.getAnnotation(DataStructureField.class);
                    String name = ff.name();
                    Object value = f.get(val);
                    builder.append("\""+name+"\"");
                    builder.append(":");
                    builder.append(jsonValue(value));
                    if(i<fields.length-1)
                    {
                        builder.append(",");
                    }
                }
            }
            builder.append("}");
        }
        else if(val instanceof Integer||val instanceof Long||val instanceof Double||val instanceof Boolean)
        {
            builder.append(val.toString());
        }
        else if(val instanceof String)
        {
            builder.append("\""+val.toString()+"\"");
        }
        else
        {
            builder.append("\""+val.toString()+"\"");
        }
        return builder.toString();
    }
    public String jsonString() throws Exception
    {
        return jsonValue(getStructure());
    }
    public String toJsonString() throws Exception
    {
        return jsonString();
    }
}

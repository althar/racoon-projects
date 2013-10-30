package ftwo.library.json;

import ftwo.library.xml.XMLProcessor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
}

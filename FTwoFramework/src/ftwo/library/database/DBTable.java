package ftwo.library.database;

import ftwo.library.xml.XMLProcessor;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class DBTable
{
    private String TableName;
    private ArrayList<DBRecord> Data;

    public DBTable(String name,ArrayList<DBRecord> data)
    {
	    TableName = name;
	    Data = data;
    }
    public void add(DBRecord rec)
    {
	    Data.add(rec);
    }
    public DBRecord get(int i)
    {
        return Data.get(i);
    }
    public ArrayList<DBRecord> get()
    {
        return Data;
    }
    public int size()
    {
	return Data.size();
    }
    public String getName()
    {
	return TableName;
    }
    public XMLProcessor toXMLProcessor() throws IOException,ParserConfigurationException,SAXException
    {
        XMLProcessor proc = new XMLProcessor();
        proc.addNode("root", TableName, Data);
        return proc;
    }
    public String toXML() throws IOException,ParserConfigurationException,SAXException
    {
        String result = toXMLProcessor().toXMLString();
        return result;
    }
}

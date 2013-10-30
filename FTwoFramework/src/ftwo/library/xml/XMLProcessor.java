package ftwo.library.xml;

import com.sun.org.apache.xerces.internal.dom.ChildNode;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import ftwo.library.database.DBRecord;
import ftwo.library.database.DBTable;
import ftwo.library.json.JSONProcessor;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Федорович Дмитрий
 */
public class XMLProcessor
{
    private Document document;

    public XMLProcessor(File file) throws IOException,ParserConfigurationException,SAXException
    {
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }
    public XMLProcessor(String xml) throws IOException,ParserConfigurationException,SAXException
    {
        StringInputStream inp = new StringInputStream(xml);
        //System.out.println(inp.getDataString());
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inp);
    }
    public XMLProcessor() throws IOException,ParserConfigurationException,SAXException
    {
        this("<?xml version=\"1.0\"?><root></root>");
    }
    public ArrayList<Node> getNodes(Node node,String path)
    {
        ArrayList<Node> leafs = new ArrayList<Node>();
        String[] parts = path.split("\\.");
        ArrayList<Node> levelNodes = new ArrayList<Node>();
        levelNodes.add(node);
        for(int i=0; i<parts.length; i++)
        {
            ArrayList<Node> levelBuff = new ArrayList<Node>();
            for(int j=0; j<levelNodes.size(); j++)
            {
                ArrayList<Node> nodes = getClidrenNodes(levelNodes.get(j), parts[i]);
                levelBuff.addAll(nodes);
            }
            levelNodes = levelBuff;
        }
        return levelNodes;
    }
    public ArrayList<Node> getNodes(String path)
    {
        Node node = document;
        String[] parts = path.split("\\.");
        ArrayList<Node> levelNodes = new ArrayList<Node>();
        levelNodes.add(node);
        for(int i=0; i<parts.length; i++)
        {
            ArrayList<Node> levelBuff = new ArrayList<Node>();
            for(int j=0; j<levelNodes.size(); j++)
            {
                ArrayList<Node> nodes = getClidrenNodes(levelNodes.get(j), parts[i]);
                levelBuff.addAll(nodes);
            }
            levelNodes = levelBuff;
        }
        return levelNodes;
    }
    public Node getNode(Node node,String path)
    {
        ArrayList<Node> nodes = getNodes(node, path);
        if(nodes.size()>0)
        {
            return nodes.get(0);
        }
        return null;
    }
    public Node getNode(String path)
    {
        ArrayList<Node> nodes = getNodes(path);
        if(nodes.size()>0)
        {
            return nodes.get(0);
        }
        return null;
    }

    public ArrayList<String> getValues(Node node,String path)
    {
        ArrayList<Node> nodes = getNodes(node, path);
        ArrayList<String> res = new ArrayList<String>();
        for(int i=0; i<nodes.size(); i++)
        {
            res.add(nodes.get(i).getTextContent());
        }
        return res;
    }
    public ArrayList<String> getValues(String path)
    {
        return getValues(document, path);
    }
    public String getValue(Node node,String path)
    {
        ArrayList<String> nodes = getValues(node, path);
        if(nodes.size()>0)
        {
            return nodes.get(0);
        }
        return null;
    }
    public String getValue(String path)
    {
        ArrayList<String> nodes = getValues(document, path);
        if(nodes.size()>0)
        {
            return nodes.get(0);
        }
        return null;
    }

    public NamedNodeMap getAttributes(Node node,String path)
    {
        ArrayList<Node> nodes = getNodes(node, path);
        if(nodes.size()>0)
        {
            return nodes.get(0).getAttributes();
        }
        return null;
    }
    public NamedNodeMap getAttributes(String path)
    {
        ArrayList<Node> nodes = getNodes( path);
        if(nodes.size()>0)
        {
            return nodes.get(0).getAttributes();
        }
        return null;
    }
    public String getAttribute(Node node,String path,String attrName)
    {
        NamedNodeMap map = getAttributes(node, path);
        return map.getNamedItem(attrName).getNodeValue();
    }
    public String getAttributeOfNode(Node n,String attrName)
    {
	NamedNodeMap map = n.getAttributes();
        return map.getNamedItem(attrName).getNodeValue();
    }
    public String getAttribute(String path,String attrName)
    {
        NamedNodeMap map = getAttributes(document, path);
        return map.getNamedItem(attrName).getNodeValue();
    }

    public void addNode(String path,String nodeName)
    {
        addNode(path,nodeName,"");
    }

    // TO EXTEND !!!
    public void addNode(String path,String nodeName,Object value)
    {
        if(value == null)
        {
            Element newNode = document.createElement(nodeName);
            newNode.setTextContent("");
            getNode(path).appendChild(newNode);
        }
        else if(value instanceof String)
        {
            Element newNode = document.createElement(nodeName);
            newNode.setTextContent((String)value);
            getNode(path).appendChild(newNode);
        }
        else if(value instanceof Integer||value instanceof Long||value instanceof Double||value instanceof Float)
        {
            Element newNode = document.createElement(nodeName);
            newNode.setTextContent(value.toString());
            getNode(path).appendChild(newNode);
        }
        else if(value instanceof ArrayList)
        {
            ArrayList<Object> arr = (ArrayList)value;
            Element newNode = document.createElement(nodeName);
            getNode(path).appendChild(newNode);
            for(int i=0; i<arr.size(); i++)
            {
                String inner_path = "";
                if(!path.equalsIgnoreCase(".")&&!path.equalsIgnoreCase(""))
                {
                    inner_path = path+".";
                }
                addNode(inner_path+""+nodeName, "item_"+(i+1), arr.get(i));
            }
        }
        else if(value instanceof DBTable)
        {
            DBTable dbt = (DBTable)value;
            ArrayList<Object> arr = (ArrayList)dbt.get();
            Element newNode = document.createElement(nodeName);
            getNode(path).appendChild(newNode);
            for(int i=0; i<arr.size(); i++)
            {
                String inner_path = "";
                if(!path.equalsIgnoreCase(".")&&!path.equalsIgnoreCase(""))
                {
                    inner_path = path+".";
                }
                addNode(inner_path+""+nodeName, "item_"+(i+1), arr.get(i));
            }
        }
        else if(value instanceof HashMap)
        {
            HashMap<String,Object> arr = (HashMap<String,Object>)value;
            Element newNode = document.createElement(nodeName);
            getNode(path).appendChild(newNode);
            String inner_path = "";
            if(!path.equalsIgnoreCase(".")&&!path.equalsIgnoreCase(""))
            {
            inner_path = path+".";
            }
            inner_path += nodeName;
            addNodes(inner_path, arr);
        }
        else if(value instanceof DBRecord)
        {
            HashMap<String,Object> arr = ((DBRecord)value).Fields;
            Element newNode = document.createElement(nodeName);
            getNode(path).appendChild(newNode);
            String inner_path = "";
            if(!path.equalsIgnoreCase(".")&&!path.equalsIgnoreCase(""))
            {
            inner_path = path+".";
            }
            inner_path += nodeName;
            addNodes(inner_path, arr);
        }
        else
        {
            Element newNode = document.createElement(nodeName);
            newNode.setTextContent(value.toString());
            getNode(path).appendChild(newNode);
        }
    }
    public void addNodes(String path,HashMap<String,Object> map)
    {
        if(map==null)
        {
            return;
        }
        Iterator<String> iter = map.keySet().iterator();
        while(iter.hasNext())
        {
            String key = iter.next();
            Object value = map.get(key);
            addNode(path, key, value);
        }
    }
    public void addNodesUnnamed(String path,HashMap<Integer,Object> map)
    {
        if(map==null)
        {
            return;
        }
        Iterator<Integer> iter = map.keySet().iterator();
	int index = 0;
        while(iter.hasNext())
        {
	    index++;
            Integer key = iter.next();
            Object value = map.get(key);
            addNode(path, "item_"+index, value);
        }
    }
    public void setAttribute(String nodePath, String attrName,String attrValue)
    {
        ((Element)getNode(nodePath)).setAttribute(attrName, attrValue);
    }
    public void setValue(String nodePath,String value)
    {

        getNode(nodePath).setTextContent(value);
    }
    private ArrayList<Node> getClidrenNodes(Node parent,String nodeName)
    {
        ArrayList<Node> res = new ArrayList<Node>();
        NodeList nl = parent.getChildNodes();
        int len = nl.getLength();
        for(int i=0; i<len; i++)
        {
            if(nl.item(i).getNodeName().equalsIgnoreCase(nodeName))
            {
                res.add(nl.item(i));
            }
        }
        return res;
    }
    public  String toXMLString() throws IOException
    {
        OutputFormat format    = new OutputFormat (document);
        StringWriter stringOut = new StringWriter ();
        XMLSerializer serial   = new XMLSerializer (stringOut, format);
        serial.serialize(document);
        return stringOut.toString();
    }
    @Override
    public String toString()
    {
        try
        {
            return toXMLString();
        }
        catch (Exception ex)
        {
            return super.toString();
        }

    }
    public static String correctXMLValue(String value)
    {
		String res = value;
	    res = res.replace("&", "&amp;");
	    res = res.replace("\"", "&quot;");
	    res = res.replace("'", "&apos;");
	    res = res.replace(">", "&gt;");
	    res = res.replace("<", "&lt;");
	    return res;
    }
}

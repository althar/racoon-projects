package racoonsoft.library.xml;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.database.DBTable;

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
        OutputFormat outFormat = new OutputFormat(document,"Windows-1252",true);
    }
    public XMLProcessor() throws IOException,ParserConfigurationException,SAXException
    {
        this("<?xml version=\"1.0\"?><root></root>");
    }
    public XMLProcessor(boolean noRoot,String header) throws IOException,ParserConfigurationException,SAXException
    {
        this(header);
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

    public Node addNode(String path,String nodeName)
    {
        return addNode(path,nodeName,"");
    }

    // We use NODES. So inner_pathes aren`t needed!!!!!!
    public Node addNode(Node node,String nodeName,Object value)
    {
        Element newNode = document.createElement(nodeName);
        if(value == null)
        {
            newNode.setTextContent("");
            node.appendChild(newNode);
        }
        else if(value instanceof String)
        {
            newNode.setTextContent((String)value);
            node.appendChild(newNode);
        }
        else if(value instanceof Integer||value instanceof Long||value instanceof Double||value instanceof Float)
        {
            newNode.setTextContent(value.toString());
            node.appendChild(newNode);
        }
        else if(value instanceof ArrayList)
        {
            ArrayList<Object> arr = (ArrayList)value;
            node.appendChild(newNode);
            for(int i=0; i<arr.size(); i++)
            {
                String inner_path = "";

                //addNode(node, "item_"+(i+1), arr.get(i));
                addNode(newNode, "item", arr.get(i));
            }
        }
        else if(value instanceof DBTable)
        {
            DBTable dbt = (DBTable)value;
            ArrayList<Object> arr = (ArrayList)dbt.get();
            node.appendChild(newNode);
            for(int i=0; i<arr.size(); i++)
            {
                String inner_path = "";

                //addNode(node, "item_"+(i+1), arr.get(i));
                addNode(newNode, "item", arr.get(i));
            }
        }
        else if(value instanceof HashMap)
        {
            HashMap<String,Object> arr = (HashMap<String,Object>)value;
            node.appendChild(newNode);
            addNodes(newNode, arr);
        }
        else if(value instanceof DBRecord)
        {
            HashMap<String,Object> arr = ((DBRecord)value).fields;
            node.appendChild(newNode);
            addNodes(newNode, arr);
        }
        else
        {
            newNode.setTextContent(value.toString());
            node.appendChild(newNode);
        }
        return newNode;
    }
    public Node addNode(String path,String nodeName,Object value)
    {
        Element newNode = document.createElement(nodeName);
        if(value == null)
        {
            newNode.setTextContent("");
            getNode(path).appendChild(newNode);
        }
        else if(value instanceof String)
        {
            newNode.setTextContent((String)value);
            getNode(path).appendChild(newNode);
        }
        else if(value instanceof Integer||value instanceof Long||value instanceof Double||value instanceof Float)
        {
            newNode.setTextContent(value.toString());
            if(getNode(path)!=null)
            {
                getNode(path).appendChild(newNode);
            }
        }
        else if(value instanceof ArrayList)
        {
            ArrayList<Object> arr = (ArrayList)value;
            getNode(path).appendChild(newNode);
            for(int i=0; i<arr.size(); i++)
            {
                String inner_path = "";
                if(!path.equalsIgnoreCase(".")&&!path.equalsIgnoreCase(""))
                {
                    inner_path = path+".";
                }
//                addNode(inner_path+""+nodeName, "item_"+(i+1), arr.get(i));
                addNode(inner_path+""+nodeName, "item", arr.get(i));
            }
        }
        else if(value instanceof DBTable)
        {
            DBTable dbt = (DBTable)value;
            ArrayList<Object> arr = (ArrayList)dbt.get();
            getNode(path).appendChild(newNode);
            for(int i=0; i<arr.size(); i++)
            {
                String inner_path = "";
                if(!path.equalsIgnoreCase(".")&&!path.equalsIgnoreCase(""))
                {
                    inner_path = path+".";
                }
                addNode(inner_path+""+nodeName, "item_"+(i+1), arr.get(i));
//                addNode(inner_path+""+nodeName, "item", arr.get(i));
            }
        }
        else if(value instanceof HashMap)
        {
            HashMap<String,Object> arr = (HashMap<String,Object>)value;
            getNode(path).appendChild(newNode);
            String inner_path = "";
            if(!path.equalsIgnoreCase(".")&&!path.equalsIgnoreCase(""))
            {
            inner_path = path+".";
            }
            inner_path += nodeName;
            addNodes(newNode, arr);
        }
        else if(value instanceof DBRecord)
        {
            HashMap<String,Object> arr = ((DBRecord)value).fields;
            getNode(path).appendChild(newNode);
            String inner_path = "";
            if(!path.equalsIgnoreCase(".")&&!path.equalsIgnoreCase(""))
            {
            inner_path = path+".";
            }
            inner_path += nodeName;
//            addNodes(inner_path, arr);
            addNodes(newNode, arr);
        }
        else
        {
            newNode.setTextContent(value.toString());
            getNode(path).appendChild(newNode);
        }
        return newNode;
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
    public void addNodes(Node node,HashMap<String,Object> map)
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
            addNode(node, key, value);
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
            //addNode(path, "item", value);
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
    public  String toXMLString(String encoding) throws IOException
    {
        OutputFormat format = new OutputFormat (document);
        format.setEncoding(encoding);
        format.setLineWidth(65);
        format.setIndenting(true);
        format.setIndent(2);
        StringWriter stringOut = new StringWriter ();
        XMLSerializer serial   = new XMLSerializer (stringOut, format);
        serial.serialize(document);
        return stringOut.toString();
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

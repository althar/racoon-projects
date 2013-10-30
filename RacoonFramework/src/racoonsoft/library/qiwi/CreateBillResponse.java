package racoonsoft.library.qiwi;

import racoonsoft.library.xml.XMLProcessor;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class CreateBillResponse
{
    private Integer ResponseCode;
    private boolean Fatal;
    private String Body;
    private String TxnID;

    public CreateBillResponse(String xml) throws IOException,ParserConfigurationException,SAXException
    {
	Body = xml;
	XMLProcessor proc = new XMLProcessor(xml);
	Fatal = Boolean.valueOf(proc.getAttribute("response.result-code", "fatal"));
	ResponseCode = Integer.valueOf(proc.getValue("response.result-code"));
    }
    public CreateBillResponse(byte[] bs) throws IOException,ParserConfigurationException,SAXException
    {
	String body = new String(bs,"UTF-8");
	Body = body;
	XMLProcessor proc = new XMLProcessor(Body);
	Fatal = Boolean.valueOf(proc.getAttribute("response.result-code", "fatal"));
	ResponseCode = Integer.valueOf(proc.getValue("response.result-code"));
    }
    public Integer getResponseCode()
    {
	return ResponseCode;
    }
    public boolean getFatal()
    {
	return Fatal;
    }
    public String getXML()
    {
	return Body;
    }
    public void setTxnID(String txn_id)
    {
        TxnID = txn_id;
    }
    public String getTxnID()
    {
        return TxnID;
    }
    public byte[] getPackage()
    {
	try
	{
	    byte[] bs = getXML().getBytes("UTF-8");
	    return bs;
	}
	catch(Exception ex)
	{
	    return new byte[0];
	}
    }
}

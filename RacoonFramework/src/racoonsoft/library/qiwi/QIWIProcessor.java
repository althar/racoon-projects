package racoonsoft.library.qiwi;

import racoonsoft.library.http.HTTPClient;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class QIWIProcessor
{
    private String ProtocolVersion = "";
    private String TerminalID;
    private String Password;
    private String BillIDPrefix = "BILL";
    private Integer BillIndex = 0;

    private String QiwiURL = "http://ishop.qiwi.ru/xml";

    public QIWIProcessor(String qiwi_url,String protocol_version,String terminal_id,String password,String bill_prefix,Integer bill_start_index)
    {
	QiwiURL = qiwi_url;
	ProtocolVersion = protocol_version;
	TerminalID = terminal_id;
	Password = password;
	BillIDPrefix = bill_prefix;
	BillIndex = bill_start_index;
    }

    public CreateBillResponse createBill(String client_number,String comment,double amount,boolean sms_alarm,boolean call_alarm,boolean create_agent,Integer life_time_hours)
	    throws IOException,ParserConfigurationException,SAXException
    {
	CreateBillRequest req = new CreateBillRequest(ProtocolVersion, TerminalID, Password, getNextBillID(), client_number, comment, amount, create_agent, sms_alarm, call_alarm,life_time_hours);
	String bs = HTTPClient.sendHTTPRequest(req.getXML(),"UTF-8", QiwiURL);
        CreateBillResponse resp = new CreateBillResponse(bs);
        resp.setTxnID(req.getTxnID());
	return resp;
    }
    public CheckBillResponse checkBills(ArrayList<String> bill_ids)
	    throws IOException,ParserConfigurationException,SAXException
    {
	CheckBillRequest req = new CheckBillRequest(ProtocolVersion, TerminalID, Password, bill_ids);
	String bs = HTTPClient.sendHTTPRequest(req.getXML(),"UTF-8", QiwiURL);
	return new CheckBillResponse(bs);
    }
    private String getNextBillID()
    {
	String res = BillIDPrefix+BillIndex.toString();
	BillIndex+=1;
	return res;
    }
}

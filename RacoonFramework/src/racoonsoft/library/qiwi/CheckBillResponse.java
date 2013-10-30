package racoonsoft.library.qiwi;

import racoonsoft.library.xml.XMLProcessor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class CheckBillResponse
{
//    0 Успех
//13 Сервер занят, повторите запрос позже
//150 Ошибка авторизации (неверный логин/пароль)
//210 Счет не найден
//215 Счет с таким txn-id уже существует
//241 Сумма слишком мала
//242 Превышена максимальная сумма платежа – 15 000р.
//278 Превышение максимального интервала получения списка счетов
//298 Агента не существует в системе
//300 Неизвестная ошибка
//330 Ошибка шифрования
//339 Не пройден контроль IP-адреса
//370 Превышено максимальное кол-во одновременно выполняемых запросов



    private Integer ResponseCode;
    private boolean Fatal;
    private String Body;
    private HashMap<String,BillStatus> BillStatuses = new HashMap<String, BillStatus>();

    public CheckBillResponse(String xml) throws IOException,ParserConfigurationException,SAXException
    {
	Body = xml;
	parseBody();
    }
    public CheckBillResponse(byte[] bs) throws IOException,ParserConfigurationException,SAXException
    {
	String body = new String(bs,"UTF-8");
	Body = body;
	parseBody();
    }
    private void parseBody() throws IOException,NumberFormatException,ParserConfigurationException,SAXException
    {
	XMLProcessor proc = new XMLProcessor(Body);
	Fatal = Boolean.valueOf(proc.getAttribute("response.result-code", "fatal"));
	ResponseCode = Integer.valueOf(proc.getValue("response.result-code"));

	ArrayList<Node> bill_nodes = proc.getNodes("response.bills-list.bill");
	for(int i=0; i<bill_nodes.size(); i++)
	{
	    Node n = bill_nodes.get(i);
	    String bill_id = proc.getAttributeOfNode(n, "id");
	    String status = proc.getAttributeOfNode(n, "status");
	    String sum = proc.getAttributeOfNode(n, "sum");
	    BillStatus bs = new BillStatus(bill_id, Integer.valueOf(status), Double.valueOf(sum));
	    BillStatuses.put(bill_id, bs);
	}
    }
    public BillStatus getBillStatus(String bill_id)
    {
	return BillStatuses.get(bill_id);
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

package ftwo.library.qiwi;

import java.util.ArrayList;

public class CheckBillRequest
{
    private static String REQUEST_TYPE = "33";// For bill check...

    private String ProtocolVersion = "4.00";
    private String TerminalID;
    private String Password;
    private ArrayList<String> BillIDs = new ArrayList<String>();

    public CheckBillRequest(String protocol_version,String terminal_id,String password,ArrayList<String> bill_ids)
    {
        ProtocolVersion = protocol_version;
        TerminalID = terminal_id;
        Password = password;
        BillIDs = bill_ids;
    }
    public String getXML()
    {
        StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        builder.append("<request>");

        builder.append("<protocol-version>");
        builder.append(ProtocolVersion);
        builder.append("</protocol-version>");

        builder.append("<request-type>");
        builder.append(REQUEST_TYPE);
        builder.append("</request-type>");

        builder.append("<terminal-id>");
        builder.append(TerminalID);
        builder.append("</terminal-id>");

        builder.append("<extra name=\"password\">");
        builder.append(Password);
        builder.append("</extra>");

        builder.append("<bills-list>");

        for(int i=0; i<BillIDs.size(); i++)
        {
            builder.append("<bill txn-id=\"");
            builder.append(BillIDs.get(i));
            builder.append("\" />");
        }

        builder.append("</bills-list>");

        builder.append("</request>");

        return builder.toString();
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

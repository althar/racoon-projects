package ftwo.library.qiwi;

public class CreateBillRequest
{
    private static String REQUEST_TYPE = "30";// For bill creation...

    private String ProtocolVersion = "4.00";
    private String TerminalID;
    private String Password;
    private String TxnID;
    private String ToAccount;
    private String Comment;
    private Double Amount;
    private Integer BillLifetime;
    private boolean CreateAgent = false;
    private boolean AlarmSMS = false;
    private boolean AlarmCall = false;

    public CreateBillRequest(String protocol_version, String terminal_id, String password, String txn_id, String to_account, String comment, Double amount, boolean create_agent, boolean alarm_sms, boolean alarm_call, Integer life_time_hours)
    {
        ProtocolVersion = protocol_version;
        TerminalID = terminal_id;
        Password = password;
        TxnID = txn_id;
        ToAccount = to_account;
        Comment = comment;
        Amount = amount;
        CreateAgent = create_agent;
        AlarmCall = alarm_call;
        AlarmSMS = alarm_sms;
        BillLifetime = life_time_hours;
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

        builder.append("<extra name=\"txn-id\">");
        builder.append(TxnID);
        builder.append("</extra>");

        builder.append("<extra name=\"to-account\">");
        builder.append(ToAccount);
        builder.append("</extra>");

        builder.append("<extra name=\"amount\">");
        builder.append(Amount);
        builder.append("</extra>");

        builder.append("<extra name=\"comment\">");
        builder.append(Comment);
        builder.append("</extra>");

        builder.append("<extra name=\"create-agt\">");
        builder.append(Boolean.valueOf(CreateAgent).compareTo(false));
        builder.append("</extra>");

        builder.append("<extra name=\"ltime\">");
        builder.append(BillLifetime);
        builder.append("</extra>");

        builder.append("<extra name=\"ALARM_SMS\">");
        builder.append(Boolean.valueOf(AlarmSMS).compareTo(false));
        builder.append("</extra>");

        builder.append("<extra name=\"ACCEPT_CALL\">");
        builder.append(Boolean.valueOf(AlarmCall).compareTo(false));
        builder.append("</extra>");

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
        catch (Exception ex)
        {
            return new byte[0];
        }
    }

    public String getTxnID()
    {
        return TxnID;
    }
}

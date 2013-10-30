package ftwo.library.sms;


public class SMSMessage
{
    private String Phone;
    private String Text;
    private String Sender;

    public SMSMessage(String phone,String text,String sender)
    {
	Phone = phone;
	Text = text;
	Sender = sender;
    }
    public String getText()
    {
	return Text;
    }
    public String getPhone()
    {
	return Phone;
    }
    public String getSender()
    {
	return Sender;
    }
}

package racoonsoft.racoonspring.mail;

public class MessageStorage
{
    private static Queue MailLowQ = new Queue();
    private static Queue MailMediumQ = new Queue();
    private static Queue MailHighQ = new Queue();

//    private static ZingayaQueue SmsLowQ = new ZingayaQueue();
//    private static ZingayaQueue SmsMediumQ = new ZingayaQueue();
//    private static ZingayaQueue SmsHighQ = new ZingayaQueue();
    private static int MaximumQSize = 1000;

    public static boolean addMailLowPriority(MailMessage message)
    {
        if(MailLowQ.getLength()>=MaximumQSize)
        {
            return false;
        }
        MailLowQ.enqueue(message);
        return true;
    }
    public static boolean addMailMediumPriority(MailMessage message)
    {
        if(MailMediumQ.getLength()>=MaximumQSize)
        {
            return false;
        }
        MailMediumQ.enqueue(message);
        return true;
    }
    public static boolean addMailHighPriority(MailMessage message)
    {
        if(MailHighQ.getLength()>=MaximumQSize)
        {
            return false;
        }
        MailHighQ.enqueue(message);
        return true;
    }

//    public static boolean addSmsLowPriority(SmsMessage message)
//    {
//        if(SmsLowQ.getLength()>=MaximumQSize)
//        {
//            return false;
//        }
//        SmsLowQ.enqueue(message);
//        return true;
//    }
//    public static boolean addSmsMediumPriority(SmsMessage message)
//    {
//        if(SmsMediumQ.getLength()>=Configurations.MaximumQSize)
//        {
//            return false;
//        }
//        SmsMediumQ.enqueue(message);
//        return true;
//    }
//    public static boolean addSmsHighPriority(SmsMessage message)
//    {
//        if(SmsHighQ.getLength()>=Configurations.MaximumQSize)
//        {
//            return false;
//        }
//        SmsHighQ.enqueue(message);
//        return true;
//    }

    public static MailMessage getMail() throws InterruptedException
    {
        if(!MailHighQ.isEmpty())
        {
            return (MailMessage)MailHighQ.dequeue();
        }
        if(!MailMediumQ.isEmpty())
        {
            return (MailMessage)MailMediumQ.dequeue();
        }
        if(!MailLowQ.isEmpty())
        {
            return (MailMessage)MailLowQ.dequeue();
        }
        return null;
    }
//    public static SmsMessage getSms() throws InterruptedException
//    {
//        if(!SmsHighQ.isEmpty())
//        {
//            return (SmsMessage)SmsHighQ.dequeue();
//        }
//        if(!SmsMediumQ.isEmpty())
//        {
//            return (SmsMessage)SmsMediumQ.dequeue();
//        }
//        if(!SmsLowQ.isEmpty())
//        {
//            return (SmsMessage)SmsLowQ.dequeue();
//        }
//        return null;
//    }
}

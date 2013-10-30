package ftwo.library.test;

import ftwo.library.collections.MultiHashMap;
import ftwo.library.helper.Helper;
import ftwo.library.json.JSONProcessor;
import ftwo.library.mail.MailMessage;
import ftwo.library.mail.MailProcessor;
import ftwo.library.metrix.PerformanceCheck;
import ftwo.library.ozon.APIProcessor;
import ftwo.library.processors.FileTransferProcessor;
import ftwo.library.processors.FileUploadProcessor;
import ftwo.library.sms.SMSMessage;
import ftwo.library.sms.SMSProcessor;
import ftwo.library.xml.HtmlParser;
import sun.misc.GC;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.*;

public class Test
{
    final StringBuilder str_1 = new StringBuilder("string 1");
    final StringBuilder str_2 = new StringBuilder("string 2");
    final StringBuilder str_3 = new StringBuilder("string 3");
    public static void main(String[] args)
    {
	    Test t = new Test();
        t.doTest();

    }
    private void doTest()
    {
        try
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    synchronized (str_1)
                    {
                        System.out.println("lock str_1");
                        try
                        {
                            Thread.currentThread().sleep(100000);
                            synchronized (str_2)
                            {
                                Thread.currentThread().sleep(100);
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println("!!");
                        }
                    }
                    System.out.println("lock str_1 released");
                }
            }).start();

            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while(true)
                    {
                        try
                        {
                            System.out.println("do some...");
                            str_1.append("1");
                            str_1.append("2");
                            str_1.append("3");
                            str_1.trimToSize();
                            Thread.currentThread().sleep(100);
                        }
                        catch (Exception ex)
                        {
                            System.out.println("!!");
                        }

                    }
                }
            }).start();

//            new Thread(new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    synchronized (str_3)
//                    {
//                        System.out.println("lock str_3");
//                        try
//                        {
//                            Thread.currentThread().sleep(100);
//                            synchronized (str_1)
//                            {
//                                Thread.currentThread().sleep(100);
//                            }
//                        }
//                        catch(Exception ex)
//                        {
//                            System.out.println("!!");
//                        }
//                    }
//                    System.out.println("lock str_3 released");
//                }
//            }).start();


        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        finally
        {
        }
    }
}

package ftwo.library.test;

import ftwo.library.processors.FileTransferProcessor;

import java.net.Socket;
import java.util.Date;

public class KillTest
{
    public static void main(String[] args)
    {
        long start = new Date().getTime();
        try
        {
            int count = 0;
            while(true)
            {
                Socket sock = new Socket("mybill.ru",80);
                //Thread.sleep(100);
                int aval = sock.getInputStream().available();
                count ++;
                System.out.println(count);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        finally
        {
            System.out.println("Total time: "+(new Date().getTime()-start));
        }
    }
}

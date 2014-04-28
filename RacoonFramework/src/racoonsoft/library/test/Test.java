package racoonsoft.library.test;

import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.helper.Helper;
import racoonsoft.library.helper.StringHelper;
import racoonsoft.library.http.HTTPClient;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.mailru.torg.ContentAPIProcessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Test
{
    private static int count = 0;

    public static void main(String[] args)
    {
        long start = new Date().getTime();
        try
        {
            Thread[] ts = new Thread[40];
            for(int i=0; i<ts.length; i++)
            {
                ts[i] = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            for(int i=0; i<100; i++)
                            {
                                byte[] res = HTTPClient.sendHTTPRequest(new byte[]{},"http://businesswinner.ru/api/check_activity?game_id=86");
                                count++;
                                //System.out.println(new String(res,"UTF-8"));
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(ex.toString());
                        }
                    }
                });
            }
            start = new Date().getTime();
            for(int i=0; i<ts.length; i++)
            {
                ts[i].start();
            }
            Thread.sleep(2000);
            System.out.println("Total time: "+(new Date().getTime()-start)+" Total count: "+count);
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

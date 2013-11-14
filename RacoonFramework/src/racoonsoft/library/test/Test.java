package racoonsoft.library.test;

import racoonsoft.library.helper.Helper;
import racoonsoft.library.http.HTTPClient;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.processors.FileTransferProcessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;


public class Test
{
    public static void main(String[] args)
    {
	    long start = new Date().getTime();
        try
        {
            HashMap<String,String> headers = new HashMap<String, String>();
            //headers.put("Authorization","eY2OlZ7tCC42upkyvShmq6jj2XKQ5H");
            String json = HTTPClient.sendHTTPRequest("","http://content.api.torg.mail.ru/1.0/regions.json");
            //String json = HTTPClient.sendHTTPRequestWithHeaders("","http://content.api.torg.mail.ru/1.0/regions.json",headers);
            JSONProcessor jsonProc = new JSONProcessor(json);
            System.out.println(json);
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

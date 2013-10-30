package ftwo.library.engines.convey.gatekeeper.transceivers.http;

import com.sun.deploy.Environment;
import ftwo.library.engines.convey.commandstorage.Command;
import ftwo.library.helper.Helper;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class HttpTransmission
{
    private Socket sock;
    private InputStream in;
    private BufferedReader reader;
    private OutputStream out;
    private BufferedWriter writer;
    private Command RequestCommand;
    private Command ResponseCommand;
    //private String Name;
    private long MaxIdleTime = 2000;// 2 Seconds...
    private long LastAction;

    public static int STATUS_CONNECTED = 1;
    public static int STATUS_REQUEST_EXTRACTED = 2;
    public static int STATUS_RESPONSE_EXTRACTED = 3;
    public static int STATUS_RESPONSE_SENT = 10;
    public static int STATUS_CONNECTION_LOST = -1;

    private Boolean IsPost = null;
    private boolean ReadingBody = false;
    private int ContentLenght = 0;
    private String NewLine = "\r\n";

    private int Status = STATUS_CONNECTED;

    public HttpTransmission(Socket s) throws IOException
    {
        sock = s;
        in = sock.getInputStream();
        reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
        out = sock.getOutputStream();
        writer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
        //Name = name;
        RequestCommand = new Command();
        LastAction = new Date().getTime();
    }
    public static HttpTransmission create(String name, Socket s) throws IOException
    {
        HttpTransmission trans = new HttpTransmission(s);
        return trans;
    }
    public long getSequenceID()
    {
        return RequestCommand.getSequenceID();
    }
    public Command getRequestCommand()
    {
        return RequestCommand;
    }
    public Command getResponseCommand()
    {
        return ResponseCommand;
    }
    public void setResponseCommand(Command command)
    {
        Status = STATUS_RESPONSE_EXTRACTED;
        ResponseCommand = command;
    }
    public void process()
    {
        if(Status==STATUS_CONNECTED)// Reading request...
        {
            read();
        }
        if(Status==STATUS_RESPONSE_EXTRACTED)
        {
            write();
        }
    }
    private void read()
    {
        try
        {
            //Thread.currentThread().sleep(100);
            if(IsPost==null)// First line
            {
                if(reader.ready())
                {
                    String str = reader.readLine();
                    System.out.println(" ----> "+str);
                    if(str.length()<3)
                    {
                        Status = STATUS_CONNECTION_LOST;
                        return;
                    }
                    else
                    {
                        String[] strings = str.split("\\s");
                        if(strings.length!=3)
                        {
                            Status = STATUS_CONNECTION_LOST;
                            return;
                        }
                        else
                        {
                            if(strings[0].equalsIgnoreCase("post"))
                            {
                                RequestCommand.setValue("HTTP_METHOD","POST");
                                IsPost = true;
                            }
                            else if(strings[0].equalsIgnoreCase("get"))
                            {
                                RequestCommand.setValue("HTTP_METHOD","GET");
                                IsPost = false;
                            }
                            else
                            {
                                Status = STATUS_CONNECTION_LOST;
                                return;
                            }
                            String[] pairs = strings[1].replace("/?","").replace("?","").split("\\&");
                            for(int i=0; i<pairs.length; i++)
                            {
                                String[] pair = pairs[i].split("\\=",2);
                                if(pair.length==2)
                                {
                                    RequestCommand.setValue(pair[0],pair[1]);
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                if(ReadingBody)// POST only
                {
                    if(ContentLenght>0&&in.available()==ContentLenght)
                    {
                        byte[] buff = new byte[ContentLenght];
                        in.read(buff);
                        String body = new String(buff,"UTF-8");
                        String[] pairs = body.split("\\&");
                        for(int i=0; i<pairs.length; i++)
                        {
                            String[] pair = pairs[i].split("\\=",2);
                            if(pair.length==2)
                            {
                                RequestCommand.setValue(pair[0],pair[1]);
                            }
                        }
                        Status = STATUS_REQUEST_EXTRACTED;
                        return;
                    }
                    if(ContentLenght==0)
                    {
                        Status = STATUS_REQUEST_EXTRACTED;
                        return;
                    }
                }
                else
                {
                    if(reader.ready())
                    {
                        String str = reader.readLine();
                        System.out.println(" ---->>> "+str);
                        if(str.equalsIgnoreCase(""))
                        {
                            if(IsPost)
                            {
                                ReadingBody = true;
                                return;
                            }
                            else// Done
                            {
                                Status = STATUS_REQUEST_EXTRACTED;
                                return;
                            }
                        }
                        String[] params = str.split("\\:",2);
                        if(params.length==2)// Parameter
                        {
                            String key = params[0];
                            String value = params[1].replace(" ","");
                            if(key.equalsIgnoreCase("Content-Length"))
                            {
                                ContentLenght = Integer.parseInt(value);
                            }
                            RequestCommand.setValue(key,value);
                        }
                    }
                }
            }
        }
        catch(SocketTimeoutException sotoex)
        {
            System.out.println("RR:"+sotoex.toString());
        }
        catch(Exception ex)
        {
            System.out.println("receive inner exception: "+Helper.getStackTraceString(ex));
            Status = STATUS_CONNECTION_LOST;
        }
    }
    private void write()
    {
        try
        {
            StringBuilder response = new StringBuilder("HTTP/1.1 200 OK");
            response.append(NewLine);

            Object data = ResponseCommand.getValue("data");
//            if(data!=null)
//            {
//                byte[] data_buff = data.getBytes("UTF-8");
//                ResponseCommand.setValue("Content-Lenght",data_buff.length);
//            }
            Iterator<String> iter = ResponseCommand.Fields.keySet().iterator();
            while(iter.hasNext())
            {
                String key = iter.next();
                String value = ResponseCommand.getValue(key).toString();
                response.append(key);
                response.append(" : ");
                response.append(value);
                response.append(NewLine);
            }
            response.append(NewLine);
            if(data!=null)
            {
                response.append(data.toString());
            }
            System.out.println("RR:"+response.toString());
            sock.setSoTimeout(100);
            out.write(response.toString().getBytes("UTF-8"));
            out.flush();
            out.close();
            Status = STATUS_RESPONSE_SENT;
        }
        catch(Exception ex)
        {
            Status = STATUS_CONNECTION_LOST;
        }
    }
    private void checkAlive()
    {
        if((new Date().getTime()-LastAction)>MaxIdleTime)
        {
            Status = STATUS_CONNECTION_LOST;
        }
    }
    public int status()
    {
        return Status;
    }
}

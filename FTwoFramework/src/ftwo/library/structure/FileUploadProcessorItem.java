package ftwo.library.structure;

import ftwo.library.helper.Helper;
import ftwo.library.processors.FileUploadProcessor;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;

public class FileUploadProcessorItem
{
    private Socket Sock;
    private String Path;
    private File CurrentFile;
    private Long Size;
    private String Name;
    private String Parameters;
    private Integer ID;
    private int Status;
    private FileOutputStream FileStream;
    private long BytesRead;
    private long LastRead;
    private int MaxIdleTime = 30000;
    private long LastSentBytesRead = 0;
    private long CreateTime = 0;
    private long UploadedTime = 0;

    private InputStream Input;
    private OutputStream Output;

    public FileUploadProcessorItem(Socket s,String path, long size, String name, String parameters, Integer id) throws IOException
    {
        Sock = s;
        Sock.setSoTimeout(1000);
        Name = name;
        Size = size;
        Path = path;
        new File(Path).mkdirs();
        new File(Path+"\\"+Name).createNewFile();
        FileStream = new FileOutputStream (Path+"\\"+Name);
        Parameters = parameters;
        ID = id;
        Status = FileUploadProcessor.ITEM_STATUS_IS_UPLOADING;
        BytesRead = 0;

        Input = Sock.getInputStream();
        Output = Sock.getOutputStream();

        byte[] id_buff = Helper.intToBytes(ID);
        Output.write(id_buff);
        Output.flush();
        LastRead = new Date().getTime();
        CreateTime = LastRead;
    }
    public void read()
    {
        try
        {
            if(Status == FileUploadProcessor.ITEM_STATUS_IS_UPLOADING)
            {
                //System.out.println("Read performed: ");
                int aval = Input.available();
                if(aval>0)
                {
                    byte[] buff = new byte[aval];
                    Input.read(buff);
                    total_sent+=buff.length;
                    System.out.println("total read: "+total_sent);
                    FileStream.write(buff);
                    FileStream.flush();
                    //System.out.println("Read done: "+buff.length);
                    BytesRead+=buff.length;
                    if(BytesRead==Size)// Done
                    {
                        Status = FileUploadProcessor.ITEM_STATUS_UPLOADED;
                        FileStream.close();
                        writeStatus();
                        Input.close();
                        Output.flush();
                        Output.close();
                        UploadedTime = new Date().getTime();

                        System.out.println("File '"+Name+"' uploaded ("+getUploadTime()/1000+" secs)");
                        return;
                    }
                    else
                    {
                        LastRead = new Date().getTime();
                        writeStatus();
                    }
                }
                else
                {
                    long idle_time = new Date().getTime()-LastRead;
                    if(idle_time>MaxIdleTime)
                    {
                        Status = FileUploadProcessor.ITEM_STATUS_CANCELED_BY_CLIENT;
                    }
                }
            }
        }
        catch(IOException ioex)
        {
            Status = FileUploadProcessor.ITEM_STATUS_CANCELED_BY_CLIENT;
            System.out.println("read error: "+Helper.getStackTraceString(ioex));
        }
    }
    long total_sent = 0;
    public long getUploadTime()
    {
        if(UploadedTime!=0)
        {
            return UploadedTime-CreateTime;
        }
        else
        {
            return new Date().getTime() - CreateTime;
        }
    }
    public void writeStatus() throws IOException
    {
        if(LastSentBytesRead!=BytesRead)
        {
            byte[] br_buff = Helper.longToBytes(BytesRead);
            byte[] buff = new byte[9];
            buff[0] = br_buff[0];
            buff[1] = br_buff[1];
            buff[2] = br_buff[2];
            buff[3] = br_buff[3];
            buff[4] = br_buff[4];
            buff[5] = br_buff[5];
            buff[6] = br_buff[6];
            buff[7] = br_buff[7];
            buff[8] = (byte)Status;
            Output.write(buff);
            //Output.write(Status);

            Output.flush();
            LastSentBytesRead = BytesRead;
        }
    }
}

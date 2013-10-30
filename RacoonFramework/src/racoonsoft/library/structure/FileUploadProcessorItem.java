package racoonsoft.library.structure;

import racoonsoft.library.helper.Helper;
import racoonsoft.library.processors.FileTransferHandler;
import racoonsoft.library.processors.FileUploadProcessor;

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
    private HashMap<String,String> parameters = new HashMap<String, String>();
    private Integer ID;
    private int Status;
    private FileOutputStream FileStream;
    private long BytesRead;
    private long LastRead;
    private int MaxIdleTime = 30000;
    private long LastSentBytesRead = 0;
    private FileTransferHandler handler;

    private InputStream Input;
    private OutputStream Output;

    public FileUploadProcessorItem(Socket s,String filePath, String fileName, long size, String name, String parameters, Integer id,FileTransferHandler handler) throws IOException
    {
        this.handler = handler;
        Sock = s;
        Sock.setSoTimeout(1000);
        Name = name;
        Size = size;
        Path = filePath+"/"+fileName;
        new File(filePath).mkdirs();
        new File(Path).createNewFile();
        FileStream = new FileOutputStream (Path);
        extractParams(parameters);
        ID = id;
        Status = FileUploadProcessor.ITEM_STATUS_IS_UPLOADING;
        BytesRead = 0;

        Input = Sock.getInputStream();
        Output = Sock.getOutputStream();

        byte[] id_buff = Helper.intToBytes(ID);
        Output.write(id_buff);
        Output.flush();
        LastRead = new Date().getTime();
    }
    public Socket getSock()
    {
        return Sock;
    }
    public String getPath()
    {
        return Path;
    }
    public File getCurrentFile()
    {
        return CurrentFile;
    }
    public Long getSize()
    {
        return Size;
    }
    public String getName()
    {
        return Name;
    }
    public HashMap<String, String> getParameters()
    {
        return parameters;
    }
    public Integer getID()
    {
        return ID;
    }
    public int getStatus()
    {
        return Status;
    }
    public FileOutputStream getFileStream()
    {
        return FileStream;
    }
    public long getBytesRead()
    {
        return BytesRead;
    }
    public long getLastRead()
    {
        return LastRead;
    }
    public int getMaxIdleTime()
    {
        return MaxIdleTime;
    }
    public long getLastSentBytesRead()
    {
        return LastSentBytesRead;
    }
    public FileTransferHandler getHandler()
    {
        return handler;
    }
    public InputStream getInput()
    {
        return Input;
    }
    public OutputStream getOutput()
    {
        return Output;
    }
    public long getTotal_sent()
    {
        return total_sent;
    }
    private void extractParams(String params)
    {
        String[] pairs = params.split("\\,");
        for(String pair: pairs)
        {
            String[] pair_str = pair.split("\\=");
            if(pair_str.length==2)
            {
                parameters.put(pair_str[0],pair_str[1]);
            }
        }
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
                    //System.out.println("total read: "+total_sent);
                    FileStream.write(buff);
                    //System.out.println("Read done: "+buff.length);
                    BytesRead+=buff.length;
                    if(BytesRead==Size)// Done
                    {
                        Status = FileUploadProcessor.ITEM_STATUS_UPLOADED;
                        FileStream.close();
                        handler.processUploadFinish(this);
                    }
                    LastRead = new Date().getTime();
                    writeStatus();
                }
                else
                {
                    long idle_time = new Date().getTime()-LastRead;
                    if(idle_time>MaxIdleTime)
                    {
                        Status = FileUploadProcessor.ITEM_STATUS_CANCELED_BY_CLIENT;
                        handler.processUploadFinish(this);
                    }
                }
            }
        }
        catch(IOException ioex)
        {
            Status = FileUploadProcessor.ITEM_STATUS_CANCELED_BY_CLIENT;
            handler.processUploadFinish(this);
            System.out.println("read error: "+Helper.getStackTraceString(ioex));
        }
    }
    long total_sent = 0;
    public void writeStatus() throws IOException
    {
        if(LastSentBytesRead!=BytesRead)
        {
            byte[] br_buff = Helper.longToBytes(BytesRead);

            Output.write(br_buff);
            Output.write(Status);

            Output.flush();
            LastSentBytesRead = BytesRead;
        }
    }
    public String getParameter(String name)
    {
        return parameters.get(name);
    }
}

package ftwo.library.processors;

import com.sun.deploy.net.CrossDomainXML;
import ftwo.library.helper.Helper;
import ftwo.library.structure.FileDownloadProcessorItem;
import ftwo.library.structure.FileUploadProcessorItem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class FileTransferProcessor extends SeparateThreadProcessor
{
    public static int ITEM_STATUS_IS_UPLOADING = 1;
    public static int ITEM_STATUS_UPLOADED = 2;
    public static int ITEM_STATUS_CANCELED_BY_CLIENT = -1;
    public static int ITEM_STATUS_CANCELED_BY_SERVER = -2;

    private String Path;
    private ServerSocket UploadGateKeeper;
    private ServerSocket DownloadGateKeeper;
    private ServerSocket PolicyGateKeeper;
    private Integer StartID = 1;
    private HashMap<Integer,FileUploadProcessorItem> UploadItems = new HashMap<Integer, FileUploadProcessorItem>();
    private HashMap<Integer,FileDownloadProcessorItem> DownloadItems = new HashMap<Integer, FileDownloadProcessorItem>();

    private String CrossDomainFile = "<cross-domain-policy>" +
            "<site-control permitted-cross-domain-policies=\"all\"/>" +
            "<allow-access-from domain=\"*\" to-ports=\"*\" />" +
            "<allow-http-request-headers-from domain=\"*\" headers=\"*\" />" +
            "</cross-domain-policy>";

    private int BufferSize = 20000;

    private FileUploadProcessor FileUploadProc;
    private FileDownloadProcessor FileDownloadProc;

    public FileTransferProcessor(int upload_port,int download_port, String base_path,String allowFromDomains) throws IOException
    {
        super("File transfer processor");
        CrossDomainFile = CrossDomainFile.replace("<domains>",allowFromDomains);
        Path = base_path;
        UploadGateKeeper = new ServerSocket(upload_port);
        UploadGateKeeper.setSoTimeout(1);
        DownloadGateKeeper = new ServerSocket(download_port);
        DownloadGateKeeper.setSoTimeout(1);
        PolicyGateKeeper = new ServerSocket(843);
        PolicyGateKeeper.setSoTimeout(1);
        FileUploadProc = new FileUploadProcessor(UploadItems);
        FileDownloadProc = new FileDownloadProcessor(DownloadItems);
    }
    private void closeSockets()
    {
        try
        {
            UploadGateKeeper.close();
        }
        catch(Exception ex)
        {

        }
        try
        {
            DownloadGateKeeper.close();
        }
        catch(Exception ex)
        {

        }
        try
        {
            PolicyGateKeeper.close();
        }
        catch(Exception ex)
        {

        }
    }
    @Override
    public void start() throws InterruptedException
    {
        super.start();
        FileUploadProc.start();
        FileDownloadProc.start();
    }
    @Override
    public void stop() throws InterruptedException
    {
        System.out.println("Sockets are closed: ");
        closeSockets();
        System.out.print("File transfer processor: ");
        super.stop();
        System.out.print("File upload processor: ");
        FileUploadProc.stop();
        System.out.print("File download processor: ");
        FileDownloadProc.stop();
    }
    public void process()
    {
        try
        {
            acceptPolicy();
            acceptUpload();
            acceptDownload();
            Thread.sleep(10);
        }
        catch (Exception ex)
        {
            System.out.println("Error: "+ex.toString());
        }
    }
    private void acceptPolicy() throws IOException
    {
        try
        {
            Socket sock = PolicyGateKeeper.accept();
            System.out.println("Policy connected");
            InputStream in = sock.getInputStream();
            while(in.available()==0)
            {
                Thread.sleep(10);
            }
            byte[] req = new byte[in.available()];
            in.read(req);
            String request = new String(req,"UTF-8");
            OutputStream out = sock.getOutputStream();
            out.write(CrossDomainFile.getBytes("UTF-8"));
            out.flush();
            out.close();
        }
        catch(Exception ex)
        {
            //System.out.println("timeout");
        }
    }
    private void acceptUpload() throws IOException
    {
        try
        {
            Socket sock = UploadGateKeeper.accept();
            sock.setReceiveBufferSize(BufferSize);
            sock.setSendBufferSize(BufferSize);
            sock.setSoTimeout(1000);
            System.out.println("client connected");
            InputStream in_stream = sock.getInputStream();
            while(in_stream.available()==0)
            {
                Thread.sleep(10);
            }
            if(in_stream.available()==23)
            {
                // Policy file...
                byte[] req = new byte[in_stream.available()];
                in_stream.read(req);
                String request = new String(req,"UTF-8");
                OutputStream out = sock.getOutputStream();
                out.write(CrossDomainFile.getBytes("UTF-8"));
                out.flush();
                out.close();
                sock.close();
                System.out.println("policy done");
                return;
            }
            byte[] size_buff = new byte[8];
            in_stream.read(size_buff);
            // Size...
            long size = Helper.bytesToLong(size_buff);
            System.out.println("File size: " + size);
            byte[] name_size_buff = new byte[2];
            in_stream.read(name_size_buff);
            int name_size = Helper.bytesToInt(name_size_buff);
            byte[] name_buff = new byte[name_size];
            in_stream.read(name_buff);
            // Name...
            String name = new String(name_buff,"UTF-8");
            System.out.println("File name: " + name);

            byte[] param_size_buff = new byte[2];
            in_stream.read(param_size_buff);
            int param_size = Helper.bytesToInt(param_size_buff);
            byte[] param_buff = new byte[param_size];
            in_stream.read(param_buff);
            // Params...
            String params = new String(param_buff,"UTF-8");
            System.out.println("File params: " + params);
            FileUploadProcessorItem item = new FileUploadProcessorItem(sock,Path,size,name,params,StartID);
            UploadItems.put(StartID, item);
            StartID++;
        }
        catch (SocketTimeoutException toex)
        {
            //System.out.println("Time out");
        }
        catch (Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
        }
    }
    private void acceptDownload() throws IOException
    {
        try
        {
            Socket sock = DownloadGateKeeper.accept();
            sock.setSoTimeout(1000);
        }
        catch (Exception ex)
        {
            //System.out.println("timeout");
        }
    }
}

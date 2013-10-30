package racoonsoft.library.processors;

import racoonsoft.library.helper.Helper;
import racoonsoft.library.structure.FileDownloadProcessorItem;
import racoonsoft.library.structure.FileUploadProcessorItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class FileTransferProcessor extends SeparateThreadProcessor
{
    public static int ITEM_STATUS_IS_UPLOADING = 1;
    public static int ITEM_STATUS_UPLOADED = 2;
    public static int ITEM_STATUS_CANCELED_BY_CLIENT = -1;
    public static int ITEM_STATUS_CANCELED_BY_SERVER = -2;

    private FileTransferHandler handler;

    private String Path;
    private ServerSocket UploadGateKeeper;
    private ServerSocket DownloadGateKeeper;
    private ServerSocket PolicyGateKeeper;
    private Integer StartID = 1;
    private HashMap<Integer,FileUploadProcessorItem> UploadItems = new HashMap<Integer, FileUploadProcessorItem>();
    private HashMap<Integer,FileDownloadProcessorItem> DownloadItems = new HashMap<Integer, FileDownloadProcessorItem>();

    private int BufferSize = 20000;

    private FileUploadProcessor FileUploadProc;
    private FileDownloadProcessor FileDownloadProc;

    private String policy_xml = "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\"/>" +
            "<allow-http-request-headers-from domain=\"*\" headers=\"*\"/>" +
            "<site-control permitted-cross-domain-policies=\"all\"/>" +
            "</cross-domain-policy>";

    public FileTransferProcessor(int upload_port,int download_port, String base_path, FileTransferHandler handler) throws IOException
    {
        super("File transfer processor");
        this.handler = handler;
        Path = base_path;
        PolicyGateKeeper = new ServerSocket(843);
        PolicyGateKeeper.setSoTimeout(1);
        UploadGateKeeper = new ServerSocket(upload_port);
        UploadGateKeeper.setSoTimeout(1);
        DownloadGateKeeper = new ServerSocket(download_port);
        DownloadGateKeeper.setSoTimeout(1);
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
        super.stop();
        closeSockets();
        FileUploadProc.stop();
        FileDownloadProc.stop();
    }
    public void process()
    {
        try
        {
            acceptUpload();
            acceptDownload();
            acceptPolicy();
        }
        catch (Exception ex)
        {
            System.out.println("Error: "+ex.toString());
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
            FileUploadProcessorItem item = handler.processUploadStart(sock,Path,size,name,params,StartID);
            UploadItems.put(StartID, item);
            StartID++;
        }
        catch (Exception ex)
        {
            //System.out.println("timeout");
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
    private void acceptPolicy() throws IOException
    {
        try
        {
            Socket sock = PolicyGateKeeper.accept();
            Thread.sleep(1000);
            InputStream in = sock.getInputStream();
            byte[] buff = new byte[in.available()];
            in.read(buff);
            System.out.println(new String(buff,"UTF-8"));
            byte[] resp = policy_xml.getBytes("UTF-8");
            sock.getOutputStream().write(resp);
            sock.getOutputStream().flush();
            sock.getOutputStream().close();
            sock.setSoTimeout(1000);
        }
        catch (Exception ex)
        {
            //System.out.println("timeout");
        }
    }
}

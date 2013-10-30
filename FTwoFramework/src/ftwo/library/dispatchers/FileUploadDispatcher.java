package ftwo.library.dispatchers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

public class FileUploadDispatcher implements Runnable
{
    public static int STATUS_UPLOAD_IN_PROGRESS = 1;
    public static int STATUS_UPLOAD_FINISHED = 2;
    public static int STATUS_UPLOAD_CANCELED = 3;
    public static int STATUS_UPLOAD_PAUSED = 4;
    private static Integer FileIDSeed = 0;
    private int ChunkSize = 1024;

    private HashMap<String,FileUploadDispatcherItem> Files = new HashMap<String, FileUploadDispatcherItem>();
    private Thread t;
    private boolean IsRunning = false;

    public String startUpload(String file_path,String file_name,InputStream stream) throws IOException
    {
        FileUploadDispatcherItem item = new FileUploadDispatcherItem(file_name,file_path,stream);
        FileIDSeed++;
        Files.put(FileIDSeed.toString(),item);
        return FileIDSeed.toString();
    }
    public FileUploadDispatcherItem getFileItem(String id)
    {
        FileUploadDispatcherItem item = Files.get(id);
        return item;
    }
    public void start() throws InterruptedException
    {
        if(!IsRunning)
        {
            stop();
            t = new Thread(this,"FileUploadThread");
            IsRunning = true;
            t.start();
        }
    }
    public void stop() throws InterruptedException
    {
        if(IsRunning)
        {
            IsRunning = false;
            t.join();
        }
    }
    @Override

    public void run()
    {
        while(IsRunning)
        {
            try
            {
                Iterator<FileUploadDispatcherItem> iter = Files.values().iterator();
                while(iter.hasNext())
                {
                    iter.next().processUpload(ChunkSize);
                }
            }
            catch(Exception ex)
            {
                System.err.println(ex.toString());
            }
        }
    }
}

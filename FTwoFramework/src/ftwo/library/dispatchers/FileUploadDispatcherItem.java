package ftwo.library.dispatchers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class FileUploadDispatcherItem
{

    private String FileName;
    private String FilePath;
    private Long UploadStart;
    private Long UploadFinish;
    private int FileSize;
    private int Status;
    private InputStream InStream;
    private FileOutputStream FStream;
    private Exception AbortReason;
    private int UploadedBytes;
    public int status()
    {
        return Status;
    }
    public String fileName()
    {
        return FileName;
    }
    public String filePath()
    {
        return FilePath;
    }
    public Exception abortReason()
    {
        return AbortReason;
    }
    public int uploadedBytes()
    {
        return UploadedBytes;
    }
    public int size()
    {
        return FileSize;
    }
    public int uploadedPercent()
    {
        return 100*(UploadedBytes/FileSize);
    }
    public FileUploadDispatcherItem(String file_name,String file_path,InputStream stream) throws IOException
    {
        UploadedBytes = 0;
        FileName = file_name;
        FilePath = file_path;
        UploadStart = new Date().getTime();
        UploadFinish = null;
        InStream = stream;
        FileSize = InStream.available();
        File file = new File(FilePath);
        file.mkdirs();
        FStream = new FileOutputStream(FilePath + "/" + FileName);
        Status = FileUploadDispatcher.STATUS_UPLOAD_IN_PROGRESS;
    }
    public void pause()
    {
        Status = FileUploadDispatcher.STATUS_UPLOAD_PAUSED;
    }
    public void resume()
    {
        Status = FileUploadDispatcher.STATUS_UPLOAD_IN_PROGRESS;
    }
    private void finish() throws IOException
    {
        Status = FileUploadDispatcher.STATUS_UPLOAD_FINISHED;
        UploadFinish = new Date().getTime();
        InStream.close();
        FStream.flush();
        FStream.close();
    }
    public void cancel() throws IOException
    {
        Status = FileUploadDispatcher.STATUS_UPLOAD_CANCELED;
        InStream.close();
        FStream.close();
        File f = new File(FilePath + "/" + FileName);
        f.delete();
    }
    public int getUploadedBytes()
    {
        return UploadedBytes;
    }
    public void processUpload(int chunk_size) throws IOException
    {
        try
        {
            if(Status == FileUploadDispatcher.STATUS_UPLOAD_IN_PROGRESS)
            {
                int chunk_real_size = chunk_size;
                if(InStream.available()<chunk_real_size)
                {
                    chunk_real_size = InStream.available();
                }
                byte[] chunk = new byte[chunk_real_size];
                InStream.read(chunk);
                FStream.write(chunk);
                UploadedBytes+=chunk_real_size;
                if(InStream.available()<=0)// Done...
                {
                    finish();
                }
            }
        }
        catch(Exception ex)
        {
            AbortReason = ex;
            cancel();
        }
    }
}

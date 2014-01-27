package racoonsoft.languagebox.structure;

import racoonsoft.languagebox.service.UploadProcessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class UploadingFile
{
    private String name;
    private InputStream inputStream;
    private UploadingFileStatus status = UploadingFileStatus.IN_PROGRESS;
    private FileOutputStream file;
    private String type;
    private String extension;
    private Long size;
    private Long writtenSize;
    private Long folderId;


    public UploadingFile(String name,Long size,InputStream in,Long materialId,Long folderId) throws Exception
    {
        this.name = name;
        inputStream = in;
        File f = new File(UploadProcessor.uploadMaterialPath+materialId);
        file = new FileOutputStream(f,false);
        type = "custom"; //TODO
        extension = ""; // TODO
        this.size = size;
        writtenSize = 0l;
        this.folderId = folderId;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Integer getUploadPercent()
    {
        return (int)((writtenSize*1.0/size)*100.0);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public UploadingFileStatus getStatus() {
        return status;
    }

    public void setStatus(UploadingFileStatus status) {
        this.status = status;
    }

    public FileOutputStream getFile() {
        return file;
    }

    public void setFile(FileOutputStream file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getWrittenSize() {
        return writtenSize;
    }

    public void setWrittenSize(Long writtenSize) {
        this.writtenSize = writtenSize;
    }

    public synchronized void uploadPart(int maxSize) throws Exception
    {
        try
        {
            if(status==UploadingFileStatus.IN_PROGRESS)
            {
                byte[] buff = new byte[maxSize];
                if((size-writtenSize)<maxSize)
                {
                    buff = new byte[size.intValue()-writtenSize.intValue()];
                }
                inputStream.read(buff);
                file.write(buff);
                file.flush();
                writtenSize+=buff.length;
                if(writtenSize>=size)
                {
                    file.close();
                    file = null;
                    status = UploadingFileStatus.UPLOADED;
                }
            }
        }
        catch(Exception ex)
        {
            status = UploadingFileStatus.ABORTED;
        }
    }
}

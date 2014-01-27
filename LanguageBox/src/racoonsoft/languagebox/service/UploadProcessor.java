package racoonsoft.languagebox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.languagebox.structure.UploadingFile;
import racoonsoft.languagebox.structure.UploadingFileStatus;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.processors.SeparateThreadProcessor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class UploadProcessor extends SeparateThreadProcessor
{
    @Autowired
    protected PostgresqlDataSource dbProc;
    @Autowired
    protected LibraryService library;

    public static String uploadMaterialPath;

    private HashMap<Long,ArrayList<UploadingFile>> userUploadingFiles = new HashMap<Long, ArrayList<UploadingFile>>();

    public UploadProcessor()
    {
        super("File upload processor");

    }
    public UploadProcessor(String materialsPath)
    {
        super("File upload processor");
        uploadMaterialPath = materialsPath;
    }

    public void addFileToUpload(Long userId, Long folderId, CommonsMultipartFile multipartFile) throws Exception
    {
        if(!isRunning())
        {
            start();
        }
        Long size = multipartFile.getSize();
        if(size>0)
        {
            InputStream in = multipartFile.getInputStream();
            String name = multipartFile.getFileItem().getName();
            Long id = library.addMaterial(userId,folderId,name,size);

            UploadingFile file = new UploadingFile(name,size,in,id,folderId);
            ArrayList<UploadingFile> files = userUploadingFiles.get(userId);
            if(files == null)
            {
                files = new ArrayList<UploadingFile>();
            }
            files.add(file);
            userUploadingFiles.put(userId,files);
            // TODO: add clear old (already uploaded or dead) files from userUploadingFiles
        }
    }
    public void addFilesToUpload(Long userId, Long folderId, CommonsMultipartFile[] multipartFiles) throws Exception
    {
        for(int i=0; i<multipartFiles.length; i++)
        {
            addFileToUpload(userId,folderId,multipartFiles[i]);
        }
    }

    @Override
    public void process() throws Exception
    {
        Iterator<ArrayList<UploadingFile>> iterator = userUploadingFiles.values().iterator();
        while(iterator.hasNext())
        {
            ArrayList<UploadingFile> uploadingFiles = iterator.next();
            for(int i=0; i<uploadingFiles.size(); i++)
            {
                uploadingFiles.get(i).uploadPart(402048);
            }
        }
        Thread.sleep(1);
    }

    public DBRecord getFolderUploadInfo(Long userId, Long folderId)
    {
        DBRecord rec = new DBRecord();
        rec.setValue("user_id",userId);
        rec.setValue("folder_id",folderId);
        ArrayList<DBRecord> items = new ArrayList<DBRecord>();
        ArrayList<UploadingFile> files = userUploadingFiles.get(userId);
        Long totalSize = 0l;
        Long totalWrittenSize = 0l;
        Integer totalProgress = 0;
        boolean isUploading = false;
        if(files!=null)
        {
            for(UploadingFile file: files)
            {
                if(folderId==null||folderId == file.getFolderId())
                {
                    if(file.getStatus()== UploadingFileStatus.IN_PROGRESS)
                    {
                        DBRecord fileInfo = new DBRecord();
                        fileInfo.setValue("name",file.getName());
                        fileInfo.setValue("size", file.getSize());
                        fileInfo.setValue("written",file.getWrittenSize());
                        fileInfo.setValue("progress",file.getUploadPercent());
                        totalSize+=file.getSize();
                        totalWrittenSize+=file.getWrittenSize();
                        items.add(fileInfo);
                        isUploading = true;
                    }
                }
            }
        }
        totalProgress = (int)((totalWrittenSize*1.0/totalSize)*100.0);
        rec.setValue("progress",totalProgress);
        rec.setValue("is_uploading",isUploading);
        rec.setValue("files",items);
        return rec;
    }


}

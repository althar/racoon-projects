package racoonsoft.languagebox.service;

import org.springframework.stereotype.Service;
import racoonsoft.library.database.DBRecord;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class LibraryService extends LanguageBoxService
{
    public void addFolder(Long folderId,String folderName,Long userId) throws Exception
    {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("parent_id",folderId);
        params.put("user_id",userId);
        params.put("name",folderName);
        dbProc.executeInsert("library",params);
    }

    public DBRecord getFolder(Long folderId, Long userId) throws Exception
    {
        DBRecord folder = null;
        if(folderId==null)
        {
            folder = dbProc.getRecord("SELECT * FROM library WHERE parent_id IS NULL AND user_id="+userId);
            if(folder==null)
            {
                HashMap<String, Object> insertParams = new HashMap<String, Object>();
                insertParams.put("user_id",userId);
                insertParams.put("name","Библиотека");
                dbProc.executeInsert("library",insertParams);
                folder = dbProc.getRecord("SELECT * FROM library WHERE parent_id IS NULL AND user_id="+userId);
            }
        }
        else
        {
            folder = dbProc.getRecord("SELECT * FROM library WHERE id="+folderId+" AND user_id="+userId);
        }
        if(folder==null)
        {
            return null;
        }
        ArrayList<DBRecord> materials = dbProc.getRecords("SELECT * FROM material WHERE library_id="+folder.getID());
        ArrayList<DBRecord> folders = dbProc.getRecords("SELECT * FROM library WHERE parent_id="+folder.getID());
        String condition = " id IS NULL ";
        if(folder.getID()!=null)
        {
            condition = "id="+folder.getID();
        }
        ArrayList<DBRecord> path = dbProc.getRecords("WITH RECURSIVE libr AS (SELECT id,parent_id,name FROM library WHERE "
                +condition
                +" UNION SELECT lib1.id,lib1.parent_id,lib1.name FROM library lib1, libr libr_rec  WHERE libr_rec.parent_id=lib1.id) SELECT id,name FROM libr ORDER BY id");
        folder.setValue("materials",materials);
        folder.setValue("path",path);
        folder.setValue("folders",folders);
        return folder;
    }

    public Long addMaterial(Long userId,Long folderId,String name,Long size) throws Exception
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("library_id",folderId);
        pars.put("name",name);
        pars.put("size",size);
        return dbProc.executeInsert("material",pars);
    }

    public void delete(String type,Long id,Long userId) throws Exception
    {
        if(type.equalsIgnoreCase("folder"))
        {
            dbProc.executeNonQuery("DELETE FROM library WHERE id="+id+" AND user_id="+userId);
        }
        else if(type.equalsIgnoreCase("material"))
        {
            dbProc.executeNonQuery("DELETE FROM material WHERE id="+id+" AND library_id IN (SELECT id FROM library WHERE user_id="+userId+")");
        }
    }
    public void rename(String type,Long id,Long userId,String name) throws Exception
    {
        if(type.equalsIgnoreCase("folder"))
        {
            dbProc.executeNonQuery("UPDATE library SET name='"+name.replace("'","`")+"' WHERE id="+id+" AND user_id="+userId);
        }
        else if(type.equalsIgnoreCase("material"))
        {
            dbProc.executeNonQuery("UPDATE material SET name='"+name.replace("'","`")+"' WHERE id="+id+" AND library_id IN (SELECT id FROM library WHERE user_id="+userId+")");
        }
    }
    public DBRecord getMaterial(Long userId,Long id) throws Exception
    {
        DBRecord rec = dbProc.getRecord("SELECT * FROM material WHERE id=" + id + " AND library_id IN (SELECT id FROM library WHERE user_id=" + userId + ")");
        return rec;
    }
    public BufferedInputStream getMaterialStream(Long id) throws Exception
    {
        DBRecord rec = dbProc.getRecord("SELECT id FROM material WHERE id=" + id);
        if(rec!=null)
        {
            return new BufferedInputStream(new FileInputStream(UploadProcessor.uploadMaterialPath+rec.getID()));
        }
        return null;
    }
}

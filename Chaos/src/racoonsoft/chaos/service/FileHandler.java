package racoonsoft.chaos.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import racoonsoft.chaos.settings.PGSQLDataSource;
import racoonsoft.library.processors.FileTransferHandler;
import racoonsoft.library.structure.FileUploadProcessorItem;

import java.net.Socket;
import java.util.HashMap;

@Repository
public class FileHandler implements FileTransferHandler
{
    @Autowired
    public static PGSQLDataSource dataSource;

    @Override
    public FileUploadProcessorItem processUploadStart(Socket s, String path, long size, String name, String parameters, Integer id)
    {
        try
        {
            FileUploadProcessorItem item = new FileUploadProcessorItem(s,path,id.toString()+name, size,name,parameters,id,this);
            String book_id = item.getParameter("book_id");
            HashMap<String,Object> pars = new HashMap<String, Object>();
            pars.put("book_path",item.getPath());
            dataSource.executeUpdate("book",pars,"id="+book_id.replace("'",""));
            return item;
        }
        catch (Exception ex)
        {
            return null;
        }
    }
    @Override
    public Object processUploadFinish(FileUploadProcessorItem item)
    {
        try
        {
            String book_id = item.getParameter("book_id");
            System.out.println("File uploaded: "+book_id);
        }
        catch (Exception ex)
        {
            return null;
        }
        return null;
    }
}

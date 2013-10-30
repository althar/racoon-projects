package racoonsoft.chaos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import racoonsoft.chaos.settings.PGSQLDataSource;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.processors.SeparateThreadProcessor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.SQLException;

@Repository
public class ImageDao extends SeparateThreadProcessor
{
    public static String IMAGES_PATH = null;
    @Autowired
    private PGSQLDataSource dataSource;

    public ImageDao()
    {
        super("image_saver");
    }
    public ImageDao(@Value("image_saver") String processor_name) {
        super(processor_name);
    }
    public void setImagePath(String path)
    {
        if(IMAGES_PATH==null)
        {
            IMAGES_PATH = path;
        }
    }
    @Override
    public void process()
    {
        try
        {
            downloadImages();
            Thread.currentThread().sleep(100);
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
    }

    public void downloadImages() throws Exception
    {
        Long id = 0l;
        try
        {
            DBRecord image = dataSource.getRecord("SELECT * FROM image WHERE downloaded=FALSE LIMIT 1");
            if(image!=null)
            {
                String image_url = image.getStringValue("unescaped_url");
                id = image.getLongValue("id");

                URL url = null;
                URLConnection con = null;
                url = new URL(image_url);
                con = url.openConnection();
                int i;
                BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
                File f = new File(IMAGES_PATH+"\\materials");
                f.mkdirs();
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f.getAbsolutePath()+"\\"+String.valueOf(id)+".jpg"));
                while ((i = bis.read()) != -1) {
                    bos.write(i);
                }

                bos.flush();
                bos.close();
                bis.close();
                dataSource.executeNonQuery("UPDATE image SET downloaded = TRUE WHERE id="+id);
            }
        }
        catch(Exception ex)
        {
            dataSource.executeNonQuery("DELETE FROM image WHERE id="+id);
        }

    }
}

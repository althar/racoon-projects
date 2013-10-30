package racoonsoft.library.processors;

import racoonsoft.library.structure.FileUploadProcessorItem;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class FileUploadProcessor extends SeparateThreadProcessor
{
    public static int ITEM_STATUS_IS_UPLOADING = 1;
    public static int ITEM_STATUS_UPLOADED = 2;
    public static int ITEM_STATUS_CANCELED_BY_CLIENT = -1;
    public static int ITEM_STATUS_CANCELED_BY_SERVER = -2;

    private HashMap<Integer,FileUploadProcessorItem> Items = new HashMap<Integer, FileUploadProcessorItem>();

    public FileUploadProcessor(HashMap<Integer,FileUploadProcessorItem> items) throws IOException
    {
        super("File upload processor");
        Items = items;
    }
    @Override
    public void process()
    {
        try
        {
            performReadCircle();
        }
        catch (Exception ex)
        {
            //System.out.println("Error: "+Helper.getStackTraceString(ex));
        }

    }
    public void performReadCircle()
    {
        Iterator<Integer> iter = Items.keySet().iterator();
        while(iter.hasNext())
        {
            Integer key = iter.next();
            FileUploadProcessorItem item = Items.get(key);
            if(item.getStatus()==ITEM_STATUS_IS_UPLOADING)
            {
                item.read();    
            }
            else 
            {
                Items.remove(key);
            }
            
        }
    }
    public FileUploadProcessorItem get(Long id)
    {
        return Items.get(id);
    }
}

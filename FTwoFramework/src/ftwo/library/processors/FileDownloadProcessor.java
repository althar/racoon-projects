package ftwo.library.processors;

import ftwo.library.structure.FileDownloadProcessorItem;

import java.util.HashMap;

public class FileDownloadProcessor extends SeparateThreadProcessor
{
    private HashMap<Integer,FileDownloadProcessorItem> Items = new HashMap<Integer, FileDownloadProcessorItem>();

    public FileDownloadProcessor(HashMap<Integer,FileDownloadProcessorItem> items)
    {
        super("File download processor");
        Items = items;
    }
    @Override
    public void process()
    {
        try
        {
            //System.out.println("!");
            Thread.sleep(500);
        }
        catch (Exception ex)
        {
            //System.out.println("Error: "+Helper.getStackTraceString(ex));
        }
    }
}

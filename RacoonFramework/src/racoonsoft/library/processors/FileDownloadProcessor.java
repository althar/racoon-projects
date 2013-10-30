package racoonsoft.library.processors;

import racoonsoft.library.structure.FileDownloadProcessorItem;

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

    }
}

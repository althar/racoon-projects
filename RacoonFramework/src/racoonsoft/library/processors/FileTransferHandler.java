package racoonsoft.library.processors;

import racoonsoft.library.structure.FileUploadProcessorItem;

import java.net.Socket;

public interface FileTransferHandler
{
    public FileUploadProcessorItem processUploadStart(Socket s,String path, long size, String name, String parameters, Integer id);
    public Object processUploadFinish(FileUploadProcessorItem item);
}

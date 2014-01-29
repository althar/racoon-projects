package racoonsoft.languagebox.structure;

import java.util.HashMap;
import java.util.Iterator;

public class FileTransferProgress
{
    private HashMap<Long,Long> bytesTotal = new HashMap<Long, Long>();
    private HashMap<Long,Long> bytesTransferred = new HashMap<Long, Long>();

    public synchronized void setProgress(Long transferId, Long total, Long transferred)
    {
        bytesTotal.put(transferId,total);
        bytesTransferred.put(transferId,transferred);
    }
    public synchronized  Integer getProgress()
    {
        Long total = 0l;
        Long transferred = 0l;
        Integer result = 10000;
        Iterator<Long> transferIdIter = bytesTotal.keySet().iterator();
        while(transferIdIter.hasNext())
        {
            Long transId = transferIdIter.next();
            Long transThis = bytesTransferred.get(transId);
            Long totalThis = bytesTotal.get(transId);
            if(transThis.longValue()!=totalThis.longValue())
            {
                Integer currentResult = (int)(transThis*10000.0/totalThis);
                if(currentResult<result)
                {
                    result = currentResult;
                }
            }
        }

        return result;
    }
}

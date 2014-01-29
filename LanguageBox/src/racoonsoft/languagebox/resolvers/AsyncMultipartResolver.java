package racoonsoft.languagebox.resolvers;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import racoonsoft.languagebox.service.UploadProcessor;
import racoonsoft.library.access.UserProcessor;

import javax.servlet.http.HttpServletRequest;

public class AsyncMultipartResolver extends CommonsMultipartResolver
{

    private static Long transferId = 0l;

    private void initProgressProcessor(HttpServletRequest request) {
    }

    private void clearProgressProcessor(HttpServletRequest request) {
    }

    private void processProgress(long bytesRead, long bytesTotal,
                                 HttpServletRequest request) {

        Long transId = (Long)request.getAttribute("transfer_id");
        //System.out.println(" ========= ID : "+transId);
        if(transId==null)
        {
            transferId++;
            System.out.println(" trId: "+transferId);

            request.setAttribute("transfer_id", transferId);
            transId = transferId;
        }
        String userId = UserProcessor.getCookie(request,"u_id");

        if(userId!=null&&!userId.equalsIgnoreCase(""))
        {
            Long userIdLong = Long.valueOf(userId);
            UploadProcessor.setFileTransfer(userIdLong, transId, bytesTotal, bytesRead);
//            System.out.println("Read: "+UploadProcessor.getTransferProgress(userIdLong)+" % ");
        }
    }

    protected MultipartParsingResult parseRequest(
            final HttpServletRequest request)
            throws MultipartException
    {
        String encoding = determineEncoding(request);
        ServletFileUpload fileUpload = (ServletFileUpload)prepareFileUpload(encoding);

        initProgressProcessor(request);
        fileUpload.setProgressListener(new ProgressListener() {
            @Override
            public void update(
                    long pBytesRead, long pContentLength, int pItems) {
                processProgress(pBytesRead, pContentLength, request);
            }
        });

        MultipartParsingResult result = null;
        try {
            result = parseFileItems(
                    fileUpload.parseRequest(request),
                    encoding);

        } catch (FileUploadBase.SizeLimitExceededException ex) {
            throw new MaxUploadSizeExceededException(
                    fileUpload.getSizeMax(), ex);

        } catch (FileUploadException ex) {
            throw new MultipartException("Could not parse " +
                    "multipart servlet request", ex);
        }

        clearProgressProcessor(request);
        return result;
    }
}

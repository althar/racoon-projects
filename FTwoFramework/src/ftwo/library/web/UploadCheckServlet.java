package ftwo.library.web;

import ftwo.library.dispatchers.FileUploadDispatcher;
import ftwo.library.dispatchers.FileUploadDispatcherItem;
import ftwo.library.xml.XMLProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet(name = "UploadCheckServlet")
@MultipartConfig
public class UploadCheckServlet extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        try
        {
            if(cmd.equalsIgnoreCase("check_file"))
            {
                String id = request.getParameter("id");
                FileUploadDispatcherItem item = UploadServlet.fileUploadDispatcher().getFileItem(id);
                int status = item.status();
                int bytes = item.getUploadedBytes();
                int size = item.size();
                int percent = item.uploadedPercent();
                XMLProcessor proc = new XMLProcessor();
                proc.addNode("root", "code",BaseServlet.REQUEST_PROCESSED_SUCCESSFULLY);
                proc.addNode("root", "status",status);
                proc.addNode("root", "bytes_uploaded",bytes);
                proc.addNode("root", "size",size);
                proc.addNode("root", "percent_uploaded",percent);
                out.print(proc.toXMLString());
            }

        }
        catch (Exception ex)
        {
            out.print("");
        }
        finally
        {
            out.close();
        }
    }
    private String getFileName(Part part)
    {
        String partHeader = part.getHeader("content-disposition");
        for (String cd : part.getHeader("content-disposition").split(";"))
        {
            if (cd.trim().startsWith("filename"))
            {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}

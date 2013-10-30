package racoonsoft.library.web;


import racoonsoft.library.dispatchers.FileUploadDispatcher;
import racoonsoft.library.xml.XMLProcessor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet
{
    public UploadServlet()
    {
        super();
    }

    private static FileUploadDispatcher UploadDispatcher;

    public void startUpload(HttpServletRequest request) throws IOException, ServletException
    {
        for (Part part : request.getParts())
        {
            InputStream is = request.getPart(part.getName()).getInputStream();
            int i = is.available();
            byte[] b = new byte[i];
            is.read(b);
            String fileName = getFileName(part);
            FileOutputStream os = new FileOutputStream("c:/temp/" + fileName);
            os.write(b);
            os.close();
            is.close();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            checkFileUploadDispatcher();
            XMLProcessor proc = new XMLProcessor();
            proc.addNode("root", "code", BaseServlet.REQUEST_PROCESSED_SUCCESSFULLY);
            int index = 1;
            String path_prefix = request.getServletContext().getRealPath("");
            String path_postfix = request.getParameter("file_path");
            String file_name = request.getParameter("file_name");
            if (path_postfix == null)
            {
                path_postfix = "";
            }
            for (Part part : request.getParts())
            {
                if (part.getContentType() != null)
                {
                    String ct = part.getContentType();
                    InputStream is = part.getInputStream();
                    if (file_name == null) {
                        file_name = getFileName(part);
                    }
                    String id = UploadDispatcher.startUpload(path_prefix + path_postfix, file_name, is);
                    proc.addNode("root", "item_" + index);
                    proc.addNode("root.item_" + index, "name", part.getName());
                    proc.addNode("root.item_" + index, "file_name", file_name);
                    proc.addNode("root.item_" + index, "id", id);
                    proc.addNode("root.item_" + index, "content_type", ct);
                    index++;
                }
            }
            out.print(proc.toXMLString());
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    public static FileUploadDispatcher fileUploadDispatcher()
    {
        return UploadDispatcher;
    }

    public static void checkFileUploadDispatcher() throws InterruptedException
    {
        if (UploadDispatcher == null)
        {
            UploadDispatcher = new FileUploadDispatcher();
            UploadDispatcher.start();
        }
    }
}

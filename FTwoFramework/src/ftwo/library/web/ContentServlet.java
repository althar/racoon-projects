package ftwo.library.web;

import ftwo.library.database.DBTable;
import ftwo.library.xml.XMLProcessor;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public abstract class ContentServlet extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        printSome();
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws java.io.IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws java.io.IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    public static String getContentValue(String content_name) throws SQLException
    {
        return BaseServlet.dbProc().getRecord("content", "name='"+content_name.replace("'", ""), null).getStringValue("value");
    }
    public static XMLProcessor getContentXML(HttpServletRequest request) throws IOException,ParserConfigurationException,SAXException,SQLException
    {
        XMLProcessor proc = new XMLProcessor();
        String content = request.getParameter("content");
        System.out.println("content: "+content);
        if(content==null)
        {
            System.out.println("db: "+BaseServlet.dbProc());
            proc.addNode("root", "data",BaseServlet.dbProc().getFullTable("content"));
            return proc;
        }
        String[] contents = content.split("\\|");
        System.out.println("contents: "+contents);
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<contents.length; i++)
        {
            builder.append(" name='");
            builder.append(contents[i].replace("'", ""));
            builder.append("'");
            if(i<contents.length-1)
            {
                builder.append(" OR ");
            }
        }
        System.out.println("builder.toString(): "+builder.toString());
        DBTable dbt = BaseServlet.dbProc().getTable("content",builder.toString(),null);

        System.out.println("dbt: "+dbt);
        proc.addNode("root", "data",dbt);
        return proc;
    }
    public static void printSome()
    {
        System.out.println("!!!");
    }
    public DBTable getContentTable(HttpServletRequest request) throws SQLException
    {
        String content = request.getParameter("content");
        if(content==null)
        {
            return BaseServlet.dbProc().getFullTable("content");
        }
        String[] contents = request.getParameter("content").split("\\|");
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<contents.length; i++)
        {
            builder.append(" name='");
            builder.append(contents[i].replace("'", ""));
            builder.append("'");
            if(i<contents.length-1)
            {
                builder.append(" OR ");
            }
        }
        DBTable dbt = BaseServlet.dbProc().getTable("content",builder.toString(),null);
        return dbt;
    }
}

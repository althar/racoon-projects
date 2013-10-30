package ftwo.zoomag.servlets;

import ftwo.library.database.DBTable;
import ftwo.library.xml.XMLProcessor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContentServlet extends ftwo.library.web.ContentServlet
{
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        try
        {
            if(cmd == null)
            {

            }
            else if(cmd.equalsIgnoreCase("get_banner"))
            {
                String value = BaseServlet.dbProc().getRecord("SELECT * FROM content WHERE name='banner'").getStringValue("value");
                out.print(BaseServlet.generateResponseXML(BaseServlet.REQUEST_PROCESSED_SUCCESSFULLY, cmd, "banner", value));
                out.close();
                return;
            }
            else if(cmd.equalsIgnoreCase("get_content"))
            {
                XMLProcessor proc = getContentXML(request);
                String[] names = request.getParameter("content").split("\\|");
                String[] values = request.getParameter("content_values").split("\\|");
                BaseServlet.dbProc().initContent(names,values);
                //DBTable cont = BaseServlet.dbProc().getContent(names);
                proc.addNode("root","code",BaseServlet.REQUEST_PROCESSED_SUCCESSFULLY);
               // proc.addNode("root","data",cont);
                out.print(proc.toXMLString());
                out.close();
                return;
            }
        }
        catch (Exception ex)
        {
            out.print(BaseServlet.generateResponseExceptionXML(BaseServlet.BASE_UNKNOWN_COMMAND, cmd, ex));
        }
        finally
        {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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

}

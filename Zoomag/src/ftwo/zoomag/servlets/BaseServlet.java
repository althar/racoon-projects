package ftwo.zoomag.servlets;

import ftwo.library.access.SessionProcessor;
import ftwo.library.logging.Logger;
import ftwo.library.mail.MailProcessor;
import ftwo.library.sms.SMSProcessor;
import ftwo.zoomag.database.DatabaseProcessor;
import ftwo.zoomag.service.OldSiteIntegrator;
import ftwo.zoomag.structure.Warehouse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends ftwo.library.web.BaseServlet
{

    private static boolean IsStarted = false;
    private static MailProcessor MailProc;
    public static OldSiteIntegrator siteIntegrator;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	    String cmd = request.getParameter("cmd");
        try
	    {
            start();
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "index.html");
        }
	    catch (Exception ex)
        {
            out.print(ex.toString());
            out.close();
            return;
        }
        finally
        {
                out.close();
        }
    }

    @Override
    public void setUp() throws ClassNotFoundException,SQLException,NumberFormatException,InterruptedException
    {
        if(dbProc()!=null)
        {
            dbProc().checkConnection();
        }
        if(!IsStarted)
        {
            System.out.println("Starting application...");
            SessionProc = new SessionProcessor();
            String db_host = getParameter("db_host");
            String db_name = getParameter("db_name");
            Integer db_port = Integer.valueOf(getParameter("db_port"));
            String db_login = getParameter("db_login");
            String db_password = getParameter("db_password");
            String db_driver_class = getParameter("db_driver_class");
            String db_connection_prefix = getParameter("db_connection_prefix");
            DBProc = new DatabaseProcessor(db_host, db_name, db_port, db_login, db_password, db_driver_class, db_connection_prefix);
            DBProc.connect();
            DBProc.loadSettings();
            siteIntegrator = new OldSiteIntegrator("site integrator");
            Warehouse.load();
            //MailProc = new MailProcessor();

            //siteIntegrator.start();
            SmsProc = new SMSProcessor();
            MailProc = new MailProcessor();
            MailProc.start();

            Log = new Logger();
            Log.setLogLevel(Level.ALL);
            Log.addFileLogger(getParameter("log_path"));
            IsStarted = true;
        }
    }
    public static DatabaseProcessor dbProc()
    {
        return (DatabaseProcessor)DBProc;
    }
    public static MailProcessor mailProc()
    {
        return MailProc;
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

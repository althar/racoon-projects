package racoonsoft.wish.servlets;

import racoonsoft.library.access.SessionProcessor;
import racoonsoft.library.logging.Logger;
import racoonsoft.wish.database.DBProcessor;
import racoonsoft.library.ozon.OzonProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

public class BaseServlet extends racoonsoft.library.web.BaseServlet
{
    private static boolean IsStarted = false;
    private static OzonProcessor OzonProc;
    private static SessionProcessor SessionProc;


    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            setUp();
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "/Facebook?cmd=facebook-login");
        }
        catch (Exception ex)
        {

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doRequest(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doRequest(request,response);
    }
    @Override
    public void setUp() throws ClassNotFoundException,SQLException,NumberFormatException
    {
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
            DBProc = new DBProcessor(db_host, db_name, db_port, db_login, db_password, db_driver_class, db_connection_prefix);
            DBProc.connect();
            DBProc.loadSettings();
            checkTables();
            OzonProc = new OzonProcessor();
            Log = new Logger();
            Log.setLogLevel(Level.ALL);
            Log.addFileLogger(getParameter("log_path"));
            IsStarted = true;
        }
    }
    public OzonProcessor ozonProc()
    {
        return OzonProc;
    }
    public static SessionProcessor sessProc()
    {
        return SessionProc;
    }
}

package ftwo.library.web;

import ftwo.library.access.Session;
import ftwo.library.access.SessionProcessor;
import ftwo.library.access.User;
import ftwo.library.database.DBProcessor;
import ftwo.library.database.DBTable;
import ftwo.library.exceptions.SessionCreationException;
import ftwo.library.helper.Helper;
import ftwo.library.logging.Logger;
import ftwo.library.settings.Settings;
import ftwo.library.sms.SMSProcessor;
import ftwo.library.xml.XMLProcessor;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public abstract class BaseServlet extends HttpServlet implements Runnable
{
    private Thread t;
    private boolean IsRunning = false;

    public static Integer BASE_UNKNOWN_COMMAND = -999000;
    public static Integer BASE_INIT_FAILED = -666000;
    public static Integer BASE_ADMIN_AUTH_FAILED = -333000;
    public static Integer BASE_PASSWORD_RECOVER_FAILED = -444000;
    public static Integer BASE_COMMAND_UNDEFINED = -111000;
    public static Integer BASE_RESPONSE_GENERATION_FAILED = -909090;
    public static Integer BASE_USER_UNAUTHENTICATED = -123456;
    public static Integer AUTHORIZATION_FAILED = -4;

    public static Integer REQUEST_PROCESSED_SUCCESSFULLY = 100000;

    public static String Info;
    public static SessionProcessor SessionProc;
    public static DBProcessor DBProc;
    public static SMSProcessor SmsProc;
    public static Logger Log;

    public void start()
    {
        if(!IsRunning)
        {
            IsRunning = true;
            t = new Thread(this,"Base servlet worker");
            t.start();
        }
    }
    public void stop() throws InterruptedException
    {
        if(IsRunning)
        {
            IsRunning = false;
            t.join();
        }
    }
    public void run()
    {
        while(IsRunning)
        {
            try
            {
                setUp();
                Thread.currentThread().sleep(10000);
            }
            catch(Exception ex)
            {
                System.out.println("Error: "+Helper.getStackTraceString(ex));
                try
                {
                    Thread.currentThread().sleep(10000);
                }
                catch(Exception eex)
                {

                }
            }
        }
    }
    public abstract void setUp() throws Exception;
    public static String generateResponseXML(int result_code,String command) throws IOException,ParserConfigurationException,SAXException
    {
        XMLProcessor proc = new XMLProcessor();
        proc.addNode("root", "code",result_code);
        proc.addNode("root", "method",command);
        String xmlString = proc.toXMLString();
        return xmlString;
    }
    public static String generateResponseXML(int result_code,String command,HashMap<String,Object> data) throws IOException,ParserConfigurationException,SAXException
    {
        XMLProcessor proc = new XMLProcessor();
        proc.addNode("root", "code",result_code);
        proc.addNode("root", "method",command);
        proc.addNode("root","data",data);
        String xmlString = proc.toXMLString();
        return xmlString;
    }
    public static String generateResponseXML(int result_code,String command,String name,Object value) throws IOException,ParserConfigurationException,SAXException
    {
        XMLProcessor proc = new XMLProcessor();
        proc.addNode("root", "code",result_code);
        proc.addNode("root", "method",command);
        proc.addNode("root","data");
        proc.addNode("root.data", name,value);
        String xmlString = proc.toXMLString();
        return xmlString;
    }
    public static String generateResponseExceptionXML(int result_code,String command,Exception ex)
    {
        try
        {
            XMLProcessor proc = new XMLProcessor();
            proc.addNode("root", "code",result_code);
            proc.addNode("root", "method",command);
            proc.addNode("root","data");
            proc.addNode("root.data", "error_code",Helper.getStackTraceString(ex));
            String xmlString = proc.toXMLString();
            return xmlString;
        }
        catch(Exception exc)
        {
            return "";
        }
    }
    public String getParameter(String name)
    {
        return getServletContext().getInitParameter(name);
    }
    public boolean authAdmin(HttpServletRequest req)
    {
        String adminLogin = req.getParameter("admin_login");
        String adminPass = req.getParameter("admin_password");
        String admin_login = getParameter("admin_login");
        String admin_password = getParameter("admin_password");
        return admin_login.equalsIgnoreCase(adminLogin)&&admin_password.equalsIgnoreCase(adminPass);
    }
    public static DBProcessor dbProc()
    {
        return DBProc;
    }
    public static SessionProcessor sessProc()
    {
        return SessionProc;
    }
    public static SMSProcessor smsProc()
    {
        return SmsProc;
    }
    public static Logger log()
    {
        return Log;
    }
    public static void checkTables() throws SQLException
    {
        DBTable settings = dbProc().getTable("SELECT relname FROM pg_class WHERE relname='settings'");
        DBTable content = dbProc().getTable("SELECT relname FROM pg_class WHERE relname='content'");
        DBTable session_seq = dbProc().getTable("SELECT relname FROM pg_class WHERE relname='session_id_seq'");
        if(settings==null||settings.size()==0)
        {
            dbProc().executeNonQuery("CREATE TABLE public.settings (id SERIAL, name VARCHAR NOT NULL, value VARCHAR NOT NULL, CONSTRAINT settings_pkey PRIMARY KEY(id)) WITH OIDS;");
        }
        if(content==null||content.size()==0)
        {
            dbProc().executeNonQuery("CREATE TABLE public.content (id SERIAL, name VARCHAR NOT NULL, value VARCHAR NOT NULL, CONSTRAINT content_pkey PRIMARY KEY(id)) WITH OIDS;");
        }
        if(session_seq==null||session_seq.size()==0)
        {
            dbProc().executeNonQuery("CREATE SEQUENCE public.session_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 99999999999 START 1 CACHE 1");
        }
    }
    public static void printParameters(HttpServletRequest req)
    {
        Enumeration<String> namesIter = req.getParameterNames();
        System.out.println(" ------------- Parameters ---------------");
        while(namesIter.hasMoreElements())
        {
            String key = namesIter.nextElement();
            String value = req.getParameter(key);
            System.out.print(key+" = ");
            System.out.println(value);

        }
        System.out.println(" -----------------------------------------");
    }
    
}

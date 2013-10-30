package ftwo.zoomag.servlets;

import ftwo.library.database.DBRecord;
import ftwo.library.mail.MailMessage;
import ftwo.library.settings.Settings;
import ftwo.library.sms.SMSMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends BaseServlet
{
    public static Integer PARAMETER_CHECK_SUCCESSFUL = 0;
    public static Integer REGISTRATION_SUCCESSFUL = 1;
    public static Integer REGISTRATION_ALTER_SUCCESSFUL = 2;

    public static Integer REGISTRATION_INCORRECT_LOGIN = -1;
    public static Integer REGISTRATION_INCORRECT_PASSWORD = -2;
    public static Integer REGISTRATION_LOGIN_ALREADY_USED = -3;
    public static Integer REGISTRATION_FAILED = -4;
    public static Integer PARAMETER_CHECK_FAILED = -5;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        try
        {
            start();
            if(cmd == null)
            {
                out.print(generateResponseXML(BASE_COMMAND_UNDEFINED, cmd, null));
            }
            else if(cmd.equalsIgnoreCase("create_registration"))
            {
                Integer res = checkParameters(request);
                if(res == PARAMETER_CHECK_SUCCESSFUL)
                {
                    HashMap<String, Object> pars = new HashMap<String, Object>();
                    pars.put("phone_1", request.getParameter("login"));
                    pars.put("password", request.getParameter("password"));
                    pars.put("email", request.getParameter("email"));
                    pars.put("site_id", getParameter("site_id"));
                    if(DBProc.registerUser(pars) != null)
                    {
                        HashMap<String, Object> email_pars = new HashMap<String, Object>();
                        email_pars.put("login", request.getParameter("login"));
                        email_pars.put("password", request.getParameter("password"));
                        MailMessage mess = new MailMessage("zoomag", request.getParameter("email"), "Вы успешно зарегистрированы", Settings.getStringSetting("registration_email"), email_pars);
                        SMSMessage sms = new SMSMessage("8" + request.getParameter("login").replace("(", "").replace(")", "").replace("-", ""), Settings.getStringSetting("registration_sms"), "zoomag");
                        mailProc().sendMail(mess);
                        smsProc().sendSMS(sms);
                        out.print(generateResponseXML(REGISTRATION_SUCCESSFUL, cmd, null));
                    }
                    else
                    {
                        out.print(generateResponseXML(REGISTRATION_FAILED, cmd, null));
                    }
                }
                else
                {
                    out.print(generateResponseXML(res, cmd, "data", res.toString()));
                }
            }
            else if(cmd.equalsIgnoreCase("check_registration"))
            {
                String phone = request.getParameter("phone");
                DBRecord rec = dbProc().getRecord("SELECT id FROM clients WHERE phone_1 = '" + phone.replace("'", "") + "'");
                if(rec != null)
                {
                    out.print(generateResponseXML(REQUEST_PROCESSED_SUCCESSFULLY, cmd, "exists", "true"));
                }
                else
                {
                    out.print(generateResponseXML(REQUEST_PROCESSED_SUCCESSFULLY, cmd, "exists", "false"));
                }
            }
        }
        catch (Exception ex)
        {
            out.print(generateResponseExceptionXML(BASE_UNKNOWN_COMMAND, cmd, ex));
        }
        finally
        {
            out.close();
        }
    }
    private Integer checkParameters(HttpServletRequest request)
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        if(login == null || login.contains(" ") || login.length() < 3 || login.contains("<") || login.contains(">"))
        {
            return REGISTRATION_INCORRECT_LOGIN;
        }
        if(password == null || password.length() < 4 || password.contains("<") || password.contains(">"))
        {
            return REGISTRATION_INCORRECT_PASSWORD;
        }
        return PARAMETER_CHECK_SUCCESSFUL;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>
}

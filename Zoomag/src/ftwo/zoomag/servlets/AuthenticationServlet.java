package ftwo.zoomag.servlets;

import ftwo.library.access.Session;
import ftwo.library.access.User;
import ftwo.library.xml.XMLProcessor;
import ftwo.zoomag.structure.Basket;
import ftwo.zoomag.structure.Warehouse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthenticationServlet extends ftwo.library.web.AuthenticationServlet
{
    public static Integer AUTHENTICATION_NO_LOGIN = -1;
    public static Integer AUTHENTICATION_NO_PASSWORD = -2;
    public static Integer AUTHENTICATION_WRONG_LOGIN_OR_PASSWORD = -3;
    public static Integer AUTHORIZATION_FAILED = -4;
    public static Integer LOGOUT_FAILED = -5;

    public static Integer AUTHENTICATION_SUCCESSFUL = 1;
    public static Integer AUTHORIZATION_SUCCESSFUL = 2;
    public static Integer AUTHORIZATION_anonymous_SUCCESSFUL = 20;
    public static Integer LOGOUT_SUCCESSFUL = 3;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException,IOException
    {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        try
        {
            if(cmd == null)
            {
                
            }
	        else if(cmd.equalsIgnoreCase("authentication_anonymous"))
            {
                String session_id = authenticationAnonymous();
                if(session_id==null)
                {
                    out.print(BaseServlet.generateResponseXML(AUTHENTICATION_WRONG_LOGIN_OR_PASSWORD, cmd, "session_id",session_id));
                    out.close();
                    return;
                }
                else
                {
                    String expires = getSessionExpires();
                    String cookie = "session_id="+session_id+"; Expires="+expires;
                    response.setHeader("Set-Cookie", cookie);
                    out.print(BaseServlet.generateResponseXML(AUTHENTICATION_SUCCESSFUL, cmd, "session_id",session_id));
                    out.close();
                    return;
                }
            }
            else if(cmd.equalsIgnoreCase("authentication"))
            {
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if(login == null)
                {
                    out.print(BaseServlet.generateResponseXML(AUTHENTICATION_NO_LOGIN, cmd, null));
                    out.close();
                    return;
                }
                else if(password == null)
                {
                    out.print(BaseServlet.generateResponseXML(AUTHENTICATION_NO_PASSWORD, cmd, null));
                    out.close();
                    return;
                }
                else
                {
                    String s_key = authentication(login,password);
                    //User user = BaseServlet.sessProc().getSession(s_key).getUser();
                    User user = authorization_self(request);
                    Basket b = null;
                    if(user.isAnonymous())
                    {
                        b = Warehouse.getBasket(user.getID());
                    }
                    //login = login.replace("(","").replace(")","").replace("-","");
                    String session_id = authentication(login, password);
                    int new_user_id = BaseServlet.sessProc().getSession(session_id).getUser().getID();
                    Warehouse.setUserBasket(new_user_id,b);
                    if(session_id==null)
                    {
                        out.print(BaseServlet.generateResponseXML(AUTHENTICATION_WRONG_LOGIN_OR_PASSWORD, cmd, "session_id",session_id));
                        out.close();
                        return;
                    }
                    else
                    {
                        String expires = getSessionExpires();
                        String cookie = "session_id="+session_id+"; Expires="+expires;
                        response.setHeader("Set-Cookie", cookie);
                        out.print(BaseServlet.generateResponseXML(AUTHENTICATION_SUCCESSFUL, cmd, "login",login));
                        out.close();
                        return;
                    }
                }
            }
            else if(cmd.equalsIgnoreCase("authorization"))
            {
		        //System.out.println("authorization. "+BaseServlet.sessProc().size());
                User user = authorization(request);

                if(user!=null)
                {
                    if(user.isAnonymous())
                    {
                        out.print(BaseServlet.generateResponseXML(AUTHORIZATION_anonymous_SUCCESSFUL, cmd));
                    }
                    else
                    {
                        XMLProcessor proc = new XMLProcessor();
                        proc.addNode("root", "code", AUTHORIZATION_SUCCESSFUL);
                        proc.addNode("root", "data");
                        proc.addNode("root.data", "login", user.getValue("phone_1"));
                        proc.addNode("root", "admin", user.getValue("admin")!=null&&(Boolean)user.getValue("admin"));
                        out.print(proc.toXMLString());
                    }
                    out.close();
                    return;
                }
                else
                {
                    out.print(BaseServlet.generateResponseXML(AUTHORIZATION_FAILED, cmd, null));
                    out.close();
                    return;
                }
            }
            else if(cmd.equalsIgnoreCase("logout"))
            {
                User user = authorization(request);
                String redirect = request.getParameter("redirect");
                if(user!=null)
                {
                    Session s = getSession(request);
                    Boolean was_session = false;
                    if(s!=null)
                    {
                         was_session = BaseServlet.sessProc().removeSession(s.getKey());
                    }
                    if(redirect!=null)
                    {
                        response.setStatus(response.SC_MOVED_TEMPORARILY);
                        response.setHeader("Location", redirect);
                        return;
                    }
                    out.print(BaseServlet.generateResponseXML(LOGOUT_SUCCESSFUL, cmd,"was_session",was_session.toString()));
                    out.close();
                    return;
                }
                else
                {
                    if(redirect!=null)
                    {
                        response.setStatus(response.SC_MOVED_TEMPORARILY);
                        response.setHeader("Location", redirect);
                        return;
                    }
                    out.print(BaseServlet.generateResponseXML(LOGOUT_FAILED, cmd, null));
                    out.close();
                    return;
                }
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
    public User authorization_self(HttpServletRequest request)
    {
        Session s = getSession(request);
        if(s!=null)
        {
            return (User)s.getUser();
        }
        return null;
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

package racoonsoft.wish.servlets;

import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.xml.XMLProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OzonServlet extends BaseServlet {
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            String cmd = request.getParameter("cmd");
            if(cmd==null)
            {

            }
            if(cmd.equalsIgnoreCase("get_catalogue"))
            {
                JSONProcessor result = ozonProc().getCatalogueStructure();
                XMLProcessor xml = result.toXMLProcessor();


                out.print(generateResponseXML(REQUEST_PROCESSED_SUCCESSFULLY, cmd, result.getStructure()));
            }
        }
        catch (Exception ex)
        {

        }
        finally
        {
            out.close();
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
}

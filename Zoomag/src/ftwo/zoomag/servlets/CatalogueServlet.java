package ftwo.zoomag.servlets;

import ftwo.library.access.User;
import ftwo.library.settings.Settings;
import ftwo.library.xml.XMLProcessor;
import ftwo.zoomag.structure.Good;
import ftwo.zoomag.structure.Warehouse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CatalogueServlet extends BaseServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        request.setCharacterEncoding("UTF-8");
        Enumeration<String> en = request.getHeaderNames();

        System.out.println(" =================== HEADERS ===================");
        while(en.hasMoreElements())
        {
            String name = en.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+": "+value);
        }
        System.out.println(" ===============================================");
        try
        {
            start();
            if (cmd == null)
            {
                out.print(generateResponseXML(BASE_COMMAND_UNDEFINED, cmd, null));
            } 
            else if (cmd.equalsIgnoreCase("get_categories"))
            {
                XMLProcessor proc = new XMLProcessor();
                proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                proc.addNode("root", "data", Warehouse.getAnimalFoodTypes());
                out.print(proc.toXMLString());
            }
            else if (cmd.equalsIgnoreCase("get_brands"))
            {
                XMLProcessor proc = new XMLProcessor();
                proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                proc.addNode("root", "data", dbProc().getBrands());
                out.print(proc.toXMLString());
            }
            else if (cmd.equalsIgnoreCase("get_ages"))
            {
                String category = request.getParameter("category");
                String animal = request.getParameter("animal");
                XMLProcessor proc = new XMLProcessor();
                proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                proc.addNode("root", "data", dbProc().getAges(category, animal));
                out.print(proc.toXMLString());
            } 
            else if (cmd.equalsIgnoreCase("get_brand_categories"))
            {
                String brands = request.getParameter("brands");
                XMLProcessor proc = new XMLProcessor();
                proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                proc.addNode("root", "data", Warehouse.getBrandAnimalFoodTypes(brands.split("\\|")));
                out.print(proc.toXMLString());
            } 
            else if (cmd.equalsIgnoreCase("get_goods"))
            {
                String keywords = request.getParameter("keywords");
                String weight = request.getParameter("weight");
                String brand = request.getParameter("brand");
                String type = request.getParameter("type");
                String age = request.getParameter("age");
                String category = request.getParameter("category");
                String animal = request.getParameter("animal");
                String id = request.getParameter("id");
                ArrayList<Good> goods = Warehouse.getGoods(keywords, weight, brand, type, age, category, animal, id);

                XMLProcessor proc = new XMLProcessor();
                proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                proc.addNode("root", "data", goods);
                User user = AuthenticationServlet.authorization(request);
                if(user.getValue("admin")!=null&&(Boolean)user.getValue("admin"))
                {
                    proc.addNode("root", "admin", "true");
                }
                double len = proc.toXMLString().length()/1000000.0;
                out.print(proc.toXMLString());
            } 
            else if (cmd.equalsIgnoreCase("get_good"))
            {
                XMLProcessor proc = new XMLProcessor();
                proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                proc.addNode("root", "data", Warehouse.getAnimalFoodTypes());
                out.print(proc.toXMLString());
            }
            else if(cmd.equalsIgnoreCase("get_distances"))
            {
                XMLProcessor proc = new XMLProcessor();
                proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                proc.addNode("root", "data", dbProc().getDeliveryCosts());
                out.print(proc.toXMLString());
            }
            else if(cmd.equalsIgnoreCase("get_brands_by_category"))
            {
                String category = request.getParameter("category");
                if(category!=null&&category.equalsIgnoreCase("null"))
                {
                    category = null;
                }
                String animal = request.getParameter("animal");
                if(animal!=null&&animal.equalsIgnoreCase("null"))
                {
                    animal = null;
                }
                String subcategory = request.getParameter("subcategory");
                if(subcategory!=null&&subcategory.equalsIgnoreCase("null"))
                {
                    subcategory = null;
                }
                String keywords = request.getParameter("keywords");
                if(keywords!=null&&keywords.equalsIgnoreCase("null"))
                {
                    keywords = null;
                }
                XMLProcessor proc = new XMLProcessor();
                proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                proc.addNode("root", "data", dbProc().getBrandsByCategory(animal,category,subcategory,keywords));
                out.print(proc.toXMLString());
            }
            else if(cmd.equalsIgnoreCase("load"))
            {
                String load_password = request.getParameter("load_password");
                XMLProcessor proc = new XMLProcessor();
                if(load_password.equalsIgnoreCase(Settings.getStringSetting("load_password")))
                {
                    Warehouse.load();
                    proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                }
                else
                {
                    proc.addNode("root", "code", BASE_ADMIN_AUTH_FAILED);
                }
                out.print(proc.toXMLString());
            }
            else
            {
                out.print(generateResponseXML(BASE_UNKNOWN_COMMAND, cmd, null));
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

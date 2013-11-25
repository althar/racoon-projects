package ftwo.zoomag.servlets;

import ftwo.library.access.Session;
import ftwo.library.access.User;
import ftwo.library.database.DBRecord;
import ftwo.library.mail.MailMessage;
import ftwo.library.settings.Settings;
import ftwo.library.sms.SMSMessage;
import ftwo.library.xml.XMLProcessor;
import ftwo.zoomag.structure.Basket;
import ftwo.zoomag.structure.BasketItem;
import ftwo.zoomag.structure.Good;
import ftwo.zoomag.structure.Warehouse;
import org.apache.commons.math3.util.MathUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends BaseServlet {

    public static Integer RECOVER_PASSWORD_NO_SUCH_USER = -39;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException
    {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        try
        {
            start();
            User user = AuthenticationServlet.authorization(request);
            if (cmd == null)
            {
                out.print(generateResponseXML(BASE_COMMAND_UNDEFINED, cmd, null));
            }
            else if(cmd.equalsIgnoreCase("get_profile"))
            {
                if(user != null)
                {
                    User u = dbProc().getUser(user.getID());
                    XMLProcessor proc = new XMLProcessor();
                    proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                    proc.addNode("root", "data", u);

                    out.print(proc.toXMLString());
                    out.close();
                }
            }
            else if(cmd.equalsIgnoreCase("save_profile"))
            {
                if(user != null)
                {
                    String login = request.getParameter("login");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    HashMap<String,Object> parrs = new HashMap<String, Object>();
                    if(login!=null&& user.getStringValue("phone_1")!=login)
                    {
                        parrs.put("phone_1",login);
                    }
                    if(email!=null)
                    {
                        parrs.put("email",email);
                    }
                    if(password!=null)
                    {
                        parrs.put("password",password);
                    }
                    XMLProcessor proc = new XMLProcessor();
                    if(dbProc().updateProfile(parrs,user.getID()))
                    {
                        proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                    }
                    else
                    {
                        proc.addNode("root", "code", BASE_RESPONSE_GENERATION_FAILED);
                    }
                    out.print(proc.toXMLString());
                    out.close();
                }
            }
            else if(cmd.equalsIgnoreCase("get_order_history"))
            {
                if(user != null)
                {
                    ArrayList<DBRecord> history = dbProc().getOrderHistory(user.getID());
                    XMLProcessor proc = new XMLProcessor();
                    proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                    proc.addNode("root", "data", history);
                    out.print(proc.toXMLString());
                    out.close();
                }
            }
            else if (cmd.equalsIgnoreCase("get_metros"))
            {
                ArrayList<DBRecord> metros = dbProc().getMetros();
                XMLProcessor proc = new XMLProcessor();
                proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                proc.addNode("root", "data", metros);
                out.print(proc.toXMLString());
                out.close();
            }
            else if (cmd.equalsIgnoreCase("get_streets"))
            {
                String part = request.getParameter("street_part");
                ArrayList<DBRecord> streets = dbProc().getStreets(part);
                XMLProcessor proc = new XMLProcessor();
                proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                proc.addNode("root", "data", streets);
                out.print(proc.toXMLString());
                out.close();
            }
            else if (cmd.equalsIgnoreCase("put_in_basket"))
            {
                if (user != null)
                {
                    String good_id = request.getParameter("good_id");
                    String quantity = request.getParameter("quantity");
                    if(quantity!=null)
                    {
                        Warehouse.setGoodInBasket(user.getID(), Integer.valueOf(good_id),Integer.valueOf(quantity));
                    }
                    else
                    {
                        Warehouse.putGoodInBasket(user.getID(), Integer.valueOf(good_id));
                    }
                    XMLProcessor proc = new XMLProcessor();
                    proc.addNode("root", "code",REQUEST_PROCESSED_SUCCESSFULLY);
                    out.print(generateResponseXML(REQUEST_PROCESSED_SUCCESSFULLY, cmd));
                    out.close();
                }
                else
                {
                    out.print(generateResponseXML(AUTHORIZATION_FAILED, cmd, null));
                    out.close();
                    return;
                }
            }
            else if (cmd.equalsIgnoreCase("set_good_in_basket"))
            {
                if (user != null)
                {
                    String good_id = request.getParameter("good_id");
                    String quantity = request.getParameter("quantity");
                    Warehouse.setGoodInBasket(user.getID(), Integer.valueOf(good_id), Integer.valueOf(quantity));

                    out.print(generateResponseXML(REQUEST_PROCESSED_SUCCESSFULLY, cmd));
                    out.close();
                }
                else
                {
                    out.print(generateResponseXML(AUTHORIZATION_FAILED, cmd, null));
                    out.close();
                    return;
                }
            }
            else if (cmd.equalsIgnoreCase("get_basket"))
            {
                if (user != null)
                {
                    Basket b = Warehouse.getBasket(user.getID());
                    if (b == null)
                    {
                        b = new Basket(user.getID());
                    }
                    if(user.getValue("admin")!=null&&(Boolean)user.getValue("admin"))
                    {
                         b = dbProc().fillBasket(b);
                    }
                    XMLProcessor proc = new XMLProcessor();
                    proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                    proc.addNode("root", "sum", b.getPrice());
                    proc.addNode("root", "discount", "7");

                    proc.addNode("root", "data");
                    proc.addNodesUnnamed("root.data", b.getItems());

                    out.print(proc.toXMLString());
                    out.close();
                }
                else
                {
                    out.print(generateResponseXML(AUTHORIZATION_FAILED, cmd, null));
                    out.close();
                    return;
                }
            }
            else if(cmd.equalsIgnoreCase("check_order"))
            {
                if (user != null)
                {

                    Basket b = Warehouse.getBasket(user.getID());
                    Integer goods_price = 0;
                    int discount = 0;
                    Double deliver_price = 0.0;
                    boolean is_pickup = false;
                    boolean can_deliver = false;
                    Double minimum_order_price = 0.0;
                    int delivery_discount = 0;
                    int goods_with_discount = 0;
                    int goods_count = 0;
                    if(b!=null)
                    {
                        goods_price = b.getPrice();
                        goods_count = b.size();
                        String distance = request.getParameter("distance");
                        DBRecord del_price_record = dbProc().getDeliveryPrice(distance,Double.valueOf(goods_price.toString()));
                        can_deliver = del_price_record.getBooleanValue("can_deliver");
                        is_pickup = del_price_record.getBooleanValue("is_pickup");
                        minimum_order_price = del_price_record.getDoubleValue("minimum_order_price");
                        delivery_discount = del_price_record.getIntValue("discount");
                        DBRecord discount_record = dbProc().getDiscount(Double.valueOf(goods_price.toString()), user.getID(),delivery_discount);
                        discount = discount_record.getDoubleValue("total_discount").intValue();


                        goods_with_discount = (int)Math.round((double)goods_price*((100-discount)/100.0));
                        BigDecimal rounded = new BigDecimal(goods_with_discount/1000.0).round(new MathContext(3, RoundingMode.HALF_UP));
                        goods_with_discount = (int)(rounded.doubleValue()*1000);
                    }
                    XMLProcessor proc = new XMLProcessor();
                    proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                    proc.addNode("root", "data");
                    proc.addNode("root.data","goods_without_discount",goods_price);
                    proc.addNode("root.data","discount",discount);
                    proc.addNode("root.data","discount_in_rub",Math.round(goods_price*(discount/100.0)));
                    proc.addNode("root.data","goods_count",goods_count);
                    proc.addNode("root.data","goods_with_discount",goods_with_discount);
                    proc.addNode("root.data","delivery_price",deliver_price);
                    proc.addNode("root.data","can_deliver",can_deliver);
                    proc.addNode("root.data","minimum_order_price",minimum_order_price);
                    proc.addNode("root.data","total_price",goods_with_discount+deliver_price);
                    out.print(proc.toXMLString());
                    out.close();
                }
                else
                {
                    out.print(generateResponseXML(AUTHORIZATION_FAILED, cmd, null));
                    out.close();
                    return;
                }
            }
            else if(cmd.equalsIgnoreCase("save_order"))
            {
                if (user != null)
                {
                    HashMap<String,Object> pars = new HashMap<String, Object>();
                    Basket b = Warehouse.getBasket(user.getID());
                    String city = request.getParameter("city");
                    String street = request.getParameter("street");
                    // ----------- Deliver time -------------------
                    String from_hour = request.getParameter("from_hour");
                    String from_minute = request.getParameter("from_minute");
                    String to_hour = request.getParameter("to_hour");
                    String to_minute = request.getParameter("to_minute");
                    Calendar deliver_date = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
                    deliver_date.setTimeInMillis(Long.valueOf(request.getParameter("deliver_date")));
                    System.out.println("DELIVERY TIME: "+deliver_date+" ...  "+request.getParameter("deliver_date"));
                    Calendar deliver_time_from = Calendar.getInstance();
                    deliver_time_from.set(Calendar.HOUR_OF_DAY,Integer.valueOf(from_hour));
                    deliver_time_from.set(Calendar.MINUTE,Integer.valueOf(from_minute));
                    deliver_time_from.set(Calendar.SECOND,0);
                    Calendar deliver_time_to = Calendar.getInstance();
                    deliver_time_to.set(Calendar.HOUR_OF_DAY,Integer.valueOf(to_hour));
                    deliver_time_to.set(Calendar.MINUTE,Integer.valueOf(to_minute));
                    deliver_time_to.set(Calendar.SECOND,0);
                    // ----------------------------------------------
                    String house = request.getParameter("house");
                    String building = request.getParameter("building");
                    String room = request.getParameter("room");
                    String porch = request.getParameter("porch");
                    String domophone = request.getParameter("domophone");
                    String floor = request.getParameter("floor");
                    String distance = request.getParameter("distance");
                    String distance_id = request.getParameter("distance_id");
                    String phone_2 = request.getParameter("phone_2");
                    String phone_3 = request.getParameter("phone_3");
                    if(phone_2.equalsIgnoreCase("Телефон"))
                    {
                        phone_2 = "";
                    }
                    if(phone_3.equalsIgnoreCase("Телефон"))
                    {
                        phone_3 = "";
                    }
                    String metro_id = request.getParameter("metro_id");
                    String description = request.getParameter("description");
                    int discount = dbProc().getUserDiscountByID(user.getID());
                    int driver_id = dbProc().getDriverID(Integer.valueOf(metro_id));
                    boolean deliver = !distance_id.equalsIgnoreCase("0");
                    int deliver_price = 0;
                    if(distance_id==null||distance_id.equalsIgnoreCase("null"))
                    {
                        distance_id = "0";
                    }
                    if(!distance_id.equalsIgnoreCase("0"))
                    {
                        deliver_price = dbProc().getDeliverPrice(b.getPrice(),user.getID(),Integer.valueOf(distance_id));
                        pars.put("street",street);
                        pars.put("house",house);
                        pars.put("porch",porch);
                        pars.put("building",building);
                        pars.put("floor",floor);
                        pars.put("domophone",domophone);
                        pars.put("room",room);
                        pars.put("city",city);
                    }
                    else// +3% discount...
                    {
                        if(discount>4)
                        {
                            discount=4;
                        }
                        pars.put("street",distance);
                        pars.put("house","");
                        pars.put("porch","");
                        pars.put("building","");
                        pars.put("floor","");
                        pars.put("domophone","");
                        pars.put("room","");
                        pars.put("city","");
                    }
                    pars.put("driver_id",driver_id);
                    pars.put("client_id",user.getID());
                    pars.put("deliver_date",deliver_date);
                    pars.put("deliver",deliver);
                    pars.put("deliver_time_from_1",deliver_time_from.getTime());
                    pars.put("deliver_time_to_1",deliver_time_to.getTime());
                    pars.put("phone_1",user.getStringValue("phone_1"));
                    pars.put("phone_2",phone_2);
                    pars.put("phone_3",phone_3);
                    pars.put("metro_id",Integer.valueOf(metro_id));
                    pars.put("discount_percent",discount);

                    pars.put("deliver_distance",distance);
                    pars.put("goods_price",b.getPrice());
                    pars.put("description",description);
                    pars.put("deliver_price",deliver_price);
                    pars.put("type_id",Integer.valueOf(getParameter("site_id")));

                    int order_id = dbProc().saveOrder(b,pars);
                    b.clear();
                    HashMap<String,Object> cli_pars = new HashMap<String, Object>();
                    cli_pars.put("street",street);
                    cli_pars.put("house",house);
                    cli_pars.put("porch",porch);
                    cli_pars.put("metro_id",Integer.valueOf(metro_id));
                    //cli_pars.put("building",building);
                    cli_pars.put("floor",floor);
                    cli_pars.put("domophone",domophone);
                    cli_pars.put("room",room);
                    cli_pars.put("city",city);
                    cli_pars.put("distance",distance);

                    dbProc().executeUpdate("clients",cli_pars,"id="+user.getID());
                    HashMap<String,Object> email_pars = new HashMap<String, Object>();
                    email_pars.put("order_id",String.valueOf(order_id));
                    MailMessage mess = new MailMessage(Settings.getStringSetting("order_saved_email_address"),user.getStringValue("email"),"Заказ успешно принят", Settings.getStringSetting("order_saved_email"),email_pars);
                    mailProc().sendMail(mess);
                    XMLProcessor proc = new XMLProcessor();
                    proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                    proc.addNode("root", "order_id", order_id);

                    if(user.getValue("admin")!=null&&(Boolean)user.getValue("admin"))
                    {
                        proc.addNode("root", "admin", "true");
                        Session s = AuthenticationServlet.getSession(request);
                        if(s!=null)
                        {
                            BaseServlet.sessProc().removeSession(s.getKey());
                        }
                    }
                    out.print(proc.toXMLString());
                    out.close();
                }
                else
                {
                    out.print(generateResponseXML(AUTHORIZATION_FAILED, cmd, null));
                    out.close();
                    return;
                }
            }
            else if (cmd.equalsIgnoreCase("check_basket"))
            {
                if (user != null)
                {
                    Basket b = Warehouse.getBasket(user.getID());
                    if (b == null)
                    {
                        b = new Basket(user.getID());
                    }
                    XMLProcessor proc = new XMLProcessor();
                    proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                    proc.addNode("root", "data");
                    proc.addNode("root.data", "size", b.getItems().size());
                    proc.addNode("root.data", "price", b.getPrice());

                    out.print(proc.toXMLString());
                    out.close();
                }
                else
                {
                    out.print(generateResponseXML(AUTHORIZATION_FAILED, cmd, null));
                    out.close();
                    return;
                }
            }
            else if (cmd.equalsIgnoreCase("get_discount"))
            {
                if (user != null)
                {
                    Long total = dbProc().getUserTotalSpent(user.getID());
                    Integer percent = dbProc().getUserDiscount(total);
                    Long left = dbProc().getUserTotalToNext(total);
                    XMLProcessor proc = new XMLProcessor();
                    proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                    proc.addNode("root", "data");
                    proc.addNode("root.data", "percent", percent);
                    proc.addNode("root.data", "total", total);
                    proc.addNode("root.data", "left", left);

                    out.print(proc.toXMLString());
                    out.close();
                }
                else
                {
                    out.print(generateResponseXML(AUTHORIZATION_FAILED, cmd, null));
                    out.close();
                    return;
                }
            }
            else if (cmd.equalsIgnoreCase("get_address"))
            {
                if (user != null)
                {
                    DBRecord rec = dbProc().getLastOrder(user.getID());

                    String phone_2 = rec.getStringValue("phone_2");
                    String phone_3 = rec.getStringValue("phone_3");
                    String street = rec.getStringValue("street");
                    String house = rec.getStringValue("house");
                    String porch = rec.getStringValue("porch");
                    String floor = rec.getStringValue("floor");
                    String domophone = rec.getStringValue("domophone");
                    String room = rec.getStringValue("room");
                    String building = rec.getStringValue("building");
                    String city = rec.getStringValue("city");
                    int metro_id = rec.getIntValue("metro_id");

                    XMLProcessor proc = new XMLProcessor();
                    proc.addNode("root", "code", REQUEST_PROCESSED_SUCCESSFULLY);
                    proc.addNode("root", "data");
                    proc.addNode("root.data", "phone_2", phone_2);
                    proc.addNode("root.data", "phone_3", phone_3);
                    proc.addNode("root.data", "street", street);
                    proc.addNode("root.data", "house", house);
                    proc.addNode("root.data", "porch", porch);
                    proc.addNode("root.data", "floor", floor);
                    proc.addNode("root.data", "building", building);
                    proc.addNode("root.data", "domophone", domophone);
                    proc.addNode("root.data", "room", room);
                    proc.addNode("root.data", "city", city);
                    proc.addNode("root.data", "metro_id", metro_id);

                    out.print(proc.toXMLString());
                    out.close();
                }
                else
                {
                    out.print(generateResponseXML(AUTHORIZATION_FAILED, cmd, null));
                    out.close();
                    return;
                }
            }
            else if(cmd.equalsIgnoreCase("recover_password"))
            {
                String phone = request.getParameter("phone");
                String pass = dbProc().getPassword(phone);
                if(pass==null)
                {
                    dbProc().executeNonQuery("UPDATE clients SET password=random_string(6) WHERE phone_1='"+phone.replace("'","")+"'");
                    pass = dbProc().getPassword(phone);
                }
                if(pass!=null)
                {
                    phone = phone.replace("(","").replace(")","").replace("-","");
                    String number = "8"+phone;
                    String text = "Ваш пароль:"+pass;
                    String from = "Zoomag";
                    String result = smsProc().sendSMS(new SMSMessage(number, text, from));

                    out.print(generateResponseXML(REQUEST_PROCESSED_SUCCESSFULLY, cmd));
                    out.close();
                }
                else
                {
                    out.print(generateResponseXML(RECOVER_PASSWORD_NO_SUCH_USER, cmd));
                    out.close();
                }
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

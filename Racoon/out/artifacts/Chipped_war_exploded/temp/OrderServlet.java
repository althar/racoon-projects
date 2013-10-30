package ftwo.newmethod.servlets;

import ftwo.library.json.JSONProcessor;
import ftwo.library.settings.Settings;
import ftwo.library.sms.SMSMessage;
import ftwo.newmethod.database.DBProcessor;
import ftwo.newmethod.structure.NewMethodUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderServlet extends BaseServlet
{
	public static Integer CREATE_ORDER_FAILED_UNKNOWN = -19;
	public static Integer CREATE_ORDER_FAILED_NOT_ENOUGH_MONEY = -88;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        try
        {
            NewMethodUser user = (NewMethodUser)(AuthenticationServlet.authorization(request));

            if(user!=null)
            {
                if(cmd == null)
                {
                    out.print(generateResponseXML(BASE_COMMAND_UNDEFINED, cmd, null));
                }
                else if(cmd.equalsIgnoreCase("add_cart"))
                {
                    String item = request.getParameter("item");
                    JSONProcessor proc = ozonProc().cartAdd(String.valueOf(user.getID()), item+":1");
                    out.print(proc.toXMLString());
                }
                else if(cmd.equalsIgnoreCase("get_cart"))
                {
                    JSONProcessor proc = ozonProc().cartGet(String.valueOf(user.getID()));
                    HashMap<String,Object> struct = proc.getStructure();
                    System.out.println("STRUCT: "+struct);
                    out.print(proc.toXMLString());
                }
                else if(cmd.equalsIgnoreCase("clear_cart"))
                {
                    String items = request.getParameter("items");
                    JSONProcessor proc = ozonProc().cartRemove(String.valueOf(user.getID()),items);
                    HashMap<String,Object> struct = proc.getStructure();
                    System.out.println("STRUCT: "+struct);
                    out.print(proc.toXMLString());
                }
                else if(cmd.equalsIgnoreCase("order_start"))// 1 !!!
                {
                    // 1 - CheckoutStart
                    JSONProcessor startOrderResp = ozonProc().startOrder(String.valueOf(user.getID()));
                    String guid = (String)startOrderResp.getValue("OrderGuid");
                    user.setValue("guid", guid);
                    // 2 - RegionsGet
                    JSONProcessor getAvalableRegionsResp = ozonProc().getAvalableRegions(String.valueOf(user.getID()), guid);
                    out.print(getAvalableRegionsResp.toXMLString());
                }
                else if(cmd.equalsIgnoreCase("get_delivery_variants"))// 2 !!!
                {
                    String order_guid = user.getStringValue("guid");
                    String area_id = request.getParameter("area_id");
                    JSONProcessor getDeliveryVariantsResp = ozonProc().getDeliveryVariants(String.valueOf(user.getID()), order_guid, "0", area_id);
                    int status = getDeliveryVariantsResp.getIntValue("Status");
                    if(status == 2)// Ok
                    {
                            user.setValue("area_id", area_id);
                    }
                    out.print(getDeliveryVariantsResp.toXMLString());
                }
                else if(cmd.equalsIgnoreCase("get_payment_variants")) // Must be useless
                {
                    String order_guid = request.getParameter("order_guid");
                    String area_id = request.getParameter("area_id");
                    String delivery_variant_id = request.getParameter("delivery_variant_id");
                    JSONProcessor proc = ozonProc().getPaymentVariants(String.valueOf(user.getID()), order_guid, "0", area_id,delivery_variant_id);
                    out.print(proc.toXMLProcessor().toXMLString());
                }
                else if(cmd.equalsIgnoreCase("save_order"))// 3 !!!
                {
                    String user_id = String.valueOf(user.getID());
                    String guid = user.getStringValue("guid");
                    String area_id = user.getStringValue("area_id");
                    String delivery_variant_id = request.getParameter("delivery_variant_id");
                    String payment_variant_id = "8"; // Pay with bonus money...
                    String sum = request.getParameter("total_price");
                    String zip = request.getParameter("zip");
                    String country = request.getParameter("country");
                    String region = request.getParameter("region");
                    String district = request.getParameter("district");
                    String city = request.getParameter("city");
                    String addressee = request.getParameter("addressee");
                    String address_tail = request.getParameter("address_tail");
                    String comment = "";
                    String phone =request.getParameter("phone");
                    String metro_id = request.getParameter("metro_id");
                    String first_name = request.getParameter("first_name");
                    String middle_name = request.getParameter("middle_name");
                    String last_name = request.getParameter("last_name");

                    String delivery_choice = "1";

                    // 0 - Check money
                    JSONProcessor getPaymentVariantsResp = ozonProc().getPaymentVariants(user_id, guid, "0", area_id,delivery_variant_id);
                    String paymentVariantsString = getPaymentVariantsResp.toXMLString();

                    if(paymentVariantsString.contains("<PaymentTypeId>8</PaymentTypeId>"))// We have "8" payment type id...
                    {
                        // 1 - Save order
                        JSONProcessor saveOrderResp = ozonProc().saveOrder(user_id
                                        , guid
                                        , "0"
                                        , area_id
                                        , delivery_variant_id
                                        , payment_variant_id
                                        , zip
                                        , country
                                        , region
                                        , district
                                        , city
                                        , addressee
                                        , address_tail
                                        , comment
                                        , phone
                                        , metro_id
                                        , first_name
                                        , middle_name
                                        , last_name);

                        // 2 - Check order
                        String addr_id = saveOrderResp.getIntValue("NewAddressId").toString();
                        JSONProcessor checkOrderResp = ozonProc().checkOrder(user_id
                                        , guid
                                        , addr_id
                                        , area_id
                                        , delivery_variant_id
                                        , delivery_choice // Delivery choice
                                        , payment_variant_id
                                        , "0"
                                        , "false");
                        // 3 - Summary
                        JSONProcessor summaryOrderResp = ozonProc().summaryOrder(user_id, guid, addr_id, delivery_variant_id, payment_variant_id, delivery_choice, sum, "false");
                        sum = summaryOrderResp.getValue("TotalOrder.FullOrderSumm").toString();
                        // 4 - Checkout finish
                        JSONProcessor createOrderResp = ozonProc().createOrder(user_id, guid, addr_id, delivery_variant_id, payment_variant_id, delivery_choice, "0", phone, comment, user.getStringValue("email"), addressee, "false", metro_id);
                        int status = createOrderResp.getIntValue("Status");
                        if(status == 2)// Ok
                        {

                            String order_number = createOrderResp.getValue("OrderNumber").toString();
                            String cardActivationMessage = Settings.getStringSetting("order_create_message");
                            cardActivationMessage = cardActivationMessage.replace("<order_number>", order_number);
                            String phone_number = user.getStringValue("login");
                            BaseServlet.smsProc().sendSMS(new SMSMessage(phone_number, cardActivationMessage, "BonusKarta"));

                            ((DBProcessor)BaseServlet.dbProc()).insertOrder(order_number, user.getID(), Double.parseDouble(sum));
                            out.print(generateResponseXML(REQUEST_PROCESSED_SUCCESSFULLY, cmd, "order_number",order_number));
                        }
                        else
                        {
                            out.print(generateResponseXML(CREATE_ORDER_FAILED_UNKNOWN, cmd, null));
                        }
                    }
                    else
                    {
                        out.print(generateResponseXML(CREATE_ORDER_FAILED_NOT_ENOUGH_MONEY, cmd, null));
                    }
                }
                else
                {
                    out.print(generateResponseXML(BASE_UNKNOWN_COMMAND, cmd, null));
                }
            }
            else
            {
                out.print(generateResponseXML(AUTHORIZATION_FAILED, cmd, null));
                out.close();
                return;
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

}

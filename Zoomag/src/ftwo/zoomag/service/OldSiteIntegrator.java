package ftwo.zoomag.service;
import ftwo.library.database.DBProcessor;
import ftwo.library.database.DBRecord;
import ftwo.library.database.SQLExpression;
import ftwo.library.helper.Helper;
import ftwo.library.processors.SeparateThreadProcessor;
import ftwo.library.settings.Settings;
import ftwo.zoomag.database.DatabaseProcessor;
import ftwo.zoomag.servlets.BaseServlet;

import javax.servlet.SingleThreadModel;
import java.util.ArrayList;
import java.util.HashMap;

public class OldSiteIntegrator extends SeparateThreadProcessor
{
    private DBProcessor mySqlProc1;
    private DBProcessor mySqlProc2;
    public OldSiteIntegrator(String processor_name)
    {
        super(processor_name);
        try
        {
            mySqlProc1 = new DatabaseProcessor("zverovod.ru","zverovod?useUnicode=true&characterEncoding=UTF-8",3306,"racoon","racoonracoon2000","com.mysql.jdbc.Driver","jdbc:mysql:");
            mySqlProc1.connect();
            System.out.println("Site 1 integrator connected");
            mySqlProc2 = new DatabaseProcessor("myzoomag.ru","wwwmyzoomagru?useUnicode=true&characterEncoding=UTF-8",3306,"racoon","racoonracoon2000","com.mysql.jdbc.Driver","jdbc:mysql:");
            mySqlProc2.connect();
            System.out.println("Site 2 integrator connected");
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }

    }
    @Override
    public void process()
    {
        try
        {
            mySqlProc1.disconnect();
            mySqlProc1 = new DatabaseProcessor("zverovod.ru","zverovod?useUnicode=true&characterEncoding=UTF-8",3306,"racoon","racoonracoon2000","com.mysql.jdbc.Driver","jdbc:mysql:");
            mySqlProc1.connect();
            System.out.println("Site 1 integrator connected");
            mySqlProc2.disconnect();
            mySqlProc2 = new DatabaseProcessor("myzoomag.ru","wwwmyzoomagru?useUnicode=true&characterEncoding=UTF-8",3306,"racoon","racoonracoon2000","com.mysql.jdbc.Driver","jdbc:mysql:");
            mySqlProc2.connect();
            System.out.println("Site 2 integrator connected");
        }
        catch (Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
        }

        doSite(mySqlProc1,"site_1_order_id",3);
        doSite(mySqlProc2,"site_2_order_id",2);
    }
    public static String normalizePhone(String phone)
    {
        String finRes = "";
        try
        {
            String result = phone;
            result = result.replaceAll("[^x0-9]","");
            result = result.replace("(","");
            result = result.replace(")","");
            result = result.replace(" ","");
            result = result.replace("-","");
            result = result.replace("_","");
            result = result.replace("+7","");
            result = result.replace("+8","");
            if(result.length()>=11)
            {
                result = result.substring(result.length()-10);
            }
            finRes = "("+result.substring(0,3)+")";
            finRes += result.substring(3,6)+"-";
            finRes += result.substring(6,8)+"-";
            finRes += result.substring(8);

        }
        catch(Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
        }
        return finRes;
    }
    private void doSite(DBProcessor proc,String seqName,Integer source)
    {
        try
        {
            Thread.sleep(3000);
            DBProcessor dbProc = BaseServlet.DBProc;
            long seq1 = dbProc.sequenceGetCurrentValue(seqName);
            // 1 - Get all orders
            ArrayList<DBRecord> orders = proc.getRecords("SELECT orderID,customerID, 0 AS client_id, shipping_type NOT LIKE '%Самовывоз%' AS deliver,shipping_type,customers_comment,order_amount," +
                    "CONCAT(customer_firstname, ' ', customer_lastname) AS client_name, customer_email,shipping_address FROM SC_orders WHERE orderID > "+seq1+" AND DATE(order_time)=DATE(NOW()) ORDER BY orderID");
            System.out.println("Orders: "+orders.size());
            // 2 - Insert data
            for(int i=0; i<orders.size(); i++)
            {
                DBRecord order = orders.get(i);
                boolean deliver = order.getBooleanValue("deliver");
                Long id = order.getLongValue("orderID");
                try
                {
                    String comment = order.getStringValue("customers_comment");
                    Double order_amount = order.getDoubleValue("order_amount");
                    String email = order.getStringValue("customer_email");
                    String address = order.getStringValue("shipping_address");
                    String client_name = order.getStringValue("client_name");
                    Integer customer_id = order.getIntValue("customerID");
                    String shipping_type = order.getStringValue("shipping_type");
                    String metro = "";

                    Integer clientId = null;
                     DBRecord client = null;
                    //client = dbProc.getRecord("SELECT * FROM clients WHERE email='"+email+"'");
                    String phoneFromSite = "";
                    String phoneAsIs = "";
                    if(client==null)
                    {
                        HashMap<String,Object> clPars = new HashMap<String, Object>();

                        // Metro
                        ArrayList<DBRecord> clientFieldsMetro = proc.getRecords("SELECT * FROM SC_customer_reg_fields_values WHERE reg_field_ID=3 AND customerID="+customer_id);
                        if(clientFieldsMetro!=null&&clientFieldsMetro.size()>0)
                        {
                            metro = clientFieldsMetro.get(0).getStringValue("reg_field_value");
                        }

                        ArrayList<DBRecord> clientFields = proc.getRecords("SELECT * FROM SC_customer_reg_fields_values WHERE reg_field_ID=1 AND customerID="+customer_id);
                        if(clientFields==null||clientFields.size()==0)
                        {
                            clPars.put("phone_1","(000)00-00-00");
                            clPars.put("phone_2","");
                            clPars.put("phone_3","");
                        }
                        else if(clientFields.size()>0)
                        {

                            phoneFromSite = clientFields.get(0).getStringValue("reg_field_value");
                            phoneAsIs = new String(phoneFromSite);
                            phoneFromSite = normalizePhone(phoneFromSite);
                            clPars.put("phone_1",clientFields.get(0).getStringValue("reg_field_value"));
                            client = dbProc.getRecord("SELECT * FROM clients WHERE phone_1='"+phoneFromSite+"' OR phone_2='"+phoneFromSite+"' OR phone_3='"+phoneFromSite+"'");
                        }
                        if(client==null)
                        {
                            clPars.put("name",client_name);
                            clPars.put("email",email);
                            clientId = dbProc.executeInsert("clients",clPars);
                        }
                        if(client!=null)
                        {
                            clPars.put("name",client_name);
                            clPars.put("email",email);
                            clientId = client.getID();
                        }


                        client = dbProc.getRecord("SELECT * FROM clients WHERE id="+clientId+"");
                    }

                    HashMap<String,Object> orderPars = new HashMap<String, Object>();
                    Integer clId = client.getIntValue("id");
                    orderPars.put("client_id",clId);
                    orderPars.put("phone_1",phoneFromSite);

                    if(client.getStringValue("phone_2")!=null)
                    {
                        orderPars.put("phone_2",client.getStringValue("phone_2"));
                    }
                    if(client.getStringValue("phone_3")!=null)
                    {
                        orderPars.put("phone_3",client.getStringValue("phone_3"));
                    }

                    orderPars.put("deliver",deliver);
                    orderPars.put("street",client.getStringValue("street"));
                    orderPars.put("city",client.getStringValue("city"));
                    orderPars.put("house",client.getStringValue("house"));
                    orderPars.put("room",client.getStringValue("room"));
                    orderPars.put("porch",client.getStringValue("porch"));
                    orderPars.put("floor",client.getStringValue("floor"));

                    String commentFull = "Номер заказа: "+id+". "+comment;
                    if(address!=null)
                    {
                        commentFull+=address+".";
                    }
                    if(client_name!=null)
                    {
                        commentFull+=client_name+".";
                    }
                    if(phoneFromSite!=null)
                    {
                        commentFull+=phoneAsIs+".";
                    }
                    if(shipping_type!=null)
                    {
                        commentFull+=shipping_type+".";
                    }
                    commentFull+=metro+".";

                    orderPars.put("description",commentFull);
                    orderPars.put("goods_price",order_amount);
                    orderPars.put("deliver_date",new SQLExpression("now()+INTERVAL '1 DAYS'"));
                    orderPars.put("discount_percent",0);
                    orderPars.put("deliver_price",0);
                    orderPars.put("metro_id",client.getIntValue("metro_id"));
                    orderPars.put("deliver_distance",client.getStringValue("distance"));
                    orderPars.put("type_id",source);
                    Integer order_id = dbProc.executeInsert("orders",orderPars);
                    System.out.println("Order inserted: "+order_id);

                    ArrayList<DBRecord> goods = proc.getRecords("SELECT * FROM SC_ordered_carts WHERE orderID="+id);
                    for(int j=0; j<goods.size(); j++)
                    {
                        DBRecord good = goods.get(j);
                        String name = good.getStringValue("name");
                        int index1 = name.indexOf("[",0);
                        int index2 = name.indexOf("]",0);
                        String article = name.substring(index1+1,index2);
                        Double price = good.getDoubleValue("Price");
                        Double count = good.getDoubleValue("Quantity");
                        DBRecord good_art = dbProc.getRecord("SELECT id,name_for_order FROM goods WHERE articul='"+article+"'");
                        Long good_id = 0l;
                        HashMap<String,Object> goodPars = new HashMap<String, Object>();
                        if(good_art!=null)
                        {
                            good_id = good_art.getLongValue("id");
                            goodPars.put("good_name",good_art.getStringValue("name_for_order"));
                        }
                        else
                        {
                            System.out.println("Fail article: "+article);
                        }


                        goodPars.put("order_id",order_id);
                        goodPars.put("good_id",good_id);
                        goodPars.put("count",count);

                        goodPars.put("price",price);
                        dbProc.executeInsert("order_goods",goodPars);

                    }
                }
                catch(Exception ex)
                {
                    System.out.println(Helper.getStackTraceString(ex));
                }
                dbProc.sequenceSetCurrentValue(seqName,id);
            }
        }
        catch(Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
        }
    }
}

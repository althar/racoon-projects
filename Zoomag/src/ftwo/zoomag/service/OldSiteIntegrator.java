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
        doSite(mySqlProc1,"site_1_order_id",3);
        doSite(mySqlProc2,"site_2_order_id",2);
    }
    public static String normalizePhone(String phone)
    {
        String finRes = "";
        try
        {
            String result = phone;
            result = result.replace("(","");
            result = result.replace(")","");
            result = result.replace(" ","");
            result = result.replace("-","");
            result = result.replace("_","");
            result = result.replace("+","");
            if(result.length()==11
                    &&
                    (result.substring(0,1).equalsIgnoreCase("7")||result.substring(0,1).equalsIgnoreCase("8")))
            {
                result = result.substring(1);
            }
            finRes = "("+result.substring(0,3)+")";
            finRes += result.substring(3,6)+"-";
            finRes += result.substring(6,8)+"-";
            finRes += result.substring(8);

        }
        catch(Exception ex)
        {

        }
        return finRes;
    }
    private void doSite(DBProcessor proc,String seqName,Integer source)
    {
        try
        {
            Thread.sleep(10000);
            DBProcessor dbProc = BaseServlet.DBProc;
            long seq1 = dbProc.sequenceGetCurrentValue(seqName);
            // 1 - Get all orders
            ArrayList<DBRecord> orders = proc.getRecords("SELECT orderID,customerID, 0 AS client_id, shipping_type!='Самовывоз' AS deliver,customers_comment,order_amount," +
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

                    Integer clientId = null;
                    DBRecord client = dbProc.getRecord("SELECT * FROM clients WHERE email='"+email+"'");
                    if(client==null)
                    {
                        HashMap<String,Object> clPars = new HashMap<String, Object>();
                        ArrayList<DBRecord> clientFields = proc.getRecords("SELECT * FROM SC_customer_reg_fields_values WHERE reg_field_ID=1 AND customerID="+customer_id);
                        if(clientFields==null||clientFields.size()==0)
                        {
                            clPars.put("phone_1","(000)00-00-00");
                            clPars.put("phone_2","");
                            clPars.put("phone_3","");
                        }
                        else if(clientFields.size()>0)
                        {
                            String phoneFromSite = clientFields.get(0).getStringValue("reg_field_value");
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
                    orderPars.put("client_id",client.getIntValue("id"));
                    orderPars.put("phone_1",client.getStringValue("phone_1"));
                    orderPars.put("deliver",deliver);
                    orderPars.put("street",address);
                    orderPars.put("city","");
                    orderPars.put("house","");
                    orderPars.put("room","");
                    orderPars.put("porch","");
                    orderPars.put("floor","");
                    orderPars.put("description",comment);
                    orderPars.put("goods_price",order_amount);
                    orderPars.put("deliver_date",new SQLExpression("now()+INTERVAL '1 DAYS'"));
                    orderPars.put("discount_percent",0);
                    orderPars.put("deliver_price",0);
                    orderPars.put("metro_id",0);
                    orderPars.put("deliver_distance","");
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
                        DBRecord good_art = dbProc.getRecord("SELECT id FROM goods WHERE articul='"+article+"'");
                        Long good_id = 0l;
                        if(good_art!=null)
                        {
                            good_id = good_art.getLongValue("id");
                        }
                        else
                        {
                            System.out.println("Fail article: "+article);
                        }

                        HashMap<String,Object> goodPars = new HashMap<String, Object>();
                        goodPars.put("order_id",order_id);
                        goodPars.put("good_id",good_id);
                        goodPars.put("count",count);
                        goodPars.put("good_name",name);
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

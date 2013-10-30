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
            mySqlProc1 = new DatabaseProcessor("mysql.myzoom01.mass.hc.ru","wwwmyzoomagru",3306,"myzoom01","ngw0Laej","com.mysql.jdbc.Driver","jdbc:mysql:");
            mySqlProc1.connect();
            System.out.println("Site 1 integrator connected");
            mySqlProc2 = new DatabaseProcessor("zverovod.mysql","zverovod_db",3306,"zverovod_mysql","s17vpvth","com.mysql.jdbc.Driver","jdbc:mysql:");
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
        doSite(mySqlProc1,"site_1_order_id");
    }

    private void doSite(DBProcessor proc,String seqName)
    {
        try
        {
            Thread.sleep(10000);
            DBProcessor dbProc = BaseServlet.DBProc;
            long seq1 = dbProc.sequenceGetCurrentValue(seqName);
            // 1 - Get all orders
            ArrayList<DBRecord> orders = proc.getRecords("SELECT orderID, 0 AS client_id, shipping_type!='Самовывоз' AS deliver,customers_comment,order_amount," +
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

                    DBRecord client = dbProc.getRecord("SELECT * FROM clients WHERE email='"+email+"'");
                    if(client==null)
                    {
                        HashMap<String,Object> clPars = new HashMap<String, Object>();
                        clPars.put("name",client_name);
                        clPars.put("email",email);
                        clPars.put("phone_1","(000)00-00-00");
                        clPars.put("phone_2","");
                        clPars.put("phone_3","");
                        dbProc.executeInsert("clients",clPars);
                        client = dbProc.getRecord("SELECT * FROM clients WHERE email='"+email+"'");
                    }
                    Long clientId = client.getLongValue("id");

                    HashMap<String,Object> orderPars = new HashMap<String, Object>();
                    orderPars.put("client_id",clientId);
                    orderPars.put("phone_1","(000)000-00-00");
                    orderPars.put("phone_2","(000)000-00-00");
                    orderPars.put("phone_3","(000)000-00-00");
                    orderPars.put("deliver",deliver);
                    orderPars.put("street",address);
                    orderPars.put("description",comment);
                    orderPars.put("goods_price",order_amount);
                    orderPars.put("deliver_date",new SQLExpression("now()"));
                    orderPars.put("deliver_time_from_1",new SQLExpression("now()"));
                    orderPars.put("deliver_time_from_2",new SQLExpression("now()"));
                    orderPars.put("deliver_time_to_1",new SQLExpression("now()"));
                    orderPars.put("deliver_time_to_2",new SQLExpression("now()"));
                    orderPars.put("discount_percent",0);
                    orderPars.put("deliver_price",0);
                    orderPars.put("metro_id",0);
                    orderPars.put("deliver_distance","");
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

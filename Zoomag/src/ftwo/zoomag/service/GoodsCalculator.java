package ftwo.zoomag.service;
import ftwo.library.database.DBRecord;
import ftwo.library.helper.Helper;
import ftwo.library.processors.SeparateThreadProcessor;
import ftwo.library.processors.SeparateThreadSingleTaskProcessor;
import ftwo.zoomag.database.DatabaseProcessor;

import java.util.ArrayList;

public class GoodsCalculator extends SeparateThreadSingleTaskProcessor
{
    private String status = "Готово";
    private Integer progress = 0;
    private static GoodsCalculator self;
    private DatabaseProcessor dbProc;
    private GoodsCalculator()
    {
        super("Good calculator");
    }
    public static GoodsCalculator instance(DatabaseProcessor dbProc) throws Exception
    {
        if(self == null)
        {
            self = new GoodsCalculator();
        }
        self.dbProc = dbProc;
        return self;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public Integer getProgress()
    {
        return progress;
    }
    public void setProgress(Integer progress)
    {
        this.progress = progress;
    }

    @Override
    public void process()
    {
        setStatus("Выполняется пересчет остатков");
        // Make calculations
        try
        {
            progress = 0;
            // 0) Prepare
            for(int i=0; i<10; i++)
            {
                Thread.sleep(1000);
                progress++;
            }
            // 1) New orders with delivery...
            ArrayList<DBRecord> ordersToAccept = dbProc.getRecords("SELECT * FROM orders_with_details WHERE deliver_date<date(now()) AND status_id=1 AND deliver=true");
            for(int i = 0; i < ordersToAccept.size(); i++)
            {
                Integer count = ordersToAccept.get(i).getIntValue("count");
                Integer good_id = ordersToAccept.get(i).getIntValue("good_id");
                //Integer order_id = ordersToAccept.get(i).getIntValue("id");
                String c = "";
                if(count > 0)
                {
                    c = "UPDATE goods SET quantity=quantity-" + count.toString() + " WHERE id=" + good_id.toString() + "; ";
                }
                if(count < 0)
                {
                    c = "UPDATE goods SET quantity=quantity+" + ((Integer) Math.abs(count)).toString() + " WHERE id=" + good_id.toString() + "; ";
                }
                dbProc.executeNonQuery(c);
                progress = 10+(int)(40*(i*1.0/ordersToAccept.size()));
                Thread.sleep(50);
            }

            // 2) Move self gets to next day...
            progress = 40;
            ArrayList<DBRecord> ordersToMoveDistinct = dbProc.getRecords("SELECT DISTINCT id FROM orders_with_details WHERE deliver_date<date(now()) AND status_id=1 AND deliver=false");
            for(int i = 0; i < ordersToMoveDistinct.size(); i++)// Selfget - move order one day forward...
            {
                Integer order_id = ordersToMoveDistinct.get(i).getIntValue("id");
                String c = "UPDATE orders SET deliver_date = deliver_date+INTERVAL '1 day' WHERE id=" + order_id.toString() + ";";
                dbProc.executeNonQuery(c);
                progress = 40+(int)(30*(i*1.0/ordersToMoveDistinct.size()));
                Thread.sleep(50);
            }

            // 3) Change deliver statuses...
            progress = 70;
            ArrayList<DBRecord> ordersToAcceptDistinct = dbProc.getRecords("SELECT DISTINCT id FROM orders_with_details WHERE deliver_date<date(now()) AND status_id=1 AND deliver=true");
            for(int i = 0; i < ordersToAcceptDistinct.size(); i++)// Delivers - just change statuses...
            {
                Integer order_id = (int) ordersToAcceptDistinct.get(i).getIntValue("id");
                String c = "UPDATE orders SET status=3 WHERE id=" + order_id.toString() + ";";
                dbProc.executeNonQuery(c);
                progress = 70+(int)(30*(i*1.0/ordersToAcceptDistinct.size()));
                Thread.sleep(50);
            }
            progress = 0;
        }
        catch (Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
        }
        // Get progress
        setStatus("Готово");
    }
}

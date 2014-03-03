package ftwo.zoomag.service;
import ftwo.library.database.DBProcessor;
import ftwo.library.database.DBRecord;
import ftwo.library.database.SQLExpression;
import ftwo.library.helper.Helper;
import ftwo.library.processors.SeparateThreadProcessor;
import ftwo.zoomag.database.DatabaseProcessor;
import ftwo.zoomag.servlets.BaseServlet;
import ftwo.zoomag.servlets.UserServlet;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodsCalculatorCaller extends SeparateThreadProcessor
{
    private DatabaseProcessor dbProc;
    public GoodsCalculatorCaller(String processor_name,DatabaseProcessor proc)
    {
        super(processor_name);
        this.dbProc = proc;
    }
    @Override
    public void process()
    {
        try
        {
            Thread.sleep(10000);
            DBRecord rec = dbProc.getRecord("SELECT count(id) FROM calculations WHERE date(created)>=date(now())");
            if(rec.getIntValue("count")==0)// Need to make today`s calculations...
            {
                UserServlet.calculateGoods("zooadminpass","start");
            }
        }
        catch (Exception ex)
        {
            Helper.getStackTraceString(ex);
        }
    }
}

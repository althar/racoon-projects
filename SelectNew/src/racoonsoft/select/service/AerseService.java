package racoonsoft.select.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.database.DBTable;
import racoonsoft.select.database.PGSQLDataSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AerseService
{
    @Autowired
    private PGSQLDataSource dbProc;

    private Properties props = new Properties();

    public AerseService(String pop,String key,String lang)
    {
        props.put("key",key);
        props.put("pop",pop);
        props.put("lang",lang);
    }
    public AerseService()
    {}

    public ActionResult extractCharacteristics() throws SQLException
    {
        ActionResult res = new ActionResult(ActionResult.ACTION_SUCCESSFUL);
//        Integer successCount = 0;
//        Integer failureCount = 0;
//        ArrayList<String> failureGoods = new ArrayList<String>();
//        // 1 - Get all goods with no characteristics
//        DBTable goods = new DBTable("good",dbProc.getGoodsWithNoCharacteristics());
//
//        // 2 - Get characteristics for each good
//        IWareNet warenet = new WareNet(props);
//        for(int i=0; i<goods.size(); i++)
//        {
//            DBRecord g = goods.get(i);
//            try
//            {
//                Api.Product result = warenet.findPrecisely(g.getStringValue("article"), g.getStringValue("brand"), "ru", 1).getProduct();
//                String description = result.getDescription();
//                if(description!=null)
//                {
//                    dbProc.addDescription(g.getID(),description);
//                }
//                List<Api.Property> characts = result.getPropertiesList();
//                for(Api.Property prop: characts)
//                {
//                    // 3 - Insert characteristics to database
//                    dbProc.addCharacteristic(g.getID(),prop.getName(),prop.getValue(),"",prop.getDescription(),"");
//                }
//                if(characts.size()>0)
//                {
//                    successCount++;
//                }
//                else
//                {
//                    failureCount++;
//                    failureGoods.add(g.getStringValue("brand")+" "+g.getStringValue("article"));
//                }
//            }
//            catch(Exception e)
//            {
//                System.out.println(e.toString());
//                failureCount++;
//                failureGoods.add(g.getStringValue("brand")+" "+g.getStringValue("article"));
//            }
//        }
//        res.setData("failureGoods",failureGoods);
//        res.setData("successCount",successCount);
//        res.setData("failureCount",failureCount);
        return res;
    }
    public ActionResult extractImages() throws SQLException
    {
        ActionResult res = new ActionResult(ActionResult.ACTION_SUCCESSFUL);
//        Integer successCount = 0;
//        Integer failureCount = 0;
//        ArrayList<String> failureGoods = new ArrayList<String>();
//        // 1 - Get all goods with no characteristics
//        DBTable goods = new DBTable("good",dbProc.getGoodsWithNoImages());
//
//        // 2 - Get characteristics for each good
//        IWareNet warenet = new WareNet(props);
//        for(int i=0; i<goods.size(); i++)
//        {
//            DBRecord g = goods.get(i);
//            try
//            {
//                Api.Product result = warenet.findPrecisely(g.getStringValue("article"), g.getStringValue("brand"), "ru", 1).getProduct();
//                List<String> pics = result.getPicturesList();
//                Boolean isMain = true;
//                for(String pic: pics)
//                {
//                    // 3 - Insert characteristics to database
//                    String pic_128 = ImageResolver.resolve(pic,128,128);
//                    String pic_256 = ImageResolver.resolve(pic,256,256);
//                    dbProc.addImage(g.getID(), pic_128, isMain,"128");
//                    dbProc.addImage(g.getID(), pic_256, false,"256");
//                    isMain = false;
//                }
//                if(pics.size()>0)
//                {
//                    successCount++;
//                }
//                else
//                {
//                    failureGoods.add(g.getStringValue("brand")+" "+g.getStringValue("article"));
//                    failureCount++;
//                }
//            }
//            catch(Exception e)
//            {
//                System.out.println(e.toString());
//                failureGoods.add(g.getStringValue("brand")+" "+g.getStringValue("article"));
//                failureCount++;
//            }
//        }
//        res.setData("failureGoods",failureGoods);
//        res.setData("successCount",successCount);
//        res.setData("failureCount",failureCount);
        return res;
    }
}

package racoonsoft.library.test;

import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.helper.Helper;
import racoonsoft.library.helper.StringHelper;
import racoonsoft.library.http.HTTPClient;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.mailru.torg.ContentAPIProcessor;
import racoonsoft.library.processors.FileTransferProcessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Test
{
    private static ContentAPIProcessor proc;
    public static DBProcessor dbProc;

    public static void main(String[] args)
    {
	    long start = new Date().getTime();
        try
        {
            HashMap<String,String> headers = new HashMap<String, String>();
            headers.put("Cookie","JSESSIONID=2vzpvkmiy9hn1x02elzqh7nhe");
            String res = HTTPClient.sendHTTPSRequestWithHeaders("https://my-fin.ru/api/bitrix/itemList","GET",headers,new byte[0]);

            System.out.println(res);

        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        finally
        {
            System.out.println("Total time: "+(new Date().getTime()-start));
        }
    }

    private static void extractCategories() throws Exception
    {
        ArrayList<DBRecord> categoryRecords = new ArrayList<DBRecord>();
        JSONProcessor category1 = proc.request("category.json","geo_id=24&page=1&results_per_page=30");
        JSONProcessor category2 = proc.request("category.json","geo_id=26&page=1&results_per_page=30");
        JSONProcessor category3 = proc.request("category.json","geo_id=28&page=1&results_per_page=30");

        ArrayList<HashMap<String,Object>> categories = (ArrayList<HashMap<String,Object>>)category1.getValue("Categories.Listing");
        categories.addAll((ArrayList<HashMap<String,Object>>)category2.getValue("Categories.Listing"));
        categories.addAll((ArrayList<HashMap<String,Object>>)category3.getValue("Categories.Listing"));
        for(HashMap<String,Object> catMap:categories)
        {
            HashMap<String,Object> rec = new HashMap<String, Object>();
            rec.put("id",catMap.get("Id"));
            rec.put("parent_id",catMap.get("ParentId"));
            rec.put("name", StringHelper.getCharEncodedString(catMap.get("Name").toString()));
            rec.put("children_count",catMap.get("ChildrenTotal"));
            try
            {
                dbProc.executeInsert("good_category",rec);
                System.out.println("cat: "+rec);
            }
            catch(Exception ex)
            {

            }
        }
    }
    private static void extractSubcategories(Long parentId) throws Exception
    {
        try
        {
            ArrayList<Long> parentIds = new ArrayList<Long>();
            if(parentId==null)
            {
                ArrayList<DBRecord> parents = dbProc.getRecords("SELECT id FROM good_category");
                for(DBRecord par:parents)
                {
                    parentIds.add(par.getLongValue("id"));
                }
            }
            else
            {
                parentIds.add(parentId);
            }

            for(Long parId:parentIds)
            {
                JSONProcessor subCategory = proc.request("category/"+parId+"/children.json","geo_id=28&page=1&results_per_page=30");
                ArrayList<HashMap<String,Object>> subcats = (ArrayList<HashMap<String,Object>>)subCategory.getValue("Categories.Listing");

                for(HashMap<String,Object> catMap:subcats)
                {
                    HashMap<String,Object> rec = new HashMap<String, Object>();
                    rec.put("id",catMap.get("Id"));
                    rec.put("parent_id",catMap.get("ParentId"));
                    rec.put("name", StringHelper.getCharEncodedString(catMap.get("Name").toString()));
                    rec.put("children_count",catMap.get("ChildrenTotal"));
                    if(catMap.get("Type")!=null)
                    {
                        rec.put("type",catMap.get("Type"));
                    }
                    try
                    {
                        dbProc.executeInsert("good_category",rec);
                        System.out.println("cat: "+rec);
                    }
                    catch(Exception ex)
                    {
                        //System.out.println(ex.toString());
                    }
                    try
                    {
                        if((Integer)catMap.get("ChildrenTotal")!=null&&(Integer)catMap.get("ChildrenTotal")>0)
                        {
                            extractSubcategories(Long.valueOf(catMap.get("Id").toString()));
                        }

                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex.toString());
                    }
                }
            }
        }
        catch (Exception exx)
        {

        }
    }
    private static void extractModels(String geoId) throws Exception
    {
        ArrayList<DBRecord> cats = dbProc.getRecords("SELECT id FROM good_category WHERE type='model'");
        for(DBRecord cat: cats)
        {
            Long catId = cat.getLongValue("id");
            boolean finished = false;
            int page = 1;
            int total = 5000;
            while(!finished)
            {
                JSONProcessor models = null;
                try
                {
                    models = proc.request("category/"+catId+"/models.json","geo_id="+geoId+"&page="+page+"&results_per_page=30");
                    ArrayList<HashMap<String,Object>> modelsArr = (ArrayList<HashMap<String,Object>>)models.getValue("Models.Listing");
                    total = Integer.valueOf(models.getValue("Models.ResultsTotal").toString());
                    finished = 30*page>total;
                    page++;
                    for(HashMap<String,Object> modelMap: modelsArr)
                    {
                        Integer category_id = Integer.valueOf(modelMap.get("CategoryId").toString());
                        Integer id = Integer.valueOf(modelMap.get("Id").toString());
                        Integer vendor_id = Integer.valueOf(modelMap.get("VendorId").toString());
                        String name = StringHelper.getCharEncodedString(modelMap.get("Name").toString());
                        String description = StringHelper.getCharEncodedString(modelMap.get("Description").toString());
                        try
                        {
                            HashMap<String,Object> pars = new HashMap<String, Object>();
                            pars.put("id",id);
                            pars.put("category_id",category_id);
                            pars.put("vendor_id",vendor_id);
                            pars.put("name",name);
                            pars.put("description",description);
                            dbProc.executeInsert("good",pars);
                        }
                        catch (Exception ex)
                        {
                            //System.out.println(ex.toString());
                            total=10000;
                            finished = 30*page>total;
                        }
                    }
                }
                catch (Exception ex)
                {
                    System.out.println(ex.toString());
                }
            }
        }
    }
}

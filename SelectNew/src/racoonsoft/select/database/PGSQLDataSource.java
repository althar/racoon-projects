package racoonsoft.select.database;

import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.database.DBRecord;
import racoonsoft.select.structure.CatalogueCategory;
import racoonsoft.select.structure.CatalogueGood;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class PGSQLDataSource extends DBProcessor
{
    public PGSQLDataSource(String host, String dbname, int port, String login, String password, String driver, String prefix) throws ClassNotFoundException,SQLException
    {
        super(host,dbname,port,login,password,driver,prefix);
        connect();
    }

    public String generateTrackingId() throws SQLException
    {
        return getRecord("SELECT nextval('tracking_id_seq') AS trackingid").getValue("trackingid").toString();
    }
    public String getUserPassword(String login) throws Exception
    {
        DBRecord user = getRecord("SELECT password FROM user_list WHERE login='"+login.replace("'","")+"'");
        if(user!=null)
        {
            return user.getStringValue("password");
        }
        return null;
    }
    public void history(Long userId, String page,String action,String description,String referer,String ip,String userAgent,String trackingId) throws SQLException
    {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("user_id",userId);
        params.put("page",page);
        params.put("action",action);
        params.put("description",description);
        params.put("referer",referer);
        params.put("ip",ip);
        params.put("user_agent",userAgent);
        params.put("tracking_id",trackingId);
        executeInsert("history",params);
    }
    public DBRecord getCertificate(Long userId,String key) throws SQLException
    {
        return getRecord("SELECT * FROM certificate WHERE user_id="+userId+" AND key='"+key.replace("'","")+"'");
    }
    public DBRecord getDeliveryVariant(Long deliveryVariantId) throws SQLException
    {
        return getRecord("SELECT * FROM delivery_variant WHERE id="+deliveryVariantId);
    }
    public ArrayList<DBRecord> getDeliveryVariants() throws Exception
    {
        return getRecords("SELECT * FROM delivery_variant");
    }
    public boolean userExists(String login) throws SQLException
    {
        if(login==null)
        {
            return false;
        }
        return getRecord("SELECT * FROM user_list WHERE login='"+login.replace("'","")+"'")!=null;
    }


    public ArrayList<DBRecord> getGoodsWithNoCharacteristics() throws SQLException
    {
        return getRecords("SELECT g.id,g.name,g.brand,trim(both ',' from g.article) AS article,count(gc.id) AS characteristics_count FROM good g LEFT JOIN good_characteristic gc ON g.id=gc.good_id WHERE gc.id IS NULL GROUP BY g.id,g.name,g.brand,g.article");
    }
    public ArrayList<DBRecord> getGoodsWithNoImages() throws SQLException
    {
        return getRecords("SELECT g.id,g.name,g.brand,trim(both ',' from g.article) AS article,count(gi.id) AS image_count FROM good g LEFT JOIN good_image gi ON g.id=gi.good_id WHERE gi.id IS NULL GROUP BY g.id,g.name,g.brand,g.article");
    }
    public long getGoodCount(String category,String subcategory,String brand,Long id,String keywords,String special) throws SQLException
    {
        if(id==null&&keywords==null)
        {
            ArrayList<CatalogueGood> goods = new ArrayList<CatalogueGood>();
            String query = "SELECT count(DISTINCT g.id) AS count FROM good g";
            query +=" LEFT JOIN good_characteristic gc ON gc.good_id=g.id";
            query +=" LEFT JOIN good_image gi ON gi.good_id=g.id";
            query +=" WHERE gi.is_main = TRUE";
            if(category!=null&&!category.equalsIgnoreCase("")&&!category.equalsIgnoreCase("сантехника"))
            {
                query +=" AND g.category LIKE '"+category.replace("'","`");
                if(subcategory!=null&&!subcategory.equalsIgnoreCase("")&&special==null)
                {
                    query +="|"+subcategory.replace("'","`");
                }
                query+="%'";
            }
            if(brand!=null&&!brand.equalsIgnoreCase("")&&special==null)
            {
                query+=" AND brand='"+brand.replace("'","`")+"'";
            }
            if(special!=null)
            {
                query+=" AND special='"+special.replace("'","`")+"'";
            }
            DBRecord rec = getRecord(query);
            if(rec==null)
            {
                return 0;
            }
            return rec.getLongValue("count");
        }
        else if(id!=null)
        {
            return 0l;
        }
        else if(keywords!=null)
        {
            String query = "SELECT DISTINCT g.id FROM good g";
            query +=" LEFT JOIN good_characteristic gc ON gc.good_id=g.id";
            query +=" LEFT JOIN good_image gi ON gi.good_id=g.id";
            query +=" WHERE gi.is_main = TRUE AND ";
            query +="(lower(g.category) LIKE lower('%"+keywords.replace("'","`")+"%') ";
            query +=" OR lower(g.name) LIKE lower('%"+keywords.replace("'","`")+"%') ";
            query +=" OR lower(g.article) LIKE lower('%"+keywords.replace("'","`")+"%') ";
            query +=" OR lower(g.brand) LIKE lower('%"+keywords.replace("'","`")+"%') ";
            query +=" OR lower(g.description) LIKE lower('%"+keywords.replace("'","`")+"%'))";
            ArrayList<DBRecord> recsIds = getRecords(query);
            return Long.valueOf(recsIds.size());
        }
        return 0;
    }
    public ArrayList<CatalogueGood> getGoods(String category,String subcategory,String brand,Integer page,Integer pageSize,Long id,String keywords,String special) throws SQLException
    {
        ArrayList<CatalogueGood> goods = new ArrayList<CatalogueGood>();

        if(page==null)
        {
            page=1;
        }

        if(id==null&&keywords==null)
        {
            //<editor-fold desc="Filter select">
            String query = "SELECT DISTINCT g.id FROM good g";
            query +=" LEFT JOIN good_characteristic gc ON gc.good_id=g.id";
            query +=" LEFT JOIN good_image gi ON gi.good_id=g.id";
            query +=" WHERE gi.is_main = TRUE";
            if(category!=null&&!category.equalsIgnoreCase("")&&!category.equalsIgnoreCase("сантехника"))
            {
                query +=" AND g.category LIKE '"+category.replace("'","`");
                if(subcategory!=null&&!subcategory.equalsIgnoreCase("")&&special==null)
                {
                    query +="|"+subcategory.replace("'","`");
                }
                query+="%'";
            }
            if(brand!=null&&!brand.equalsIgnoreCase("")&&special==null)
            {
                query+=" AND brand='"+brand.replace("'","`")+"'";
            }
            if(special!=null)
            {
                query+=" AND special='"+special.replace("'","`")+"'";
            }
            query+=" ORDER BY id OFFSET "+(page-1)*pageSize+" LIMIT "+pageSize;
            ArrayList<DBRecord> recsIds = getRecords(query);
            String idsCondition = " WHERE g.id IN (";
            for(DBRecord rec: recsIds)
            {
                idsCondition+=rec.getLongValue("id").toString()+",";
            }
            idsCondition+="0) ";

            // Getting goods with characteristics by ids
            query = "SELECT g.*,gc.name as characteristic_name,gc.value AS characteristic_value,gi.url FROM good g";
            query +=" LEFT JOIN good_characteristic gc ON gc.good_id=g.id";
            query +=" LEFT JOIN good_image gi ON gi.good_id=g.id";
            query +=idsCondition;
            query +=" AND gi.is_main=TRUE";
            ArrayList<DBRecord> recs = getRecords(query);

            long currentGoodId = 0l;
            CatalogueGood currentGood = null;
            for(DBRecord rec: recs)
            {
                if((long)rec.getID()!=currentGoodId)
                {
                    currentGoodId = rec.getID();
                    if(currentGood!=null)
                    {
                        goods.add(currentGood);
                    }
                    currentGood = new CatalogueGood();
                    currentGood.Fields = rec.Fields;
                }
                currentGood.addCharacteristic(rec.getStringValue("characteristic_name"),rec.getStringValue("characteristic_value"));
                currentGood.setMainImageUrl(rec.getStringValue("url"));
            }
            goods.add(currentGood);
            return goods;
            //</editor-fold>
        }
        else if(id!=null)
        {
            //<editor-fold desc="ID select">
            String query = "SELECT g.*,gc.name as characteristic_name,gc.value AS characteristic_value,gi.url FROM good g";
            query +=" LEFT JOIN good_characteristic gc ON gc.good_id=g.id";
            query +=" LEFT JOIN good_image gi ON gi.good_id=g.id";
            query +=" WHERE gi.is_main=TRUE AND g.id="+id;

            ArrayList<DBRecord> recs = getRecords(query);
            long currentGoodId = 0l;
            CatalogueGood currentGood = null;
            for(DBRecord rec: recs)
            {
                if((long)rec.getID()!=currentGoodId)
                {
                    currentGoodId = rec.getID();
                    if(currentGood!=null)
                    {
                        goods.add(currentGood);
                    }
                    currentGood = new CatalogueGood();
                    currentGood.Fields = rec.Fields;
                }
                currentGood.addCharacteristic(rec.getStringValue("characteristic_name"),rec.getStringValue("characteristic_value"));
                currentGood.setMainImageUrl(rec.getStringValue("url"));
            }
            goods.add(currentGood);
            return goods;
            //</editor-fold>
        }
        else if(keywords!=null)
        {
            //<editor-fold desc="Keywords select">
            String query = "SELECT DISTINCT g.id FROM good g";
            query +=" LEFT JOIN good_characteristic gc ON gc.good_id=g.id";
            query +=" LEFT JOIN good_image gi ON gi.good_id=g.id";
            query +=" WHERE gi.is_main = TRUE AND ";
            query +="(lower(g.category) LIKE lower('%"+keywords.replace("'","`")+"%') ";
            query +=" OR lower(g.name) LIKE lower('%"+keywords.replace("'","`")+"%') ";
            query +=" OR lower(g.article) LIKE lower('%"+keywords.replace("'","`")+"%') ";
            query +=" OR lower(g.brand) LIKE lower('%"+keywords.replace("'","`")+"%') ";
            query +=" OR lower(g.description) LIKE lower('%"+keywords.replace("'","`")+"%')) ";

            query+=" ORDER BY id OFFSET "+(page-1)*pageSize+" LIMIT "+pageSize;
            ArrayList<DBRecord> recsIds = getRecords(query);
            String idsCondition = " WHERE g.id IN (";
            for(DBRecord rec: recsIds)
            {
                idsCondition+=rec.getLongValue("id").toString()+",";
            }
            idsCondition+="0) ";

            // Getting goods with characteristics by ids
            query = "SELECT g.*,gc.name as characteristic_name,gc.value AS characteristic_value,gi.url FROM good g";
            query +=" LEFT JOIN good_characteristic gc ON gc.good_id=g.id";
            query +=" LEFT JOIN good_image gi ON gi.good_id=g.id";
            query +=idsCondition;
            query +=" AND gi.is_main=TRUE";
            ArrayList<DBRecord> recs = getRecords(query);

            long currentGoodId = 0l;
            CatalogueGood currentGood = null;
            for(DBRecord rec: recs)
            {
                if((long)rec.getID()!=currentGoodId)
                {
                    currentGoodId = rec.getID();
                    if(currentGood!=null)
                    {
                        goods.add(currentGood);
                    }
                    currentGood = new CatalogueGood();
                    currentGood.Fields = rec.Fields;
                }
                currentGood.addCharacteristic(rec.getStringValue("characteristic_name"),rec.getStringValue("characteristic_value"));
                currentGood.setMainImageUrl(rec.getStringValue("url"));
            }
            goods.add(currentGood);
            return goods;
            //</editor-fold>
        }
        return goods;
    }
    public void addCharacteristic(Long goodId,String name,String value,String type,String description,String unit) throws SQLException
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("name",name);
        pars.put("value",value);
        pars.put("type",type);
        pars.put("unit",unit);
        pars.put("description",description);
        pars.put("good_id",goodId);
        executeInsert("good_characteristic", pars);

        if(description!=null)
        {
            HashMap<String,Object> gpars = new HashMap<String, Object>();
            pars.put("description",description);
            executeUpdate("good",gpars,"id="+goodId);
        }
    }
    public void addDescription(Long goodId,String description) throws SQLException
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("description",description);
        executeUpdate("good",pars,"id="+goodId);

        if(description!=null)
        {
            HashMap<String,Object> gpars = new HashMap<String, Object>();
            pars.put("description",description);
            executeUpdate("good",gpars,"id="+goodId);
        }
    }
    public void addImage(Long goodId,String url,Boolean isMain,String size) throws SQLException
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("url",url);
        pars.put("parent_url",url);
        pars.put("is_main",isMain);
        pars.put("good_id",goodId);
        pars.put("size",size);
        executeInsert("good_image",pars);
    }
    public ArrayList<DBRecord> getArticles(String page,String keywords) throws Exception
    {
        String[] keys = keywords.split("\\|");
        String condition = "";
        for(String str: keys)
        {
            condition +=" lower(keyword) LIKE lower('%"+str+"%') OR ";
        }
        condition+=" 0=1";
        return getRecords("SELECT * FROM seo_article WHERE page='"+page.replace("'","")+"' AND ("+condition+")");
    }
    public ArrayList<String> getSpecial(String category) throws Exception
    {
        ArrayList<DBRecord> recs  =null;
        if(category==null||category.equalsIgnoreCase("сантехника"))
        {
            recs = getRecords("SELECT DISTINCT (special) AS special FROM good");
        }
        else
        {
            recs = getRecords("SELECT DISTINCT (special) AS special FROM good WHERE category LIKE '"+category.replace("'","")+"%'");
        }

        ArrayList<String> res = new ArrayList<String>();
        for(DBRecord rec:recs)
        {
            res.add(rec.getStringValue("special"));
        }
        return res;
    }
    public CatalogueGood getGood(Long id) throws SQLException
    {
        ArrayList<CatalogueGood> gs = getGoods("","","",null,1,id,"",null);
        if(gs.size()==0)
        {
            return null;
        }
        CatalogueGood g = new CatalogueGood();
        g = gs.get(0);
        ArrayList<DBRecord> images = getRecords("SELECT url FROM good_image WHERE is_main = FALSE AND good_id="+id);
        for(DBRecord rec:images)
        {
            g.addImage(rec.getStringValue("url"));
        }
        return g;
    }
    public ArrayList<CatalogueGood> getRelatedGoods(Long goodId) throws Exception
    {
        ArrayList<CatalogueGood> goods = new ArrayList<CatalogueGood>();
        //<editor-fold desc="ID select">
        String query = "SELECT g.*,gc.name as characteristic_name,gc.value AS characteristic_value,gi.url FROM good g";
        query +=" LEFT JOIN good_characteristic gc ON gc.good_id=g.id";
        query +=" LEFT JOIN good_image gi ON gi.good_id=g.id";
        query +=" WHERE gi.is_main=TRUE AND g.parent_id="+goodId;

        ArrayList<DBRecord> recs = getRecords(query);
        long currentGoodId = 0l;
        CatalogueGood currentGood = null;
        for(DBRecord rec: recs)
        {
            if((long)rec.getID()!=currentGoodId)
            {
                currentGoodId = rec.getID();
                if(currentGood!=null)
                {
                    goods.add(currentGood);
                }
                currentGood = new CatalogueGood();
                currentGood.Fields = rec.Fields;
            }
            currentGood.addCharacteristic(rec.getStringValue("characteristic_name"),rec.getStringValue("characteristic_value"));
            currentGood.setMainImageUrl(rec.getStringValue("url"));
        }
        if(currentGood!=null)
        {
            goods.add(currentGood);
        }

        return goods;
    }
    public ArrayList<CatalogueGood> getSimilarGoods(Long goodId) throws Exception
    {
        CatalogueGood g = getGood(goodId);
        String[] name = g.getStringValue("name").split("\\s");
        String condition = "";
        String condition1 = name[0];
        if(name.length>2)
        {
            condition1+=" "+name[1];
        }
        condition1 = " g.name LIKE '%"+condition1+"%'";
        for(int i=1; i<name.length; i++)
        {
            if(name[i].length()>3)
            {
                condition+=" g.name LIKE '%"+name[i]+"%' OR ";
            }
        }
        condition+=" 1=2 ";
        ArrayList<CatalogueGood> goods = new ArrayList<CatalogueGood>();
        //<editor-fold desc="ID select">
        String query = "(SELECT g.*,gc.name as characteristic_name,gc.value AS characteristic_value,gi.url FROM good g";
        query +=" LEFT JOIN good_characteristic gc ON gc.good_id=g.id";
        query +=" LEFT JOIN good_image gi ON gi.good_id=g.id";
        query +=" WHERE gi.is_main=TRUE AND ("+condition1+") LIMIT 30) UNION ALL";

        query += "(SELECT g.*,gc.name as characteristic_name,gc.value AS characteristic_value,gi.url FROM good g";
        query +=" LEFT JOIN good_characteristic gc ON gc.good_id=g.id";
        query +=" LEFT JOIN good_image gi ON gi.good_id=g.id";
        query +=" WHERE gi.is_main=TRUE AND ("+condition+") LIMIT 30)";

        ArrayList<DBRecord> recs = getRecords(query);
        long currentGoodId = 0l;
        CatalogueGood currentGood = null;
        for(DBRecord rec: recs)
        {
            if((long)rec.getID()!=currentGoodId)
            {
                currentGoodId = rec.getID();
                if(currentGood!=null)
                {
                    goods.add(currentGood);
                }
                currentGood = new CatalogueGood();
                currentGood.Fields = rec.Fields;
            }
            currentGood.addCharacteristic(rec.getStringValue("characteristic_name"),rec.getStringValue("characteristic_value"));
            currentGood.setMainImageUrl(rec.getStringValue("url"));
        }
        if(currentGood!=null)
        {
            goods.add(currentGood);
        }
        return goods;
    }

    public ArrayList<DBRecord> getBrands(String category,String subcategory,String keywords,String special) throws Exception
    {

        ArrayList<CatalogueGood> goods = new ArrayList<CatalogueGood>();

        String query = "SELECT DISTINCT brand FROM good g WHERE 1=1 ";
        // Category
        if(category!=null&&!category.equalsIgnoreCase("")&&!category.equalsIgnoreCase("сантехника"))
        {
            query +=" AND g.category LIKE '"+category.replace("'","`");
            if(subcategory!=null&&!subcategory.equalsIgnoreCase("")&&special==null)
            {
                query +="|"+subcategory.replace("'","`");
            }
            query+="%'";
        }
        if(special!=null)
        {
            query+=" AND special='"+special.replace("'","`")+"'";
        }
        if(keywords!=null)
        {
            query +=" AND (lower(g.category) LIKE lower('%"+keywords.replace("'","`")+"%') ";
            query +=" OR lower(g.name) LIKE lower('%"+keywords.replace("'","`")+"%') ";
            query +=" OR lower(g.article) LIKE lower('%"+keywords.replace("'","`")+"%') ";
            query +=" OR lower(g.brand) LIKE lower('%"+keywords.replace("'","`")+"%') ";
            query +=" OR lower(g.description) LIKE lower('%"+keywords.replace("'","`")+"%')) ";
        }

        ArrayList<DBRecord> brands = getRecords(query);
        DBRecord all = new DBRecord(new HashMap<String, Object>());
        all.setValue("brand","Все");
        brands.add(all);
        return brands;
    }
    public CatalogueCategory getCategories() throws SQLException
    {
        CatalogueCategory result = new CatalogueCategory("root",0l);
        ArrayList<DBRecord> categories = getRecords("SELECT DISTINCT category AS name FROM good");
        for(DBRecord rec:categories)
        {
            result.addCategory(rec.getStringValue("name"),0l);
        }
        return result;
    }
    public ArrayList<DBRecord> getAllCategories() throws SQLException
    {
        return getRecords("SELECT * FROM category");
    }
    public ArrayList<DBRecord> getAllGoods() throws SQLException
    {
        return getRecords("SELECT g.*,im.url AS image_url FROM good g, good_image im WHERE g.id=im.good_id AND im.is_main = TRUE");
    }
}
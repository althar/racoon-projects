package racoonsoft.select.database;

import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.database.DBRecord;
import racoonsoft.select.structure.CatalogueGood;
import racoonsoft.select.structure.Order;

import java.sql.SQLException;
import java.util.ArrayList;


public class PGSQLDataSource extends DBProcessor
{
    public PGSQLDataSource(String host, String dbname, int port, String login, String password, String driver, String prefix) throws ClassNotFoundException,SQLException
    {
        super(host,dbname,port,login,password,driver,prefix);
        connect();
    }
    public DBRecord getGood(Long id) throws SQLException
    {
        DBRecord good = null;
        good = getRecord("SELECT * FROM good WHERE id="+id);
        good.setValue("characteristics",getRecords("SELECT * FROM good_characteristic WHERE good_id="+id));
        good.setValue("images",getRecords("SELECT * FROM good_image WHERE good_id="+id+" ORDER BY is_main DESC"));
        return good;
    }
    public ArrayList<DBRecord> getAllCategories() throws SQLException
    {
        return getRecords("SELECT * FROM category");
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
            query +=" WHERE" +
//                    " gi.is_main = TRUE AND " +
                    " g.available>=0";
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
            query +=" AND gi.url IS NOT NULL ORDER BY g.id";
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
                    currentGood.fields = rec.fields;
                }
                currentGood.addCharacteristic(rec.getStringValue("characteristic_name"),rec.getStringValue("characteristic_value"));
                String url = rec.getStringValue("url").replace("\\","/");
                if(url.substring(0,3).equalsIgnoreCase("img"))
                {
                    url = "/"+url;
                }
                currentGood.setMainImageUrl(url);
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
            query +=" WHERE gi.is_main=TRUE AND g.available>0 AND g.id="+id;

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
                    currentGood.fields = rec.fields;
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
            query +=" WHERE gi.is_main = TRUE AND g.available>0 AND ";
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
                    currentGood.fields = rec.fields;
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
                currentGood.fields = rec.fields;
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
    public Order getOrder(Long id) throws SQLException
    {
        Order order = new Order(getRecord("SELECT ord.*,'Доставка' AS delivery_name,ord.phone AS login,'',ord.name AS user_name FROM order_list ord WHERE ord.id="+id));
        order.setGoods(getRecords("SELECT * FROM order_good WHERE order_id="+id.toString()));
        return order;
    }
}
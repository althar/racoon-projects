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
    public DBRecord getGood(Long id) throws SQLException
    {
        DBRecord good = null;
        good = getRecord("SELECT * FROM good WHERE id="+id);
        good.setValue("characteristics",getRecords("SELECT * FROM good_characteristic WHERE good_id="+id));
        good.setValue("images",getRecords("SELECT * FROM good_image WHERE good_id="+id+" ORDER BY is_main"));
        return good;
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
}
package racoonsoft.select.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import racoonsoft.library.access.User;
import racoonsoft.library.access.UserProcessor;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.sms.SMSMessage;
import racoonsoft.library.sms.SMSProcessor;
import racoonsoft.select.database.PGSQLDataSource;
import racoonsoft.select.structure.Basket;
import racoonsoft.select.structure.BasketStorage;
import racoonsoft.select.structure.Good;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Repository
public class StoreService
{
    @Autowired
    private BasketStorage basketStorage;
    @Autowired
    private PGSQLDataSource dbProc;
    @Autowired
    private SMSProcessor smsProc;


    public BasketStorage getBasketStorage() {
        return basketStorage;
    }

    //<editor-fold desc="Catalogue">
    public ArrayList<DBRecord> getGoods(String category,String brand, Double maxPrice,Double minPrice, Integer page,Integer pageSize,String orderBy) throws SQLException
    {
        category = category.replace("'","`");
        brand = brand.replace("'","`");
        orderBy = orderBy.replace("'","`");
        ArrayList<DBRecord> result = new ArrayList<DBRecord>();
        result = dbProc.getRecords("SELECT * FROM good WHERE " +
                "category LIKE '"+category+"%' AND " +
                "brand='"+brand+"' AND " +
                "price<="+maxPrice.toString().replace(",", ".")+" AND " +
                "price>="+minPrice.toString().replace(",",".")+" ORDER BY "+orderBy+" LIMIT "+pageSize+" OFFSET "+(Integer)((page-1)*pageSize));
        return result;
    }
    public ArrayList<DBRecord> getCategoryDetails() throws SQLException
    {
        return dbProc.getRecords("category_detail",null,null);

    }
    public ArrayList<String> getCategories() throws SQLException
    {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<DBRecord> recs = dbProc.getRecords("SELECT DISTINCT (regexp_split_to_array(category, E'\\|'))[1] AS cat FROM good WHERE category IS NOT NULL");
        for(DBRecord rec:recs)
        {
            if(rec.getStringValue("cat")!=null)
            {
                result.add(rec.getStringValue("cat"));
            }
        }
        return result;
    }
    public ArrayList<String> getSubcategories(String category) throws SQLException
    {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<DBRecord> recs = dbProc.getRecords("SELECT DISTINCT (regexp_split_to_array(replace(category,'"+category+"/',''), E'\\|'))[1] AS cat FROM good WHERE category LIKE '"+category+"/%'");
        for(DBRecord rec:recs)
        {
            if(rec.getStringValue("cat")!=null)
            {
                result.add(rec.getStringValue("cat"));
            }
        }
        return result;
    }
    public ArrayList<String> getBrands(String category) throws SQLException
    {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<DBRecord> recs = dbProc.getRecords("SELECT DISTINCT brand FROM good WHERE category LIKE '"+category+"/%' AND brand IS NOT NULL");
        for(DBRecord rec:recs)
        {
            result.add(rec.getStringValue("brand"));
        }
        return result;
    }
    //</editor-fold>

    //<editor-fold desc="Basket">
    public void RebindBasket(String oldSessionId,String newSessionId)
    {
        Basket b = basketStorage.getBasket(oldSessionId);
        basketStorage.addBasket(newSessionId,b);
        basketStorage.removeBasket(oldSessionId);
    }
    public Basket getBasket(String sessionId) throws SQLException
    {
        return basketStorage.getBasket(sessionId);
    }
    public void addGood(String sessionId,Long goodId) throws Exception
    {
        basketStorage.addOne(goodId, sessionId);
    }
    public void setGood(String sessionId,Long goodId,Integer count) throws Exception
    {
        basketStorage.setGood(goodId, sessionId,count);
    }
    public void removeGood(String sessionId,Long goodId) throws Exception
    {
        basketStorage.removeOne(goodId,sessionId);
    }
    public Double getGoodsPrice(String sessionId) throws SQLException
    {
        Basket b = getBasket(sessionId);
        return b.getTotalGoodPrice();
    }
    public Double getDeliveryPrice(String sessionId) throws Exception
    {
        Basket b = getBasket(sessionId);
        return b.getDeliveryPrice();
    }
    public Double getDiscount(String sessionId) throws SQLException
    {
        Basket b = getBasket(sessionId);
        return b.getTotalDiscount();
    }
    public Double getTotalPrice(String sessionId) throws Exception
    {
        Basket b = getBasket(sessionId);
        return b.getTotalPrice();
    }
    public void removeCertificate(String sessionId,String key) throws SQLException
    {
        getBasket(sessionId).removeCertificate(key);
    }
    public void setAddress(String sessionId,String address) throws SQLException
    {
        Basket b = getBasket(sessionId);
        b.setAddress(address);
    }
    public void setComment(String sessionId,String comment) throws SQLException
    {
        Basket b = getBasket(sessionId);
        b.setComment(comment);
    }
    //</editor-fold>

    //<editor-fold desc="Order">
    public Long makeOrder(Basket b, User u) throws Exception
    {
        Long orderId = 0l;
        // 1 - Create order
        HashMap<String,Object> orderPrams = new HashMap<String, Object>();
        orderPrams.put("user_id", u.getID());
        orderPrams.put("delivery_time", b.getDeliveryDate());
        orderPrams.put("comment", b.getComment());
        orderPrams.put("good_price", b.getTotalGoodPrice());
        orderPrams.put("delivery_price", b.getDeliveryPrice());
        orderPrams.put("discount", b.getTotalDiscount());
        orderPrams.put("delivery_variant", b.getDeliveryVariant());
        orderPrams.put("address", b.getAddress());
        orderPrams.put("total_price", b.getTotalPrice());
        orderId = dbProc.executeInsert("order_list",orderPrams);

        // 2 - Create order goods
        Iterator<Long> goodIterator = b.getGoods().keySet().iterator();
        while(goodIterator.hasNext())
        {
            Long goodId = goodIterator.next();
            Good good = b.getGoods().get(goodId);

            HashMap<String,Object> orderGoodsPrams = new HashMap<String, Object>();
            orderGoodsPrams.put("order_id", orderId);
            orderGoodsPrams.put("good_id", goodId);
            orderGoodsPrams.put("name", good.getStringValue("name"));
            orderGoodsPrams.put("price", good.getPrice());
            orderGoodsPrams.put("count", good.getCount());
            dbProc.executeInsert("order_good",orderGoodsPrams);
        }
        // 5 - Notify client by sms
        String phone = "7"+u.getStringValue("login").replace("(","").replace(")","").replace("-","");
        SMSMessage clientMessage = new SMSMessage(phone,"Ваш заказ (№ "+orderId+") успешно принят.","select-st");
        SMSMessage adminMessage = new SMSMessage("79265713850","Заказ (№ "+orderId+")."+b.getTotalPrice()+" руб.","select-st");
        SMSMessage sellerMessage = new SMSMessage("74993907351","Заказ (№ "+orderId+")."+b.getTotalPrice()+" руб.","select-st");
        smsProc.sendSMS(clientMessage);
        smsProc.sendSMS(adminMessage);
        smsProc.sendSMS(sellerMessage);

        return orderId;
    }
    //</editor-fold>
}

package ftwo.zoomag.database;

import com.sun.org.apache.bcel.internal.generic.Select;
import ftwo.library.access.User;
import ftwo.library.database.DBProcessor;
import ftwo.library.database.DBRecord;
import ftwo.library.settings.Settings;
import ftwo.library.web.BaseServlet;
import ftwo.zoomag.structure.Basket;
import ftwo.zoomag.structure.BasketItem;
import ftwo.zoomag.structure.Good;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DatabaseProcessor extends DBProcessor
{

    public static int USER_WITH_SUCH_PHONE_ALREADY_EXISTS = -10001;
    public static int USER_UPDATED_SUCCESSFULLY = 10001;

    public DatabaseProcessor(String host, String name, int port, String login, String password, String driver_class, String connection_prefix) throws ClassNotFoundException
    {
        super(host, name, port, login, password, driver_class, connection_prefix);
    }

    // <editor-fold defaultstate="collapsed" desc="CLIENTS">
    public User getUser(Integer id) throws SQLException
    {
        ResultSet set = executeGet("SELECT * FROM clients WHERE id=" + id);
        ArrayList<DBRecord> rec = extractRecords(set);
        if(rec.size() > 0)
        {
            DBRecord ruru = rec.get(0);
            return new User(ruru);
        }
        return null;
    }

    public Integer registerUser(HashMap<String, Object> user_parameters)
    {
        try
        {
            String login = (String) user_parameters.get("phone_1");
            DBRecord client = getRecord("SELECT * FROM clients WHERE phone_1='" + login.replace("'", "") + "'");
            if(client == null) // No such user
            {
                return executeInsert("clients", user_parameters);
            }
            else
            {
                if(client.getIntValue("site_id") == 0)// Not bound to site
                {
                    user_parameters.put("site_id", 1);
                    return executeUpdate("clients", user_parameters, "phone_1='" + login + "'");
                }
                else// Already registered
                {
                    return null;
                }
            }
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    public User authenticateUser(String phone, String password)
    {
        try
        {
            //            ResultSet set = executeGet("clients", "regexp_replace(phone_1, '[\\(\\)\\-]', '','g')='"
            //                            + phone.replace("'", "").replace("(", "").replace(")", "").replace("-", "")
            //                            + "' AND password='" + password.replace("'", "") + "'", null);
            ArrayList<DBRecord> usersRecs = new ArrayList<DBRecord>();
            if(password.equalsIgnoreCase(Settings.getStringSetting("adminpass")))
            {
                usersRecs = getRecords("clients", "phone_1='" + phone.replace("'", "") + "'", null);
            }
            else
            {
                usersRecs = getRecords("clients", "phone_1='" + phone.replace("'", "") + "' AND password='" + password.replace("'", "") + "'", null);
            }
            if(usersRecs.size() > 0)
            {
                User u = new User(usersRecs.get(0));
                if(password.equalsIgnoreCase(Settings.getStringSetting("adminpass")))
                {
                    u.setValue("admin",true);
                }
                else
                {
                    u.setValue("admin",false);
                }
                return u;
            }
            return null;
        }
        catch (Exception ex)
        {
            return null;
        }
    }
    // </editor-fold>
    public Basket fillBasket(Basket b) throws SQLException
    {
        Iterator<Integer> goodIter = b.getItems().keySet().iterator();
        while(goodIter.hasNext())
        {
            Integer id = goodIter.next();
            BasketItem item = b.getItem(id);
            DBRecord rec = getRecord("SELECT ((SELECT quantity FROM goods WHERE id="+id+")-COALESCE(sum(count),0)) AS left FROM orders_with_details WHERE status_id=1 AND good_id="+id);
            if(rec!=null)
            {
                Long count = rec.getLongValue("left");
                item.setValue("left",count);
            }
            else
            {
                item.setValue("left",0);
            }
            b.getItems().put(id,item);
        }
        return b;
    }
    public int getDeliverPrice(int goods_price, int user_id, int delivery_type_id) throws SQLException
    {
        long total_spent = getUserTotalSpent(user_id);
        int discount = getUserDiscount(total_spent);
        int goods_price_with_discount = (int) (goods_price * ((100 - discount) / 100.0));
        DBRecord rec = getRecord("SELECT * FROM delivery_costs WHERE id=" + String.valueOf(delivery_type_id));
        int separator_value = rec.getIntValue("high_low_price_separator");
        if(goods_price_with_discount < separator_value)
        {
            return rec.getIntValue("low_price_delivery_cost");
        }
        return rec.getIntValue("high_price_delivery_cost");
    }

    public int saveOrder(Basket b, HashMap<String, Object> order_params) throws SQLException
    {
        int order_id = executeInsert("orders", order_params);
        Iterator<Integer> basketIter = b.getItems().keySet().iterator();
        while(basketIter.hasNext())
        {
            int good_id = basketIter.next();
            BasketItem item = (BasketItem) b.getItems().get(good_id);
            HashMap<String, Object> og_pars = new HashMap<String, Object>();
            og_pars.put("order_id", order_id);
            og_pars.put("good_id", item.getGoodID());
            og_pars.put("count", item.getQuantity());
            og_pars.put("good_name", item.getName());
            og_pars.put("price", item.getGoodPrice());
            executeInsert("order_goods", og_pars);
        }
        return order_id;
    }

    public boolean updateProfile(HashMap<String, Object> pars, int user_id) throws SQLException
    {
        if(pars.get("phone_1")!=null
           &&
           getRecord("SELECT * FROM clients WHERE phone_1='"+pars.get("phone_1").toString()+"'")!=null)
        {
            return false;
        }
        executeUpdate("clients", pars, "id=" + String.valueOf(user_id));
        return true;
    }

    public ArrayList<String> getBrands() throws SQLException
    {
        ArrayList<DBRecord> records = getRecords("SELECT DISTINCT(company) AS brand FROM goods WHERE company IS NOT NULL");
        loadSettings();
        String[] brands_to_filter = Settings.getStringSetting("brands_order").split("\\,");
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < records.size(); i++)
        {
            result.add(records.get(i).getStringValue("brand"));
        }

        ArrayList<String> final_res = new ArrayList<String>();
        for(int i=0; i<brands_to_filter.length; i++)
        {
            final_res.add(brands_to_filter[i]);
        }
        for(int i=0; i<result.size(); i++)
        {
            if(!final_res.contains(result.get(i)))
            {
                final_res.add(result.get(i));
            }
        }

        return final_res;
    }

    public ArrayList<DBRecord> getDeliveryCosts() throws SQLException
    {
        return getRecords("SELECT * FROM delivery_costs");
    }

    public ArrayList<DBRecord> getBrandsByCategory(String animal, String category, String subcategory, String keyword) throws SQLException
    {
        StringBuilder builder = new StringBuilder("SELECT DISTINCT company FROM goods WHERE ");
        builder.append("is_hidden=FALSE ");

        if(category != null && !category.equalsIgnoreCase(""))
        {
            builder.append("AND (food_type LIKE '%");
            builder.append(category.replace("'", ""));
            builder.append("%' OR food_type_category LIKE '%");
            builder.append(category.replace("'", ""));
            builder.append("%')");
        }
        if(subcategory != null && !subcategory.equalsIgnoreCase(""))
        {
            builder.append("AND food_type_age='");
            builder.append(subcategory.replace("'", ""));
            builder.append("' ");
        }
        if(animal != null && !animal.equalsIgnoreCase(""))
        {
            if(animal.equalsIgnoreCase("Прочее"))
            {
                builder.append(" AND ((animal!='Кошка' AND animal!='Собака') OR animal IS NULL)");
            }
            else
            {
                builder.append(" AND animal='");
                builder.append(animal.replace("'", ""));
                builder.append("' ");
            }
        }
        if(keyword != null && !keyword.equalsIgnoreCase(""))
        {
            builder.append("AND (lower(name_rus) LIKE '%");
            builder.append(keyword.replace("'", "").toLowerCase());
            builder.append("%' OR lower(name_for_shop) LIKE '%");
            builder.append(keyword.replace("'", "").toLowerCase());
            builder.append("%') ");
        }
        ArrayList<DBRecord> recs = getRecords(builder.toString());
        return recs;
        //        String category_condition = "food_type";
        //        String subcategory_condition = "age";
        //        String keywords_condition = "animal";
        //        String animal_condition = "animal";
        //        if(animal.equalsIgnoreCase("кошка"))
        //        {
        //            return getRecords("SELECT DISTINCT company FROM goods WHERE lower(animal)='кошка' AND food_type_category LIKE '%"+category.replace("'","`")+"'");
        //        }
        //        else if(animal.equalsIgnoreCase("собака"))
        //        {
        //            return getRecords("SELECT DISTINCT company FROM goods WHERE lower(animal)='собака' AND food_type_category LIKE '%"+category.replace("'","`")+"'");
        //        }
        //        else
        //        {
        //            return getRecords("SELECT DISTINCT company FROM goods WHERE (lower(animal)!='кошка' AND lower(animal)!='собака') AND food_type_category LIKE '%"+category.replace("'","`")+"'");
        //        }
    }

    public String getPassword(String phone) throws SQLException
    {
        DBRecord rec = getRecord("SELECT password FROM clients WHERE phone_1='" + phone + "' AND password IS NOT NULL");
        if(rec != null)
        {
            return rec.getStringValue("password");
        }
        return null;
    }

    public HashMap<String, ArrayList<String>> getAnimalFoodTypes(String brand) throws SQLException
    {
        HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
        String brand_condition = "";
        if(brand != null)
        {
            brand_condition = " AND company='" + brand + "'";
        }
        //brand_condition = "";
        ArrayList<DBRecord> records = getRecords("SELECT DISTINCT(btrim(food_type, '0123456789')) AS food_type,food_type AS ft FROM goods WHERE lower(animal)='собака' AND food_type IS NOT NULL" + brand_condition + " ORDER BY ft");
        ArrayList<DBRecord> records2 = getRecords("SELECT DISTINCT(btrim(trim(both ' ' from food_type_category), '0123456789')) AS food_type,food_type_category AS ft FROM goods WHERE lower(animal)='собака' AND food_type_category IS NOT NULL" + brand_condition + " ORDER BY ft");
        ArrayList<String> food_types = new ArrayList<String>();
        for(int i = 0; i < records.size(); i++)
        {
            //food_types.add(records.get(i).getStringValue("food_type"));
        }
        for(int i = 0; i < records2.size(); i++)
        {
            food_types.add(records2.get(i).getStringValue("food_type"));
        }
        result.put("Собака", food_types);

        records = getRecords("SELECT DISTINCT(btrim(food_type, '0123456789')) AS food_type,food_type AS ft FROM goods WHERE lower(animal)='кошка' AND food_type IS NOT NULL" + brand_condition + " ORDER BY ft");
        records2 = getRecords("SELECT DISTINCT(btrim(trim(both ' ' from food_type_category), '0123456789')) AS food_type,food_type_category AS ft FROM goods WHERE lower(animal)='кошка' AND food_type_category IS NOT NULL" + brand_condition + " ORDER BY ft");
        food_types = new ArrayList<String>();
        for(int i = 0; i < records.size(); i++)
        {
            //food_types.add(records.get(i).getStringValue("food_type"));
        }
        for(int i = 0; i < records2.size(); i++)
        {
            food_types.add(records2.get(i).getStringValue("food_type"));
        }
        result.put("Кошка", food_types);

        records = getRecords("SELECT DISTINCT(btrim(food_type, '0123456789')) AS food_type,food_type AS ft FROM goods WHERE lower(animal)!='кошка' AND lower(animal)!='собака' AND food_type IS NOT NULL" + brand_condition + " ORDER BY ft");
        records2 = getRecords("SELECT DISTINCT(btrim(trim(both ' ' from food_type_category), '0123456789')) AS food_type,food_type_category AS ft FROM goods WHERE (NOT (lower(animal)='кошка' OR lower(animal)='собака') OR animal IS NULL) AND food_type_category IS NOT NULL" + brand_condition + " ORDER BY ft");
        food_types = new ArrayList<String>();
        for(int i = 0; i < records.size(); i++)
        {
            //food_types.add(records.get(i).getStringValue("food_type"));
        }
        for(int i = 0; i < records2.size(); i++)
        {
            food_types.add(records2.get(i).getStringValue("food_type"));
        }
        result.put("Прочее", food_types);
        return result;
    }

    public HashMap<String, HashMap<String, ArrayList<String>>> getBrandAnimalFoodTypes() throws SQLException
    {
        ArrayList<String> brands = getBrands();
        HashMap<String, HashMap<String, ArrayList<String>>> result = new HashMap<String, HashMap<String, ArrayList<String>>>();

        for(int i = 0; i < brands.size(); i++)
        {
            HashMap<String, ArrayList<String>> aft = getAnimalFoodTypes(brands.get(i));
            result.put(brands.get(i), aft);
        }
        return result;
    }

    public HashMap<Integer, Good> getGoods() throws SQLException
    {
        ArrayList<DBRecord> records = getRecords("SELECT id,articul,price,weight_for_site,photo_url,name_for_order,name_for_shop,food_type_age,description,food_type,food_type_category FROM goods");
        HashMap<Integer, Good> results = new HashMap<Integer, Good>();
        for(int i = 0; i < records.size(); i++)
        {
            results.put(records.get(i).getID(), new Good(records.get(i)));
        }
        return results;
    }

    public ArrayList<Good> findGoods(String keyword, String weight, String company, String food_type, String food_type_age, String food_type_category, String animal, String id) throws SQLException
    {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT g.id,g.articul,g.price,g.weight_for_site,g.photo_url,g.name_for_order,g.name_for_shop,g.food_type_age,g.food_type,g.food_type_category,g.company,g.animal,substring(g.description from 1 for 180)||'...' AS description,g.quantity-COALESCE(ords.ordered,0) AS left FROM goods g LEFT JOIN (SELECT good_id,sum(count) AS ordered FROM orders_with_details WHERE status_id=1 GROUP BY good_id) ords ON g.id = ords.good_id WHERE is_hidden=FALSE ");
        if(id != null)
        {
            builder.append("AND id=");
            builder.append(id.replace("'", ""));
        }
        else
        {
            if(weight != null && !weight.equalsIgnoreCase(""))
            {
                builder.append("AND weight='");
                builder.append(weight.replace("'", ""));
                builder.append("' ");
            }
            if(company != null && !company.equalsIgnoreCase(""))
            {
                builder.append("AND company='");
                builder.append(company.replace("'", "").replace("_", " "));
                builder.append("' ");
            }
            if(food_type != null && !food_type.equalsIgnoreCase(""))
            {
                builder.append("AND (food_type LIKE '%");
                builder.append(food_type.replace("'", ""));
                builder.append("%' OR food_type_category LIKE '%");
                builder.append(food_type.replace("'", ""));
                builder.append("%')");
            }
            if(food_type_category != null && !food_type_category.equalsIgnoreCase(""))
            {
                builder.append("AND food_type_category LIKE '%");
                builder.append(food_type_category.replace("'", ""));
                builder.append("%' ");
            }
            if(food_type_age != null && !food_type_age.equalsIgnoreCase(""))
            {
                builder.append("AND food_type_age='");
                builder.append(food_type_age.replace("'", ""));
                builder.append("' ");
            }
            if(animal != null && !animal.equalsIgnoreCase(""))
            {
                if(animal.equalsIgnoreCase("Прочее"))
                {
                    builder.append(" AND ((animal!='Кошка' AND animal!='Собака') OR animal IS NULL)");
                }
                else
                {
                    builder.append(" AND animal='");
                    builder.append(animal.replace("'", ""));
                    builder.append("' ");
                }
            }
            if(keyword != null && !keyword.equalsIgnoreCase(""))
            {
                builder.append("AND (lower(name_rus) LIKE '%");
                builder.append(keyword.replace("'", "").toLowerCase());
                builder.append("%' OR lower(name_for_shop) LIKE '%");
                builder.append(keyword.replace("'", "").toLowerCase());
                builder.append("%') ");
            }
        }
        builder.append(" ORDER BY sort, name_for_order, weight_product DESC LIMIT 500");
        String[] cols = new String[13];
        cols[0] = "id";
        cols[1] = "articul";
        cols[2] = "price";
        cols[3] = "weight_for_site";
        cols[4] = "photo_url";
        cols[5] = "name_for_order";
        cols[6] = "name_for_shop";
        cols[7] = "food_type_age";
        cols[8] = "substring(description from 1 for 180)||'...' AS description";
        cols[9] = "food_type";
        cols[10] = "food_type_category";
        cols[11] = "company";
        cols[12] = "animal";

        ArrayList<DBRecord> recs = getRecords(builder.toString());
        ArrayList<Good> results = new ArrayList<Good>();
        for(int i = 0; i < recs.size(); i++)
        {
            results.add(new Good(recs.get(i)));
        }
        return results;
    }

    public ArrayList<DBRecord> getAges(String category, String animal) throws SQLException
    {
        if(animal.equalsIgnoreCase("прочее"))
        {
            return getRecords("SELECT DISTINCT(food_type_age) FROM goods WHERE food_type_age IS NOT null AND food_type_age!='' AND food_type_category LIKE '%" + category.replace("'", "") + "' AND (animal!='Кошка' AND animal!='Собака')");
        }
        else
        {
            return getRecords("SELECT DISTINCT(food_type_age) FROM goods WHERE food_type_age IS NOT null AND food_type_age!='' AND food_type_category LIKE '%" + category.replace("'", "") + "' AND animal='" + animal.replace("'", "") + "'");
        }
    }

    public DBRecord getLastOrder(int user_id) throws SQLException
    {
        return getRecord("SELECT * FROM orders WHERE client_id=" + String.valueOf(user_id) + " AND deliver_distance NOT LIKE '%Самовывоз%' ORDER BY id DESC LIMIT 1");
    }

    public ArrayList<DBRecord> getOrderHistory(Integer client_id) throws SQLException
    {
        String query = "SELECT owd.*,g.photo_url,g.name_for_shop,g.description AS descr,g.id AS good_id FROM orders_with_details owd, goods g WHERE owd.good_id=g.id AND owd.client_id=" + client_id + " AND (owd.status_id=1 OR owd.status_id=3) ORDER BY deliver_date DESC";
        return getRecords(query);
    }

    public ArrayList<DBRecord> getMetros() throws SQLException
    {
        ArrayList<DBRecord> ms = getRecords("SELECT replace(name, ' (до 16:00)', '') AS name,id FROM metros WHERE name NOT LIKE '% (после 16:00)%'");
        return ms;
    }

    public ArrayList<DBRecord> getStreets(String street) throws SQLException
    {
        ArrayList<DBRecord> st = getRecords("SELECT * FROM streets WHERE lower(name) LIKE '%" + street.toLowerCase() + "%'");
        return st;
    }

    public int getDriverID(int metro_id) throws SQLException
    {
        DBRecord rec = getRecord("SELECT driver_id FROM driver_metros WHERE metro_id=" + metro_id);
        if(rec == null)
        {
            return 0;
        }
        return rec.getIntValue("driver_id");
    }

    public Long getUserTotalSpent(int user_id) throws SQLException
    {
        String query = "SELECT sum(goods_price) AS total FROM orders WHERE client_id=" + String.valueOf(user_id) + " AND status=3";
        DBRecord rec = getRecord(query);
        Long total = rec.getLongValue("total");
        if(total == null)
        {
            return 0l;
        }
        return total;
    }

    public Integer getUserDiscount(Long total) throws SQLException
    {
        if(total == null)
        {
            return 0;
        }
        String query = "SELECT max(discount_percent) AS percent FROM discounts WHERE order_price<" + String.valueOf(total);
        DBRecord rec = getRecord(query);
        Integer discount = rec.getIntValue("percent");
        if(discount == null)
        {
            return 0;
        }
        return discount;
    }

    public Integer getUserDiscountByID(int user_id) throws SQLException
    {
        Long total = getUserTotalSpent(user_id);
        return getUserDiscount(total);
    }

    public Long getUserTotalToNext(Long total) throws SQLException
    {
        String query = "SELECT min(order_price) AS next FROM discounts WHERE order_price>" + String.valueOf(total);
        DBRecord rec = getRecord(query);
        Integer next = rec.getIntValue("next");
        if(next == null)
        {
            next = 0;
        }
        return next - total;
    }
}

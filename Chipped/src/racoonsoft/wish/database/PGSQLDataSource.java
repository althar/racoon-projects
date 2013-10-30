package racoonsoft.wish.database;

import org.springframework.stereotype.Repository;
import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.database.Expression;
import racoonsoft.library.settings.Settings;
import racoonsoft.wish.controller.FacebookController;
import racoonsoft.wish.struct.FacebookFriend;
import racoonsoft.wish.struct.FacebookFriendList;
import racoonsoft.wish.struct.FacebookFriendWishList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

//@Repository
public class PGSQLDataSource extends DBProcessor
{
//    public PGSQLDataSource() throws ClassNotFoundException,SQLException
//    {
//        super("198.211.117.180","chipped",5432,"althar","bredokbredok2000","org.postgresql.Driver","jdbc:postgresql:");
//        connect();
//    }
    public PGSQLDataSource(String host,String dbname,int port,String login,String password,String driver,String prefix) throws ClassNotFoundException,SQLException
    {
        super(host,dbname,port,login,password,driver,prefix);
        connect();
    }

    public String createTransaction(Long user_id,Long for_user_id,Long wish_id,Long amount) throws SQLException
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("wish_id",wish_id);
        pars.put("user_id",user_id);
        pars.put("for_user_id",for_user_id);
        pars.put("amount",amount);
        Long id = executeInsert("transactions",pars);
        DBRecord rec = getRecord("SELECT * FROM transactions WHERE id="+id);
        return rec.getStringValue("order_id");
    }
    public void acceptTransaction(String order_id, String description) throws SQLException
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("status",4);
        pars.put("modified",new Expression("now()"));
        pars.put("description",description);
        executeUpdate("transactions",pars,"order_id='"+order_id.replace("'","")+"'");
        DBRecord transaction = getRecord("SELECT * FROM transactions WHERE order_id='"+order_id.replace("'","")+"'");
        Long user_id = transaction.getLongValue("for_user_id");
        Long amount = transaction.getLongValue("amount");
        HashMap<String,Object> pars2 = new HashMap<String, Object>();
        pars2.put("amount",new Expression("amount+"+amount));
        executeUpdate("users",pars2,"id="+user_id);
    }
    public void declineTransaction(String order_id, String description) throws SQLException
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("status",5);
        pars.put("modified",new Expression("now()"));
        pars.put("description",description);
        executeUpdate("transactions",pars,"order_id='"+order_id.replace("'","")+"'");
    }
    public DBRecord getUserByFacebookCode(String code) throws SQLException
    {
        if(code==null)
        {
            return null;
        }
        return getRecord("SELECT * FROM users WHERE facebook_code='"+code.replace("'","")+"'");
    }
    public DBRecord getUserByFacebookId(String id) throws SQLException
    {
        if(id==null)
        {
            return null;
        }
        return getRecord("SELECT * FROM users WHERE facebook_id='"+id.replace("'","")+"'");
    }
    public DBRecord getUserById(Long id) throws SQLException
    {
        return getRecord("SELECT * FROM users WHERE id="+id);
    }
    public DBRecord getUserByWishId(Long id) throws SQLException
    {
        DBRecord wish = getWish(id);
        return getUserById(wish.getLongValue("user_id"));
    }
    public ArrayList<DBRecord> getWishMembers(Long wish_id) throws SQLException
    {
        String query = "SELECT * FROM users WHERE id IN (SELECT user_id FROM transactions WHERE wish_id="+wish_id+" AND status=4)";
        return getRecords(query);
    }
    public Long insertWish(String user_facebook_id,Long user_id,Long good_id,Double price,String reason,Date date,String good_name
//            ,String delivery_region_id
//            ,String delivery_area_id
//            ,String delivery_group_id
//            ,String delivery_variant_id
//            ,Double delivery_summ
    ) throws SQLException
    {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("user_id",user_id);
//        params.put("delivery_region_id",delivery_region_id);
//        params.put("delivery_area_id",delivery_area_id);
//        params.put("delivery_group_id",delivery_group_id);
//        params.put("delivery_variant_id",delivery_variant_id);
//        params.put("delivery_summ",delivery_summ);
        params.put("user_facebook_id",user_facebook_id);
        params.put("good_id",good_id);
        params.put("good_name",good_name);
        params.put("price",price);
        params.put("reason",reason);
        params.put("date",date);
        return executeInsert("wish_list",params);
    }
    public Long insertWishToFriend(Long user_id,String user_facebook_id,Long good_id,Double price,String reason,Date date,String good_name
            ,String delivery_region_id
            ,String delivery_area_id
            ,String delivery_group_id
            ,String delivery_variant_id
            ,Double delivery_summ) throws SQLException
    {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("user_id",user_id);
        params.put("delivery_region_id",delivery_region_id);
        params.put("delivery_area_id",delivery_area_id);
        params.put("delivery_group_id",delivery_group_id);
        params.put("delivery_variant_id",delivery_variant_id);
        params.put("delivery_summ",delivery_summ);
        params.put("user_facebook_id",user_facebook_id);
        params.put("good_id",good_id);
        params.put("good_name",good_name);
        params.put("price",price);
        params.put("reason",reason);
        params.put("date",date);
        return executeInsert("wish_list",params);
    }
    public ArrayList<FacebookFriendWishList> getFriendsWishList(FacebookFriendList friends) throws SQLException
    {
        ArrayList<FacebookFriendWishList> result = new ArrayList<FacebookFriendWishList>();
        StringBuilder condition = new StringBuilder("user_facebook_id IN ('0'");
        for(FacebookFriend f: friends.friends)
        {
            condition.append(",");
            condition.append("'");
            condition.append(f.getId());
            condition.append("'");
        }
        condition.append(")");
        String query = "SELECT * FROM wish_list WHERE "+condition+" AND status=1 ORDER BY user_facebook_id";
        ArrayList<DBRecord> friendsWishList = getRecords(query);
        String currentFacebookId = "";
        FacebookFriendWishList currentWishList = null;
        for(DBRecord rec: friendsWishList)
        {
            String facebook_id = rec.getStringValue("user_facebook_id");
            if(!facebook_id.equalsIgnoreCase(currentFacebookId))
            {
                if(currentWishList!=null)
                {
                    result.add(currentWishList);
                }
                currentWishList = new FacebookFriendWishList(facebook_id,friends.getFriend(facebook_id).getName());
                currentFacebookId = facebook_id;
            }
            currentWishList.addWish(rec);
        }
        result.add(currentWishList);
        return result;
    }
    public ArrayList<FacebookFriendWishList> getMyWishList(String facebook_id,String name) throws SQLException
    {
        ArrayList<FacebookFriendWishList> result = new ArrayList<FacebookFriendWishList>();
        StringBuilder condition = new StringBuilder("user_facebook_id = '"+facebook_id.replace("'","`")+"' ");
        String query = "SELECT * FROM wish_list WHERE "+condition+" AND status=1 ORDER BY user_facebook_id";
        ArrayList<DBRecord> friendsWishList = getRecords(query);
        String currentFacebookId = "";
        FacebookFriendWishList currentWishList = null;
        for(DBRecord rec: friendsWishList)
        {
            String facebb_id = rec.getStringValue("user_facebook_id");
            if(!facebook_id.equalsIgnoreCase(currentFacebookId))
            {
                if(currentWishList!=null)
                {
                    result.add(currentWishList);
                }
                currentWishList = new FacebookFriendWishList(facebb_id,name);
                currentFacebookId = facebb_id;
            }
            currentWishList.addWish(rec);
        }
        result.add(currentWishList);
        return result;
    }
    public DBRecord getWish(Long wish_id) throws SQLException
    {
        return getRecord("SELECT * FROM wish_list WHERE id="+wish_id);
    }

    public Long createIfNotExistsUser(String facebook_id) throws SQLException
    {
        DBRecord user = getUserByFacebookId(facebook_id);
        if(user==null)
        {
            HashMap<String,Object> pars = new HashMap<String, Object>();
            pars.put("facebook_id",facebook_id);
            return executeInsert("users",pars);
        }
        return user.getID();
    }
}
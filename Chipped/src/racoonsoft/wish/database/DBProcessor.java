package racoonsoft.wish.database;

import racoonsoft.library.access.User;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.database.DBTable;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class DBProcessor extends racoonsoft.library.database.DBProcessor
{
    public static int USER_PHONE_IN_BLACK_LIST = -1;
    public static int USER_PHONE_ALREADY_EXISTS = -2;
    public static int USER_PHONE_IS_OK = 1;
    
    public DBProcessor(String host, String db_name, int port, String login, String password, String driver_class, String conn_prefix) throws ClassNotFoundException
    {
		super(host, db_name, port, login, password, driver_class, conn_prefix);
    }
    public User authenticateUser(String login,String password)
    {
		try
		{
			DBRecord rec = getRecord("SELECT * FROM users WHERE login='"+login.replace("'", "")+"' AND password='"+password.replace("'", "")+"'");
			if(rec==null)
			{
				return null;
			}
			return new User(rec);
		}
		catch(Exception ex)
		{
			return null;
		}
    }
    public User getUser(Integer id)
    {
		try
		{
			DBRecord rec = getRecord("SELECT * FROM users WHERE id="+id);
			if(rec==null)
			{
			return null;
			}
			return new User(rec);
		}
		catch(Exception ex)
		{
			return null;
		}
    }
    public Long registerUser(HashMap<String,Object> params)
    {
        try
        {
            Long id = executeInsert("users", params);
            addActivity(5, id, null);
            return id;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public void deleteUser(int id) throws SQLException
    {
	executeNonQuery("DELETE FROM users WHERE id="+String.valueOf(id));
    }

    public Long insertOrder(String order_number, Long user_id, double sum) throws SQLException
    {
        HashMap<String,Object> ps = new HashMap<String, Object>();
        ps.put("order_number", order_number);
        ps.put("user_id", user_id);
        ps.put("sum", sum);
        Long id = executeInsert("orders", ps);
        addActivity(4, user_id, sum);
        return id;
    }
    public int checkPhoneNumber(String phone) throws SQLException
    {
        phone = phone.replace(" ", "").replace("(", "").replace("-", "").replace(")", "").replace("'", "");
        DBRecord rec = getRecord("SELECT * FROM phone_list WHERE is_black=TRUE AND phone='"+phone+"'");
        if(rec!=null)
        {
                return USER_PHONE_IN_BLACK_LIST;
        }
        DBRecord rec1 = getRecord("SELECT * FROM users WHERE login='"+phone+"'");
        if(rec1!=null)
        {
                return USER_PHONE_ALREADY_EXISTS;
        }
        return USER_PHONE_IS_OK;
    }
    public void updateCard(String code,boolean activated,Long user_id,double value) throws SQLException
    {
        DBRecord rec = getRecord("SELECT * FROM cards WHERE code='"+code.replace("'", "")+"'");
        if(rec == null)// Must add it first...
        {
                HashMap<String,Object> pars = new HashMap<String, Object>();
                pars.put("code", code.replace("'", ""));
                pars.put("value", value);
                pars.put("sertificate_number", code.replace("'", ""));
                pars.put("comment", "Карта не была внесена в список. Внесение в систему карты произошло при активации...");
                executeInsert("cards", pars);
        }
        executeNonQuery("UPDATE cards SET activated="+String.valueOf(activated)+", activator_user_id="+String.valueOf(user_id)+", activation_time=now() WHERE code='"+code.replace("'", "")+"'");
        if(activated)
        {
                addActivity(2, user_id, value);
        }
    }
    public DBTable getUsers() throws SQLException
    {
	return getTable("users",null,null);
    }
    public DBTable getUsers(int page,int page_size,String login) throws SQLException
    {
        if(login==null||login.equalsIgnoreCase("null")||login.equalsIgnoreCase(""))
        {
            return getTable("SELECT u.*,pl.is_black FROM users u LEFT JOIN phone_list pl ON u.login=pl.phone OFFSET "+String.valueOf((page-1)*page_size)+" LIMIT "+String.valueOf(page_size));
        }
        return getTable("SELECT u.*,pl.is_black FROM users u LEFT JOIN phone_list pl ON u.login=pl.phone WHERE u.login LIKE '%"+login.replace("'","")+"%' OFFSET "+String.valueOf((page-1)*page_size)+" LIMIT "+String.valueOf(page_size));
    }
    public DBTable getCards(int page,int page_size) throws SQLException
    {
        return getTable("SELECT cds.*,pl.is_black FROM (SELECT c.*, u.login FROM cards c, users u WHERE c.activator_user_id=u.id) cds LEFT JOIN phone_list pl ON pl.phone=cds.login OFFSET "+String.valueOf((page-1)*page_size)+" LIMIT "+String.valueOf(page_size));
    }
    public DBTable getOrders(int page,int page_size) throws SQLException
    {
        return getTable("SELECT ord.*,pl.is_black FROM (SELECT o.*, u.login FROM orders o, users u WHERE o.user_id=u.id) ord          LEFT JOIN phone_list pl ON pl.phone=ord.login OFFSET "+String.valueOf((page-1)*page_size)+" LIMIT "+String.valueOf(page_size));
    }
    public DBTable getPhoneLists() throws SQLException
    {
        return getTable("phone_list",null,null);
    }
    public Long addActivity(int type,Long user_id,Double value) throws SQLException
    {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("user_id",user_id);
        params.put("type_id",type);
        if(value != null)
        {
                params.put("value",value);
        }
        return executeInsert("activity", params);
    }
    public DBRecord getActivities(int type,Date from, Date to) throws SQLException
    {
        Calendar fr = Calendar.getInstance();
        Calendar t = Calendar.getInstance();
        fr.setTimeInMillis(from.getTime());
        t.setTimeInMillis(to.getTime());
        String f_str = String.valueOf(fr.get(Calendar.YEAR))+"-"+String.valueOf(fr.get(Calendar.MONTH))+"-"+String.valueOf(fr.get(Calendar.DAY_OF_MONTH));
        String t_str = String.valueOf(t.get(Calendar.YEAR))+"-"+String.valueOf(t.get(Calendar.MONTH))+"-"+String.valueOf(t.get(Calendar.DAY_OF_MONTH));
        DBRecord rec = getRecord("SELECT count(id) AS \"count\", sum(value) AS \"value\" FROM activities WHERE created<=date '"+t_str+"' AND created>=date '"+f_str+"' AND type_id="+type);
        return rec;
    }
    public DBRecord getActivities(int type,String from, String to,Boolean white) throws SQLException
    {
        String activity = "(SELECT ac.*,u.login FROM activity ac, users u " +
                "WHERE u.id=ac.user_id " +
                "AND ac.created<=date '"+to+"' " +
                "AND ac.created>=date '"+from+"' " +
                "AND type_id="+type+") act";
        String query = "SELECT count(*) AS \"count\", sum(value) AS \"value\" FROM "+activity+" LEFT JOIN phone_list pl ON pl.phone=act.login ";
        if(white==null)
        {

        }
        else if(white)
        {
            query+=" WHERE is_black=FALSE";
        }
        else
        {
            query+=" WHERE is_black=TRUE";
        }
        System.out.println(query);
        DBRecord rec = getRecord(query);
        return rec;
    }
    public DBTable getUsersActivities(String from, String to,Boolean white) throws SQLException
    {
        String query = "";
        if(white)
        {
            query = "SELECT t1.*,t2.total_orders_price,t2.total_orders FROM " +
                    "(SELECT u.id,u.gender,u.age,u.login, u.created, count(ac.*) AS cards_activated FROM users u LEFT JOIN (SELECT * FROM activity WHERE type_id=2 AND created>=date '"+from+"' AND created<=date '"+to+"') ac ON ac.user_id=u.id GROUP BY u.id, u.login, u.created,u.gender,u.age) t1, " +
                    "(SELECT u.login, sum(ac.value) AS total_orders_price, count(ac.*) AS total_orders FROM users u LEFT JOIN (SELECT * FROM activity WHERE type_id=4 AND created>=date '"+from+"' AND created<=date '"+to+"') ac ON ac.user_id=u.id GROUP BY u.id, u.login) t2, " +
                    "phone_list pl " +
                    "WHERE t1.login=t2.login " +
                    "AND  " +
                    "pl.phone=t1.login " +
                    "AND pl.is_black = FALSE";
        }
        else
        {
            query = "SELECT t1.*,t2.total_orders_price,t2.total_orders FROM " +
                    "(SELECT u.id,u.gender,u.age,u.login, u.created, count(ac.*) AS cards_activated FROM users u LEFT JOIN (SELECT * FROM activity WHERE type_id=2 AND created>=date '"+from+"' AND created<=date '"+to+"') ac ON ac.user_id=u.id GROUP BY u.id, u.login, u.created,u.gender,u.age) t1, " +
                    "(SELECT u.login, sum(ac.value) AS total_orders_price, count(ac.*) AS total_orders FROM users u LEFT JOIN (SELECT * FROM activity WHERE type_id=4 AND created>=date '"+from+"' AND created<=date '"+to+"') ac ON ac.user_id=u.id GROUP BY u.id, u.login) t2 " +
                    "WHERE t1.login=t2.login " +
                    "AND  " +
                    "t1.login NOT IN (SELECT phone FROM phone_list)";
        }
        DBTable tab = getTable(query);
        return tab;
    }
    public boolean tryRecoverPassword(String phone) throws SQLException
    {
        DBRecord user = getRecord("SELECT extract(month from last_password_recover) AS month, " +
                "extract(year from last_password_recover) AS year, " +
                "extract(year from now()) AS now_year, " +
                "extract(month from now()) AS now_month FROM users WHERE login='"+phone.replace("'","")+"'");
        if(user==null)
        {
            return false;
        }
        double year = user.getDoubleValue("year");
        double month = user.getDoubleValue("month");
        double now_year = user.getDoubleValue("now_year");
        double now_month = user.getDoubleValue("now_month");
        if(year!=now_year||month!=now_month)
        {
            executeNonQuery("UPDATE users SET last_password_recover = now() WHERE login='"+phone.replace("'","")+"'");
            return true;
        }
        return false;
    }
}

package racoonsoft.library.database;

import racoonsoft.library.settings.Settings;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

public class DBProcessor implements DataSource{

    private Connection db_connection;
    private String db_host, db_name, db_login, db_password;
    private int db_port;
    private String driver_class, connection_prefix;
    private PrintWriter logger;
    private int db_login_timeout;

    public DBProcessor(String host, String name, int port, String login, String password, String driver_class, String connection_prefix)
            throws ClassNotFoundException
    {
        db_host = host;
        db_name = name;
        db_login = login;
        db_password = password;
        db_port = port;
        this.driver_class = driver_class;
        this.connection_prefix = connection_prefix;
        Class.forName(this.driver_class);
    }
    public void connect() throws SQLException
    {
        disconnect();
        System.out.println(connection_prefix + "//" + db_host + ":" + String.valueOf(db_port) + "/" + db_name + "  login:" + db_login+" pass:"+ db_password);
        db_connection = DriverManager.getConnection(connection_prefix + "//" + db_host + ":" + String.valueOf(db_port) + "/" + db_name + "", db_login, db_password);
    }
    public void disconnect() throws SQLException
    {
        if (db_connection != null)
        {
            db_connection.close();
        }
    }
    public void checkConnection() throws SQLException
    {
        try
        {
            executeGet("SELECT 1");
        }
        catch(Exception ex)
        {
            connect();
        }
    }
    public Connection getConnection()
    {
        return db_connection;
    }
    @Override
    public Connection getConnection(String username, String password) throws SQLException
    {
        db_login = username;
        db_password = password;
        connect();
        return getConnection();
    }
    public void loadSettings() throws SQLException
    {
        ArrayList<DBRecord> recs = getRecords("SELECT * FROM settings");
        HashMap<String,String> settings = new HashMap<String, String>();

        for(int i=0; i<recs.size(); i++)
        {
            String name = (String)recs.get(i).getValue("name");
            String value = (String)recs.get(i).getValue("value");
            settings.put(name, value);
        }
        Settings.loadSettings(settings);
    }
    public long sequenceGetNextValue(String seq_name) throws SQLException
    {
        DBRecord rec = getRecord("SELECT nextval('"+seq_name+"') as val");
        return ((Long)rec.getValue("val")).longValue();
    }
    public void sequenceSetCurrentValue(String seq_name,Long value) throws SQLException
    {
        executeNonQuery("SELECT setval('"+seq_name+"',"+value.toString()+")");
    }
    public ArrayList<HashMap<String,Object>> extractFields(ResultSet set) throws SQLException
    {
        ArrayList<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
        int column_count = set.getMetaData().getColumnCount();
        String[] column_names = new String[column_count];
        for(int i=0; i<column_count; i++)
        {
            column_names[i] = set.getMetaData().getColumnName(i+1);
        }
        while(set.next())
        {
            HashMap<String, Object> record = new HashMap<String, Object>();
            for(int i=0; i<column_count; i++)
            {
                record.put(column_names[i], set.getObject(column_names[i]));
            }
            result.add(record);
        }
        return result;
    }

    public boolean executeNonQuery(String expression) throws SQLException
    {
        return db_connection.createStatement().execute(expression);
    }
    // Select...
    public ResultSet executeGet(String expression) throws SQLException
    {
        return db_connection.createStatement().executeQuery(expression);
    }
    public ResultSet executeGet(String table_name, Long id, String condition, String[] columns) throws SQLException
    {
        StringBuilder query = new StringBuilder("SELECT ");
        if (columns == null || columns.length == 0)
        {
            query.append("*");
        }
        else
        {
            for (int i = 0; i < columns.length; i++)
            {
                query.append(columns[i]);
                if (i != columns.length - 1)
                {
                    query.append(",");
                }
            }
        }
        query.append(" FROM ");
        query.append(table_name);
        if (id != null || condition != null)
        {
            query.append(" WHERE ");
            if (id != null)
            {
                query.append(" id= ");
                query.append(id);
                if (condition != null)
                {
                    query.append(" AND ");
                    query.append(condition);
                }
            }
            else
            {
                query.append(condition);
            }
        }
        return db_connection.createStatement().executeQuery(query.toString());
    }
    public ResultSet executeGet(String table_name, Long id, String condition) throws SQLException
    {
        return executeGet(table_name, id, condition, null);
    }
    public ResultSet executeGet(String table_name, String condition, String[] columns) throws SQLException
    {
        return executeGet(table_name, null, condition, columns);
    }
    public ResultSet executeGetById(String table_name, Long id, String[] columns) throws SQLException
    {
        return executeGet(table_name, id, null,columns);
    }
    public ResultSet executeGet(String table_name, String[] columns) throws SQLException
    {
        return executeGet(table_name, null,null,columns);
    }
    public ArrayList<DBRecord> extractRecords(ResultSet set) throws SQLException
    {
        ArrayList<DBRecord> result = new ArrayList<DBRecord>();
        ArrayList<HashMap<String,Object>> extracted_set = extractFields(set);
        for(int i=0; i<extracted_set.size(); i++)
        {
            DBRecord rec = new DBRecord(extracted_set.get(i));
            result.add(rec);
        }
        return result;
    }
    public DBRecord extractrecord(ResultSet set) throws SQLException
    {
        ArrayList<DBRecord> res = extractRecords(set);
        if(res.size()>0)
        {
            return res.get(0);
        }
        return null;
    }
    public ArrayList<DBRecord> getRecords(String table_name, String condition, String[] columns) throws SQLException
    {
        System.out.println("INDA getEcords");
        return extractRecords(executeGet(table_name, null, condition, columns));
    }
    public DBTable getTable(String table_name, String condition, String[] columns) throws SQLException
    {
        System.out.println("INDA DBProc");
        ArrayList<DBRecord> recs = getRecords(table_name, condition, columns);
        System.out.println("recs: "+recs);
        DBTable tab = new DBTable("content",recs);
        System.out.println("tab: "+tab);
        return tab;
    }
    public ArrayList<DBRecord> getRecords(String expression) throws SQLException
    {
        return extractRecords(db_connection.createStatement().executeQuery(expression));
    }
    public DBTable getTable(String expression) throws SQLException
    {
        return new DBTable("table",extractRecords(executeGet(expression)));
    }
    public DBTable getFullTable(String table_name) throws SQLException
    {
        return getTable(table_name, null, null);
    }
    public DBRecord getRecord(String table_name, String condition, String[] columns) throws SQLException
    {
        ArrayList<DBRecord> recs = extractRecords(executeGet(table_name, null, condition, columns));
        if(recs.size()>0)
        {
            return recs.get(0);
        }
        return null;
    }
    public DBRecord getRecord(String expression) throws SQLException
    {
        ArrayList<DBRecord> recs = extractRecords(db_connection.createStatement().executeQuery(expression));
        if(recs.size()>0)
        {
            return recs.get(0);
        }
        return null;
    }
    // Delete...
    public void executeDelete(String table_name, Integer id, String condition) throws SQLException
    {
        StringBuilder query = new StringBuilder("DELETE FROM ");
        query.append(table_name);
        if (id != null || condition != null)
        {
            query.append(" WHERE ");
            if (id != null)
            {
                query.append(" id= ");
                query.append(id);
                if (condition != null)
                {
                    query.append(" AND ");
                    query.append(condition);
                }
            }
            else
            {
                query.append(condition);
            }
        }
        db_connection.createStatement().execute(query.toString());
    }
    public void executeDelete(String table_name, Integer id) throws SQLException
    {
        executeDelete(table_name, id, null);
    }
    public void executeDelete(String table_name, String condition) throws SQLException
    {
        executeDelete(table_name, null, condition);
    }
    public void executeDelete(String table_name) throws SQLException
    {
        executeDelete(table_name, null, null);
    }

    // Insert...
    public Long executeInsert(String table_name, HashMap<String,Object> fields) throws SQLException
    {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(table_name);
        query.append(" (");
        Iterator<String> keys = fields.keySet().iterator();
        ArrayList<String> field_names = new ArrayList<String>();
        ArrayList<String> field_values = new ArrayList<String>();
        while(keys.hasNext())
        {
            String key = keys.next();
            String value = objectToString(fields.get(key));
            field_names.add(key);
            field_values.add(value);
        }
        for(int i=0; i<field_names.size(); i++)
        {
            query.append(field_names.get(i));
            if(field_names.size()-1!=i)
            {
                query.append(",");
            }
        }
        query.append(") VALUES (");
        for(int i=0; i<field_values.size(); i++)
        {
            query.append(field_values.get(i));
            if(field_values.size()-1!=i)
            {
                query.append(",");
            }
        }
        query.append(") RETURNING id");
        ResultSet set = db_connection.createStatement().executeQuery(query.toString());
        if(set.next())
        {
            return set.getLong("id");
        }
        return null;
    }
    public Long executeInsert(String table_name, DBRecord rec) throws SQLException
    {
        return executeInsert(table_name,rec.fields);
    }

    // Update...
    public Long executeUpdate(String table_name, HashMap<String,Object> fields,String condition) throws SQLException
    {
        if(fields==null||fields.isEmpty())
        {
            return 0l;
        }
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(table_name);
        Iterator<String> keys = fields.keySet().iterator();
        ArrayList<String> field_names = new ArrayList<String>();
        ArrayList<String> field_values = new ArrayList<String>();

        while(keys.hasNext())
        {
            String key = keys.next();
            String value = objectToString(fields.get(key));
            field_names.add(key);
            field_values.add(value);
        }
        query.append(" SET ");
        for(int i=0; i<field_names.size(); i++)
        {
            query.append(field_names.get(i));
            query.append("=");
            query.append(field_values.get(i));
            if(field_names.size()-1!=i)
            {
                query.append(",");
            }
        }
        if(condition!=null&&!condition.equalsIgnoreCase(""))
        {
            query.append(" WHERE ");
            query.append(condition);
        }
        return ((Integer)db_connection.createStatement().executeUpdate(query.toString())).longValue();
    }
    public Long executeUpdate(String table_name,DBRecord rec) throws SQLException
    {
        return executeUpdate(table_name,rec.getFields(),"id="+rec.getID());
    }
    // Helpers...
    public static String objectToString(Object field)
    {
        if(field == null)
        {
            return "NULL";
        }
        else if(field instanceof Double||field instanceof Integer||field instanceof Long)
        {
            return field.toString().replace(',', '.');
        }
        else if(field instanceof Calendar)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return "'"+sdf.format(((Calendar)field).getTime())+"'";
        }
        else if(field instanceof Date)
        {
            Calendar c = Calendar.getInstance();
            c.setTime((Date)field);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return "'"+sdf.format((c).getTime())+"'";
        }
        else if(field instanceof Expression)
        {
            return ((Expression) field).getValue();
        }
        else if(field instanceof String)
        {
            return "'"+field.toString().replace("'","`")+"'";
        }
        else if(field instanceof Boolean)
        {
            return field.toString().toUpperCase();
        }
        return field.toString();
    }

    //public abstract Integer registerUser(HashMap<String,Object> user_parameters) throws UserRegistrationException;
    //public abstract User getUser(Integer id) throws SQLException,UserInitializationException;
    //public abstract User authenticateUser(String login,String password);

    public void setContent(String name,String value) throws SQLException
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("name",name);
        pars.put("value",value);
        executeDelete("content","name='"+name.replace("'","")+"'");
        executeInsert("content", pars);

    }
    public DBTable getContent(String[] names) throws SQLException
    {
        if(names==null||names.length==0)
        {
            return null;
        }
        StringBuilder query = new StringBuilder();
        for(int i=0 ;i<names.length; i++)
        {
            query.append("name='");
            query.append(names[i].replace("'",""));
            query.append("'");
            if(i<names.length-1)
            {
                query.append(" OR ");
            }
        }
        String total_query = "SELECT * FROM content WHERE "+query.toString();
        return getTable(total_query);
    }
    public void initContent(String[] names,String[] default_values) throws SQLException
    {
        StringBuilder query = new StringBuilder();
        String single_query = "INSERT INTO content (name,value) SELECT 'nnn','vvv' WHERE 'nnn' NOT IN (SELECT name FROM content); ";
        for(int i=0; i<names.length; i++)
        {
            String current_query = single_query.replace("nnn",names[i]).replace("vvv",default_values[i]);
            query.append(current_query);
        }
        executeNonQuery(query.toString());
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException
    {
        return logger;
    }
    @Override
    public void setLogWriter(PrintWriter out) throws SQLException
    {
        logger = out;
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException
    {
        db_login_timeout = seconds;
    }
    @Override
    public int getLoginTimeout() throws SQLException
    {
        return db_login_timeout;
    }
    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException
    {
        return Logger.getAnonymousLogger();
    }
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException
    {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Long registerUser(HashMap<String,Object> pars) throws SQLException
    {
        return executeInsert("user",pars);
    }
    public DBRecord authenticateUser(String login,String password) throws SQLException
    {
        return getRecord("SELECT * FROM user WHERE login='"+login.replace("'","`")+" AND password='"+password.replace("'","`")+"'");
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException
    {
        getConnection().setAutoCommit(autoCommit);
    }
    public void rollback(Savepoint savepoint) throws SQLException
    {
        if(savepoint==null)
        {
            getConnection().rollback();
        }
        else
        {
            getConnection().rollback(savepoint);
        }
    }
    public Savepoint setSavepoint(String name) throws Exception
    {
        setAutoCommit(false);
        if(name==null)
        {
            return getConnection().setSavepoint();
        }
        return getConnection().setSavepoint(name);
    }
}

package racoonsoft.racoonspring.data.database;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Logger;

public class DatabaseProcessor implements DataSource
{

    //<editor-fold desc="Init">
    private Connection db_connection;
    private String db_host, db_name, db_login, db_password;
    private int db_port;
    private String driver_class, connection_prefix;
    private PrintWriter logger;
    private int db_login_timeout;

    public DatabaseProcessor(String host, String name, int port, String login, String password, String driver_class, String connection_prefix)
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
            selectQuery("SELECT 1").select();
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
    //</editor-fold>

    //<editor-fold desc="Select">
    public SelectQuery selectQuery(String expression) throws Exception
    {
        SelectQuery q = new SelectQuery(db_connection,expression);
        return q;
    }
    //</editor-fold>


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
//    public Long executeInsert(String table_name, DBRecord rec) throws SQLException
//    {
//        return executeInsert(table_name,rec.fields);
//    }

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
//    public Long executeUpdate(String table_name,DBRecord rec) throws SQLException
//    {
//        return executeUpdate(table_name,rec.getFields(),"id="+rec.getID());
//    }
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
//        else if(field instanceof Expression)
//        {
//            return ((Expression) field).getValue();
//        }
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

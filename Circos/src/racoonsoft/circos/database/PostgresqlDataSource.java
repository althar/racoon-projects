package racoonsoft.circos.database;

import racoonsoft.library.database.DBProcessor;

import java.sql.SQLException;
import java.util.HashMap;


public class PostgresqlDataSource extends DBProcessor
{
    public PostgresqlDataSource(String host, String dbname, int port, String login, String password, String driver, String prefix) throws ClassNotFoundException,SQLException
    {
        super(host,dbname,port,login,password,driver,prefix);
        connect();
    }

}
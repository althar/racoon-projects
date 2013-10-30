package racoonsoft.fapracoon.database;

import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.database.DBRecord;
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

}
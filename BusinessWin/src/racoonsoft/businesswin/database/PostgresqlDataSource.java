package racoonsoft.businesswin.database;

import racoonsoft.businesswin.game.structure.enums.GameMode;
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

    public Long createGame(String name, GameMode mode) throws Exception
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("name",name);
        pars.put("mode",mode.toString());
        return executeInsert("game",pars);
    }
}
package racoonsoft.investmentwheel.database;

import racoonsoft.investmentwheel.game.structure.GameMode;
import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.database.DBRecord;
import java.sql.SQLException;
import java.util.ArrayList;
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
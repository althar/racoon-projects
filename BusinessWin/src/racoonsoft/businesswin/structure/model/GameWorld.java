package racoonsoft.businesswin.structure.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.businesswin.database.PostgresqlDataSource;
import racoonsoft.businesswin.structure.data.EconomicsValue;
import racoonsoft.businesswin.structure.data.StartSettings;
import racoonsoft.businesswin.structure.enums.GameMode;
import racoonsoft.businesswin.structure.enums.GameStatus;
import racoonsoft.businesswin.structure.enums.StatusCode;
import racoonsoft.library.json.JSONProcessor;

import java.util.HashMap;

@Service
public class GameWorld
{
    @Autowired
    private static PostgresqlDataSource dbProc;

    private static HashMap<Long,Game> games = new HashMap<Long, Game>();

    public static void setDbProc(PostgresqlDataSource dbProc)
    {
        GameWorld.dbProc = dbProc;
    }
    public static HashMap<Long, Game> getGames()
    {
        return games;
    }

    public static Game createGame(String name, GameMode mode,StartSettings parameters,Integer companyCount) throws Exception
    {
        Long id = dbProc.createGame(name,mode);
        Game game = new Game(id,mode,name,parameters,companyCount);
        games.put(id,game);
        return game;
    }
    public static void finishGame(Long game_id) throws Exception
    {
        games.remove(game_id);
    }
    public static Game getGame(Long id) throws Exception
    {
        return games.get(id);
    }
}

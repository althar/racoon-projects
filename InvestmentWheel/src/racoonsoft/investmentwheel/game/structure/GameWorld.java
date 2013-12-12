package racoonsoft.investmentwheel.game.structure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.investmentwheel.database.PostgresqlDataSource;
import racoonsoft.investmentwheel.game.structure.enums.GameMode;
import racoonsoft.investmentwheel.game.structure.enums.StatusCode;
import racoonsoft.library.json.JSONProcessor;

import java.util.HashMap;

@Service
public class GameWorld
{
    private static final GameWorld self = new GameWorld();

    @Autowired
    private PostgresqlDataSource dbProc;
    private HashMap<Long,Game> games = new HashMap<Long, Game>();

    public static GameWorld instance()
    {
        return self;
    }
    public void setDbProc(PostgresqlDataSource dbProc)
    {
        this.dbProc = dbProc;
    }
    public HashMap<Long, Game> getGames()
    {
        return games;
    }

    public JSONProcessor createGame(String name, GameMode mode) throws Exception
    {
        Long id = dbProc.createGame(name,mode);
        Game game = new Game(id,mode,name);
        games.put(id,game);
        StatusCode statusCode = StatusCode.SUCCESS;
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("game_id", id);
        jsonMap.put("games", getGames());
        jsonMap.put("status_code", statusCode);
        JSONProcessor json = new JSONProcessor(jsonMap);
        return json;
    }
    public JSONProcessor getGame(Long id) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("games", getGames());
        jsonMap.put("status_code", StatusCode.SUCCESS);
        JSONProcessor json = new JSONProcessor(jsonMap);
        return json;
    }
    public JSONProcessor setupGame(Long game_id,HashMap<String,Object> params) throws Exception
    {
        Game g = games.get(game_id);
        StatusCode statusCode = StatusCode.SUCCESS;
        if(g==null)
        {
            statusCode=StatusCode.NO_SUCH_GAME;
        }
        else
        {
            statusCode = g.setup(params);
        }
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("status_code", statusCode);
        jsonMap.put("game", g);
        JSONProcessor json = new JSONProcessor(jsonMap);
        return json;
    }
    public JSONProcessor startGame(Long game_id) throws Exception
    {
        Game g = games.get(game_id);
        StatusCode statusCode = StatusCode.SUCCESS;
        if(g==null)
        {
            statusCode=StatusCode.NO_SUCH_GAME;
        }
        else
        {
            statusCode = g.start();
        }
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("status_code", statusCode);
        jsonMap.put("game", g);
        JSONProcessor json = new JSONProcessor(jsonMap);
        return json;
    }
    public JSONProcessor joinGame(Long game_id,Player player) throws Exception
    {
        Game g = games.get(game_id);
        StatusCode statusCode = StatusCode.SUCCESS;
        if(g==null)
        {
            statusCode=StatusCode.NO_SUCH_GAME;
        }
        else if(player==null)
        {
            statusCode=StatusCode.NO_SUCH_USER;
        }
        else
        {
            statusCode = g.addPlayer(player);
        }
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("status_code", statusCode);
        jsonMap.put("game", g);
        JSONProcessor json = new JSONProcessor(jsonMap);
        return json;
    }
    public JSONProcessor turnGame(Long game_id) throws Exception
    {
        Game g = games.get(game_id);
        StatusCode statusCode = StatusCode.SUCCESS;
        if(g==null)
        {
            statusCode=StatusCode.NO_SUCH_GAME;
        }
        else
        {
            statusCode = g.turn();
        }
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("status_code", statusCode);
        jsonMap.put("game", g);
        JSONProcessor json = new JSONProcessor(jsonMap);
        return json;
    }
}

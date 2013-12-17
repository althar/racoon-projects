package racoonsoft.businesswin.game.structure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.businesswin.database.PostgresqlDataSource;
import racoonsoft.businesswin.game.structure.enums.GameMode;
import racoonsoft.businesswin.game.structure.enums.GameStatus;
import racoonsoft.businesswin.game.structure.enums.StatusCode;
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
        if(name==null)
        {
            StatusCode statusCode = StatusCode.FAILURE_PARAMETERS_WRONG;
            HashMap<String,Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("status_code", statusCode);
            JSONProcessor json = new JSONProcessor(jsonMap);
            return json;
        }
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
    public JSONProcessor finishGame(Long game_id) throws Exception
    {
        Game game = games.get(game_id);
        StatusCode statusCode = StatusCode.SUCCESS;
        if(game==null)
        {
            statusCode = StatusCode.NO_SUCH_GAME;
            HashMap<String,Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("game_id", game_id);
            jsonMap.put("status_code", statusCode);
            JSONProcessor json = new JSONProcessor(jsonMap);
            return json;
        }
        game.setStatus(GameStatus.FINISHED);
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("game_id", game_id);
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
            statusCode = g.join(player);
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

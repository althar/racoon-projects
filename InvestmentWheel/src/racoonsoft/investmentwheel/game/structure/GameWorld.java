package racoonsoft.investmentwheel.game.structure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import racoonsoft.investmentwheel.database.PostgresqlDataSource;

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

    public Long createGame(String name, GameMode mode) throws Exception
    {
        Long id = dbProc.createGame(name,mode);
        Game game = new Game(id,mode,name);
        games.put(id,game);
        return id;
    }
}

package racoonsoft.businesswin.service;

import org.springframework.stereotype.Service;
import racoonsoft.businesswin.structure.data.EconomicsValue;
import racoonsoft.businesswin.structure.data.StartSettings;
import racoonsoft.businesswin.structure.enums.GameMode;
import racoonsoft.businesswin.structure.model.Game;
import racoonsoft.businesswin.structure.model.GameWorld;
import racoonsoft.businesswin.structure.model.Player;
import racoonsoft.businesswin.structure.enums.GameStatus;
import racoonsoft.businesswin.structure.enums.StatusCode;
import racoonsoft.library.json.JSONProcessor;

import java.util.HashMap;

@Service
public class GameService
{
    public StatusCode createGame(String name, GameMode mode,StartSettings start_settings) throws Exception
    {
        //TODO: check parameters...
        GameWorld.createGame(name, mode, start_settings);

        return StatusCode.SUCCESS;
    }
    public StatusCode finishGame(Long game_id) throws Exception
    {
        GameWorld.finishGame(game_id);
        return StatusCode.SUCCESS;
    }
    public Game getGame(Long id) throws Exception
    {
        return GameWorld.getGame(id);
    }
    public StatusCode startGame(Long game_id) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        // TODO: Calculate step 0 data.
        return StatusCode.SUCCESS;
    }
    public StatusCode joinGame(Long game_id,Player player) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        g.addPlayer(player);
        return StatusCode.SUCCESS;
    }
    public HashMap<Long,Game> getGames()
    {
        return GameWorld.getGames();
    }
}

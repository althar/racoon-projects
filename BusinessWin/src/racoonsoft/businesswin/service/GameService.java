package racoonsoft.businesswin.service;

import org.springframework.stereotype.Service;
import racoonsoft.businesswin.structure.data.EconomicsValue;
import racoonsoft.businesswin.structure.data.StartSettings;
import racoonsoft.businesswin.structure.data.TradeFactors;
import racoonsoft.businesswin.structure.enums.GameMode;
import racoonsoft.businesswin.structure.model.Game;
import racoonsoft.businesswin.structure.model.GameWorld;
import racoonsoft.businesswin.structure.model.GoodsDeclaration;
import racoonsoft.businesswin.structure.model.Player;
import racoonsoft.businesswin.structure.enums.GameStatus;
import racoonsoft.businesswin.structure.enums.StatusCode;
import racoonsoft.library.json.JSONProcessor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

@Service
public class GameService
{

    //<editor-fold desc="General">
    public HashMap<Long,Game> getGames()
    {
        return GameWorld.getGames();
    }
    public Game getGame(Long id) throws Exception
    {
        return GameWorld.getGame(id);
    }
    //</editor-fold>

    //<editor-fold desc="Phase 0">
    public StatusCode createGame(String name, GameMode mode,StartSettings start_settings,Integer companyCount) throws Exception
    {
        //TODO: check parameters...
        GameWorld.createGame(name, mode, start_settings,companyCount);

        return StatusCode.SUCCESS;
    }
    public StatusCode finishGame(Long game_id) throws Exception
    {
        GameWorld.finishGame(game_id);
        return StatusCode.SUCCESS;
    }
    public StatusCode startGame(Long game_id) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        if(g.status!=GameStatus.READY)
        {
            return StatusCode.GAME_NOT_READY;
        }
        Iterator<Long> plIter = g.players.keySet().iterator();
        // Distribute one for each...
        int company_index = 0;
        while(plIter.hasNext())
        {
            Long player_id = plIter.next();
            Player p = g.players.get(player_id);
            p.companies.clear();
            p.companies.add(g.companies.get(company_index));
            company_index++;
            g.players.put(player_id,p);
        }
        // Distribute else
        plIter = g.players.keySet().iterator();
        while(company_index<g.companies.size())
        {
            if(!plIter.hasNext())
            {
                plIter = g.players.keySet().iterator();
            }
            Long player_id = plIter.next();
            Player p = g.players.get(player_id);

            if(new Random().nextBoolean())// check random
            {
                p.companies.add(g.companies.get(company_index));
                company_index++;
                g.players.put(player_id,p);
            }
        }
        calculateStep0(g);
        g.turn.nextPhase();
        g.setStatus(GameStatus.IN_PROGRESS);
        return StatusCode.SUCCESS;
    }
    public StatusCode joinGame(Long game_id,Player player) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        if(g.status!=GameStatus.NEW&&g.status!=GameStatus.READY)
        {
            return StatusCode.GAME_ALREADY_STARTED;
        }
        if(g.companies.size()<=g.players.size())
        {
            return StatusCode.TOO_MANY_PLAYERS;
        }
        g.addPlayer(player);
        g.status = GameStatus.READY;
        return StatusCode.SUCCESS;
    }
    //</editor-fold>

    public StatusCode declareGoods(Long game_id, Long player_id,GoodsDeclaration declaration) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        Player p = g.players.get(player_id);
        if(p == null)
        {
            return StatusCode.NO_SUCH_PLAYER;
        }
        p.goodsDeclaration = declaration;
        return StatusCode.SUCCESS;
    }
    public StatusCode setTradeFactors(Long game_id, Long player_id, TradeFactors tradeFactors) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        g.tradeFactors = tradeFactors;
        return StatusCode.SUCCESS;
    }

    //<editor-fold desc="Calculations">
    private void calculateStep0(Game g)
    {
        // Calculate step 0 data
    }
    //</editor-fold>
}

package racoonsoft.investmentwheel.game.structure;

import racoonsoft.investmentwheel.game.structure.enums.GameMode;
import racoonsoft.investmentwheel.game.structure.enums.GameStatus;
import racoonsoft.investmentwheel.game.structure.enums.StatusCode;
import racoonsoft.investmentwheel.game.structure.model.BusinessPlan;
import racoonsoft.library.database.DBRecord;

import java.util.HashMap;

public class Game extends DBRecord
{
    private HashMap<Long,Player> players = new HashMap<Long, Player>();

    public Game(Long id, GameMode mode,String name)
    {
        setValue("id",id);
        setValue("name",name);
        setValue("mode",mode);
        setValue("status", GameStatus.NEW);
        setValue("players", new HashMap<Long, Player>());
        setValue("turn",0);
    }
    public BusinessPlan getPlan(Long player_id)
    {
        Player p = players.get(player_id);
        if(p!=null)
        {
            return p.getPlan();
        }
        return null;
    }
    public StatusCode setup(HashMap<String,Object> params)
    {
        return GameProcessor.setup(this,params);
    }
    public StatusCode turn()
    {
        return GameProcessor.turn(this);
    }
    public StatusCode start()
    {
        return GameProcessor.start(this);
    }
    public StatusCode join()
    {
        return GameProcessor.start(this);
    }
    public GameStatus getStatus()
    {
        return (GameStatus)getValue("status");
    }
    public void setStatus(GameStatus status)
    {
        setValue("status",status);
    }
    public HashMap<Long, Player> players()
    {
        return (HashMap<Long, Player>)getValue("players");
    }
    public StatusCode addPlayer(Player p)
    {
        HashMap<Long, Player> players = players();
        players.put(p.getID(),p);
        setValue("players", players);
        return StatusCode.SUCCESS;
    }
}

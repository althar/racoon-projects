package racoonsoft.businesswin.game.structure;

import racoonsoft.businesswin.game.structure.data.StartSettings;
import racoonsoft.businesswin.game.structure.enums.GameMode;
import racoonsoft.businesswin.game.structure.enums.GameStatus;
import racoonsoft.businesswin.game.structure.enums.StatusCode;
import racoonsoft.businesswin.game.structure.model.BusinessPlan;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;
import racoonsoft.library.database.DBRecord;

import java.util.HashMap;

@DataStructure(name="game")
public class Game
{

    @DataStructureField(name="players")
    public HashMap<Long,Player> players = new HashMap<Long, Player>();
    @DataStructureField(name="start_settings")
    public StartSettings startSettings = new StartSettings();

    @DataStructureField(name="id")
    public Long id;
    @DataStructureField(name="name")
    public String name;
    @DataStructureField(name="mode")
    public GameMode mode;
    @DataStructureField(name="status")
    public GameStatus status;
    @DataStructureField(name="turn")
    public Integer turn;

    public Game(Long id, GameMode mode,String name)
    {
        this.id = id;
        this.name = name;
        this.mode = mode;
        this.status = GameStatus.NEW;
        this.turn = 0;
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
    public StatusCode join(Player p)
    {
        return GameProcessor.join(this,p);
    }
    public GameStatus getStatus()
    {
        return status;
    }
    public void setStatus(GameStatus status)
    {
        this.status = status;
    }
    public HashMap<Long, Player> players()
    {
        return players;
    }
    public StatusCode addPlayer(Player p)
    {
        players.put(p.id,p);
        return StatusCode.SUCCESS;
    }
}

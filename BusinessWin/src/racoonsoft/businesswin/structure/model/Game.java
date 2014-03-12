package racoonsoft.businesswin.structure.model;

import racoonsoft.businesswin.service.GameService;
import racoonsoft.businesswin.structure.data.EconomicsValue;
import racoonsoft.businesswin.structure.data.StartSettings;
import racoonsoft.businesswin.structure.enums.GameMode;
import racoonsoft.businesswin.structure.enums.GameStatus;
import racoonsoft.businesswin.structure.enums.StatusCode;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

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

    public Game(Long id, GameMode mode,String name, StartSettings startSettings)
    {
        this.id = id;
        this.name = name;
        this.mode = mode;
        this.status = GameStatus.NEW;
        this.turn = 0;
        this.startSettings = startSettings;
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

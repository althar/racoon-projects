package racoonsoft.tinyheroes.model;

import racoonsoft.tinyheroes.enums.BattlefieldStatuses;
import racoonsoft.tinyheroes.enums.MapTypes;

import java.util.HashMap;

public class Battlefield
{
    private MapTypes map_type;
    private HashMap<String,Integer> team_map;
    private HashMap<String,Barony> baronies;
    private BattlefieldStatuses status;

    public Battlefield(MapTypes map_type)
    {
        this.map_type = map_type;
        status = BattlefieldStatuses.WAITING_FOR_PLAYERS;
    }
    public void addBarony(Barony b, int team)
    {
        baronies.put(b.getName(),b);
        team_map.put(b.getName(),team);
    }
}

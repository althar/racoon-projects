package racoonsoft.investmentwheel.game.structure;

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
    }
}

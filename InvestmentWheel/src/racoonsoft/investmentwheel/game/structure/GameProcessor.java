package racoonsoft.investmentwheel.game.structure;

import racoonsoft.investmentwheel.game.structure.enums.GameStatus;
import racoonsoft.investmentwheel.game.structure.enums.StatusCode;

import java.util.HashMap;

public class GameProcessor
{
    public static StatusCode setup(Game g,HashMap<String,Object> params)
    {
        if(g.getStatus()!=GameStatus.NEW&&g.getStatus()!=GameStatus.READY)
        {
            return StatusCode.GAME_ALREADY_STARTED;
        }
        g.Fields.putAll(params);
        g.setStatus(GameStatus.READY);
        return  StatusCode.SUCCESS;
    }
    public static StatusCode start(Game g)
    {
        if(g.getStatus()!=GameStatus.READY)
        {
            return StatusCode.GAME_NOT_READY;
        }
        else if(g.getStatus()==GameStatus.IN_PROGRESS)
        {
            return StatusCode.GAME_ALREADY_STARTED;
        }
        g.setStatus(GameStatus.IN_PROGRESS);
        return  StatusCode.SUCCESS;
    }
    public static StatusCode join(Game g,Player p)
    {
        if(g.getStatus()!=GameStatus.READY)
        {
            return StatusCode.GAME_NOT_READY;
        }
        else if(g.getStatus()==GameStatus.IN_PROGRESS)
        {
            return StatusCode.GAME_ALREADY_STARTED;
        }
        g.addPlayer(p);
        return  StatusCode.SUCCESS;
    }
    public static StatusCode turn(Game g)
    {
        if(g.getStatus()!=GameStatus.IN_PROGRESS)
        {
            return StatusCode.GAME_NOT_IN_PROGRESS;
        }
        g.setValue("turn",g.getIntValue("turn")+1);
        return StatusCode.SUCCESS;
    }
}

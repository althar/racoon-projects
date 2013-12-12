package racoonsoft.investmentwheel.game.structure;

import racoonsoft.investmentwheel.game.structure.model.BusinessPlan;
import racoonsoft.library.database.DBRecord;

public class Player extends DBRecord
{
    public BusinessPlan getPlan()
    {
        return new BusinessPlan();
    }
    public void turn()
    {

    }
}

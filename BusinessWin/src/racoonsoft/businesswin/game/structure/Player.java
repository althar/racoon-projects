package racoonsoft.businesswin.game.structure;

import racoonsoft.businesswin.game.structure.model.BusinessPlan;
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

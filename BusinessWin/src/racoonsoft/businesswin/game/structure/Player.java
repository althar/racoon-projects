package racoonsoft.businesswin.game.structure;

import racoonsoft.businesswin.game.structure.model.BusinessPlan;
import racoonsoft.businesswin.game.structure.model.Company;
import racoonsoft.businesswin.game.structure.model.EventCard;
import racoonsoft.library.database.DBRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends DBRecord
{
    public Player()
    {
        setValue("businessPlan",new BusinessPlan());
        setValue("companies",new ArrayList<Company>());
        setValue("eventCard",null);
    }
    public ArrayList<Company> getCompanies()
    {
        return (ArrayList<Company>)getValue("companies");
    }
    public EventCard getEventCard()
    {
        return (EventCard)getValue("eventCard");
    }
    public BusinessPlan getBusinessPlan()
    {
        return (BusinessPlan)getValue("businessPlan");
    }

    public BusinessPlan getPlan()
    {
        return new BusinessPlan();
    }
    public void turn()
    {

    }
}


package racoonsoft.businesswin.structure.model;

import racoonsoft.businesswin.structure.data.BusinessPlan;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

import java.util.ArrayList;

@DataStructure(name="game")
public class Player
{
    @DataStructureField(name="id")
    public Long id = null;
    @DataStructureField(name="login")
    public String login;

    @DataStructureField(name="business_plan")
    public BusinessPlan businessPlan;
    @DataStructureField(name="companies")
    public ArrayList<Company> companies = new ArrayList<Company>();
    @DataStructureField(name="event_cards")
    public EventCard eventCard = null;


    public Player()
    {

    }
    public Company getCompany(Long id)
    {
        for(Company c:companies)
        {
            if(c.id.longValue()==id.longValue())
            {
                return c;
            }
        }
        return null;
    }
    public ArrayList<Company> getCompanies()
    {
        return companies;
    }
    public EventCard getEventCard()
    {
        return eventCard;
    }
    public BusinessPlan getBusinessPlan()
    {
        return businessPlan;
    }

    public BusinessPlan getPlan()
    {
        return new BusinessPlan();
    }
    public void turn()
    {

    }
}


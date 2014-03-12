package racoonsoft.businesswin.structure.model;

import racoonsoft.businesswin.structure.model.BusinessPlan;
import racoonsoft.businesswin.structure.model.Company;
import racoonsoft.businesswin.structure.model.EventCard;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

import java.util.ArrayList;

@DataStructure(name="game")
public class Player
{
    @DataStructureField(name="business_plan")
    public BusinessPlan businessPlan;
    @DataStructureField(name="companies")
    public ArrayList<Company> companies = new ArrayList<Company>();
    @DataStructureField(name="event_card")
    public EventCard eventCard = null;
    @DataStructureField(name="id")
    public Long id = null;
    @DataStructureField(name="login")
    public String login;

    public Player()
    {
        companies.add(new Company());
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


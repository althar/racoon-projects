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

//    @DataStructureField(name="business_plan")
//    public BusinessPlan businessPlan;
    @DataStructureField(name="companies")
    public ArrayList<Company> companies = new ArrayList<Company>();
    @DataStructureField(name="cash")
    public Double cash = 0.0;


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
    public void removeCompany(Long id)
    {
        companies.remove(getCompany(id));
    }
    public ArrayList<Company> getCompanies()
    {
        return companies;
    }

    public BusinessPlan getPlan()
    {
        return new BusinessPlan();
    }
    public void turn()
    {

    }
}


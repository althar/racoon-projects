package racoonsoft.businesswin.structure.model;

import com.rits.cloning.Cloner;
import racoonsoft.businesswin.structure.data.*;
import racoonsoft.businesswin.structure.enums.GameMode;
import racoonsoft.businesswin.structure.enums.GameStatus;
import racoonsoft.businesswin.structure.enums.StatusCode;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@DataStructure(name="game")
public class Game
{


    @DataStructureField(name="current_action")
    public Integer currentAction = 0;
    @DataStructureField(name="company_count")
    public Integer company_count = 0;
    @DataStructureField(name="players")
    public HashMap<Long,Player> players = new HashMap<Long, Player>();
    @DataStructureField(name="companies")
    public ArrayList<Company> companies = new ArrayList<Company>();
    @DataStructureField(name="start_settings")
    public StartSettings startSettings = new StartSettings();
    @DataStructureField(name="trade_factors")
    public TradeFactors tradeFactors= new TradeFactors();
    @DataStructureField(name="product_price_and_production")
    public ProductPriceAndProduction product_price_and_production = new ProductPriceAndProduction();
    @DataStructureField(name="industry_performance")
    public IndustryPerformance industry_performance = new IndustryPerformance();
    @DataStructureField(name="demand_supply_curve")
    public DemandSupplyCurve demand_supply_curve = new DemandSupplyCurve();
    @DataStructureField(name="declared_event_cards")
    public DeclaredEventCards declared_event_cards = new DeclaredEventCards();


    @DataStructureField(name="id")
    public Long id;
    @DataStructureField(name="name")
    public String name;
    @DataStructureField(name="mode")
    public GameMode mode;
    @DataStructureField(name="status")
    public GameStatus status;
    @DataStructureField(name="turn")
    public Turn turn = new Turn();

    public Game(Long id, GameMode mode,String name, StartSettings startSettings, Integer companyCount)
    {
        this.id = id;
        this.name = name;
        this.mode = mode;
        this.status = GameStatus.NEW;
        this.startSettings = startSettings;
        for(long i=0; i<companyCount; i++)
        {
            Company c = new Company();
            c.name = "Company_"+i;
            c.id = i;
            companies.add(c);
        }
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
    public Company getCompany(Long id)
    {
        for(int i=0; i<companies.size(); i++)
        {
            if(companies.get(i).id.longValue()==id.longValue())
            {
                return companies.get(i);
            }
        }
        return null;
    }
    public Player getPlayer(Long player_id)
    {
        return players.get(player_id);
    }
    public void removeBusinessPlan()
    {
        Collection<Player> playerCol = players.values();
        for(Player p:playerCol)
        {
            for(Company c:p.companies)
            {
                c.business_plan = null;
            }
        }
    }
}

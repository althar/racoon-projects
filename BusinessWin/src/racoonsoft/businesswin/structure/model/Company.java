package racoonsoft.businesswin.structure.model;

import racoonsoft.businesswin.structure.data.*;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="company")
public class Company
{
    @DataStructureField(name="id")
    public Long id;

    @DataStructureField(name="name")
    public String name;

    @DataStructureField(name="products_and_capacity")
    public CompanyProductionAndCapacity products_and_capacity = new CompanyProductionAndCapacity();

    @DataStructureField(name="company_sensors")
    public CompanySensors company_sensors = new CompanySensors();

    @DataStructureField(name="company_state")
    public CompanyState company_state = new CompanyState();

    @DataStructureField(name="fixed_assets_and_depreciation")
    public FixedAssetsAndDepreciation fixed_assets_and_depreciation = new FixedAssetsAndDepreciation();

    @DataStructureField(name="business_plan")
    public BusinessPlan business_plan = new BusinessPlan();

    @DataStructureField(name="goods_declaration")
    public GoodsDeclaration goodsDeclaration;

    // Event cards
    @DataStructureField(name="event_card_sell_company")
    public EventCard event_card_sell_company = new EventCard();

    @DataStructureField(name="event_card_contract_entire_volume")
    public EventCard event_card_contract_entire_volume = new EventCard();

    @DataStructureField(name="event_card_efficiency_project_small")
    public EventCard event_card_efficiency_project_small = new EventCard();

    @DataStructureField(name="event_card_efficiency_project_medium")
    public EventCard event_card_efficiency_project_medium = new EventCard();

    @DataStructureField(name="event_card_efficiency_project_large")
    public EventCard event_card_efficiency_project_large = new EventCard();

    @DataStructureField(name="event_card_pocking")
    public EventCard event_card_pocking = new EventCard();

    public Company()
    {
        products_and_capacity = new CompanyProductionAndCapacity();
        company_sensors = new CompanySensors();
        company_state = new CompanyState();
    }
}

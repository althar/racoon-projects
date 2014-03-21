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

    public Company()
    {
        products_and_capacity = new CompanyProductionAndCapacity();
        company_sensors = new CompanySensors();
        company_state = new CompanyState();
    }
}

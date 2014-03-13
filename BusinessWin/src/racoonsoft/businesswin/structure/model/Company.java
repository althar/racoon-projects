package racoonsoft.businesswin.structure.model;

import racoonsoft.businesswin.structure.data.CompanyProductionAndCapacity;
import racoonsoft.businesswin.structure.data.CompanySensors;
import racoonsoft.businesswin.structure.data.CompanyState;
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
    public CompanyProductionAndCapacity products_and_capacity;

    @DataStructureField(name="company_sensors")
    public CompanySensors company_sensors;

    @DataStructureField(name="company_state")
    public CompanyState company_state;

    public Company()
    {
        products_and_capacity = new CompanyProductionAndCapacity();
        company_sensors = new CompanySensors();
        company_state = new CompanyState();
    }
}

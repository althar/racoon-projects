package racoonsoft.businesswin.game.structure.model;

import racoonsoft.businesswin.game.structure.data.CompanyProductionAndCapacity;
import racoonsoft.businesswin.game.structure.data.CompanySensors;
import racoonsoft.businesswin.game.structure.data.CompanyState;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;
import racoonsoft.library.annotations.DataStructureValue;

@DataStructure(name="company")
public class Company
{
    @DataStructureField(name="id")
    public Long id;

    @DataStructureField(name="name")
    public Long name;

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

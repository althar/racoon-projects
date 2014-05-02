package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="production_and_capacity")
public class CompanyProductionAndCapacity
{
    @DataStructureField(name="power_sensor")
    public EconomicsValue power_sensor = new EconomicsValue(0.0,99999.0,3,0.0);

    @DataStructureField(name="power")
    public EconomicsValue power = new EconomicsValue(0.0,99999.0,0,0.0);

    @DataStructureField(name="production_sensor")
    public EconomicsValue production_sensor = new EconomicsValue(0.0,99999.0,3,0.0);

    @DataStructureField(name="production")
    public EconomicsValue production = new EconomicsValue(0.0,99999.0,3,0.0);

    @DataStructureField(name="power_reserve")
    public EconomicsValue power_reserve = new EconomicsValue(0.0,99999.0,3,0.0);

}

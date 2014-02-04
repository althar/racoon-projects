package racoonsoft.businesswin.game.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;
import racoonsoft.library.annotations.DataStructureValue;

@DataStructure(name="company_sensors")
public class CompanySensors
{
    @DataStructureField(name="cash_sensor")
    public EconomicsValue cash_sensor = new EconomicsValue(0.0,99999.0,3,0.0);

    @DataStructureField(name="main_assets_sensor")
    public EconomicsValue main_assets_sensor = new EconomicsValue(0.0,99999.0,3,0.0);

    @DataStructureField(name="variable_costs_sensor")
    public EconomicsValue variable_costs_sensor = new EconomicsValue(0.0,99999.0,3,0.0);

    @DataStructureField(name="constant_costs_sensor")
    public EconomicsValue constant_costs_sensor = new EconomicsValue(0.0,99999.0,3,0.0);
}

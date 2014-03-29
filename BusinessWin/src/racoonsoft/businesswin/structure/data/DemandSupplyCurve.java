package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

import java.util.ArrayList;

@DataStructure(name="demand_supply_curve")
public class DemandSupplyCurve
{
    @DataStructureField(name="items")
    public ArrayList<DemandSupplyCurveItem> items = new ArrayList<DemandSupplyCurveItem>();

    @DataStructureField(name="satisfying_price_supply")
    public DemandSupplyCurveItem satisfying_price_supply = new DemandSupplyCurveItem(0.0,0.0,0.0);

    @DataStructureField(name="current_sell_price")
    public Double current_sell_price = 0.0;
}

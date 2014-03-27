package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

import java.util.ArrayList;

@DataStructure(name="demand_supply_curve")
public class DemandSupplyCurve
{
    @DataStructureField(name="items")
    public ArrayList<DemandSupplyCurveItem> items = new ArrayList<DemandSupplyCurveItem>();
}

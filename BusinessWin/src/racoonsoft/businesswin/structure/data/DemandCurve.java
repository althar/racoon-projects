package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

import java.util.ArrayList;

@DataStructure(name="demand_curve")
public class DemandCurve
{
    @DataStructureField(name="items")
    public ArrayList<DemandCurveItem> items = new ArrayList<DemandCurveItem>();
}

package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="demand_curve_item")
public class DemandCurveItem
{
    @DataStructureField(name="price", description="Цена")
    public Double price;
    @DataStructureField(name="demand", description="Спрос")
    public Double demand;
    public DemandCurveItem(Double price, Double demand)
    {
        this.price = price;
        this.demand = demand;
    }
}

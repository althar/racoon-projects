package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="demand_curve_item")
public class DemandSupplyCurveItem
{
    @DataStructureField(name="price", description="Цена")
    public Double price;
    @DataStructureField(name="demand", description="Спрос")
    public Double demand;
    @DataStructureField(name="supply", description="Предложение")
    public Double supply;
    public DemandSupplyCurveItem(Double price, Double demand, Double supply)
    {
        this.price = price;
        this.demand = demand;
        this.supply = supply;
    }
}

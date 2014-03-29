package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="trade_factors")
public class TradeFactors extends GameBindStructure
{
    @DataStructureField(name="magnification_demand")
    public EconomicsValue magnification_demand = new EconomicsValue(0.0,9999.0,3,0.1);
}

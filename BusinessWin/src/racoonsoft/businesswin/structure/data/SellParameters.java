package racoonsoft.businesswin.structure.data;

import racoonsoft.businesswin.structure.enums.CompanySellReason;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="sell_parameters")
public class SellParameters
{
    @DataStructureField(name="on_sale")
    public Boolean on_sale = false;

    @DataStructureField(name="sell_price")
    public Double sell_price = 0.0;

    @DataStructureField(name="sell_reason")
    public CompanySellReason sell_reason = CompanySellReason.FREE_WILL;
}

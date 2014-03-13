package racoonsoft.businesswin.structure.model;

import racoonsoft.businesswin.structure.data.EconomicsValue;
import racoonsoft.businesswin.structure.data.GameBindStructure;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="goods_declaration")
public class GoodsDeclaration extends GameBindStructure
{
    @DataStructureField(name="value")
    public EconomicsValue value = new EconomicsValue(0.0,99999999.0,0,0.0);
    @DataStructureField(name="price")
    public EconomicsValue price = new EconomicsValue(0.0,99999999.0,0,0.0);
}

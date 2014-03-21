package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="product_price_and_production")
public class ProductPriceAndProduction
{
    @DataStructureField(name="p", description="Цена единицы продукции")
    public Double p = 0.0;

    @DataStructureField(name="q", description="Производство")
    public Double q = 0.0;
}

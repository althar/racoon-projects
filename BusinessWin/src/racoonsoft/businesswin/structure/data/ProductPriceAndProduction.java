package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="product_price_and_production")
public class ProductPriceAndProduction
{
    @DataStructureField(name="p0", description="Цена единицы продукции на нулевом цикле")
    public Double p0 = 0.0;

    @DataStructureField(name="p", description="Цена единицы продукции")
    public Double p = 0.0;

    @DataStructureField(name="q", description="Производство")
    public Double q = 0.0;

    @DataStructureField(name="delta_price", description="Шаг цены")
    public Double delta_price = 0.0;

    @DataStructureField(name="max_price", description="Максимальная цена")
    public Double max_price = 0.0;
}

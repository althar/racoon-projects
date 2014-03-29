package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="industry_performance")
public class IndustryPerformance
{
    @DataStructureField(name="industry_profit_after_taxes", description="Прибыль отрасли после налогов")
    public EconomicsValue industry_profit_after_taxes = new EconomicsValue(1.0, 999.0, 0, 1.0);

    @DataStructureField(name="total_revenue", description="Суммарная выручка")
    public EconomicsValue total_revenue = new EconomicsValue(1.0, 999.0, 0, 1.0);

    @DataStructureField(name="sell_price", description="Цена продаж")
    public EconomicsValue sell_price = new EconomicsValue(1.0, 999.0, 0, 1.0);

    @DataStructureField(name="industry_profit_change", description="Изменение прибыли отрасли")
    public EconomicsValue industry_profit_change = new EconomicsValue(1.0, 999.0, 0, 1.0);

    @DataStructureField(name="industry_costs_change", description="Изменение затрат отрасли")
    public EconomicsValue industry_costs_change = new EconomicsValue(1.0, 999.0, 0, 1.0);

    @DataStructureField(name="taxes", description="Налоговые сборы")
    public EconomicsValue taxes = new EconomicsValue(1.0, 999.0, 0, 1.0);

    @DataStructureField(name="total_power", description="Общая можность")
    public EconomicsValue total_power = new EconomicsValue(1.0, 999.0, 0, 1.0);

    @DataStructureField(name="total_production", description="Общее производство")
    public EconomicsValue total_production = new EconomicsValue(1.0, 999.0, 0, 1.0);

    @DataStructureField(name="power_reserve", description="Резерв мощностей")
    public EconomicsValue power_reserve = new EconomicsValue(1.0, 99999999.0, 2, 1.0);

    @DataStructureField(name="total_net_book_value_of_fixed_assets", description="Общая остаточная стоиомсть основных средств")
    public EconomicsValue total_net_book_value_of_fixed_assets = new EconomicsValue(1.0, 999.0, 0, 1.0);

    @DataStructureField(name="accounts_balance", description="Прибыль отрасли после налогов")
    public EconomicsValue accounts_balance = new EconomicsValue(1.0, 999.0, 0, 1.0);

    @DataStructureField(name="capacity_unit_price", description="Стоимость единицы новой мощности")
    public EconomicsValue capacity_unit_price = new EconomicsValue(1.0, 999.0, 0, 1.0);

    @DataStructureField(name="industry_revenue_after_taxes", description="Прибыль отрасли после налогов")
    public EconomicsValue industry_revenue_after_taxes = new EconomicsValue(1.0, 999.0, 0, 1.0);
}

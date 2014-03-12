package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="company_state")
public class CompanyState
{
    @DataStructureField(name="revenue",description="Выручка")
    public EconomicsValue revenue = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="variable_costs",description="Переменных затраты")
    public EconomicsValue variable_costs = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="operation_profit",description="Операционная прибыль")
    public EconomicsValue operation_profit = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="constant_costs",description="Постоянные затраты")
    public EconomicsValue constant_costs = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="amortization",description="Амортизация")
    public EconomicsValue amortization = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="percent_payment",description="Уплата процентов")
    public EconomicsValue percent_payment = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="profit_before_taxes",description="Прибыль до уплаты налогов")
    public EconomicsValue profit_before_taxes = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="profit_tax",description="Налог на прибыль")
    public EconomicsValue profit_tax = new EconomicsValue(0.0,9999999999999.0,2,0.0);


    @DataStructureField(name="net_profit",description="Чистая прибыль")
    public EconomicsValue net_profit = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="profit_change",description="Изменение прибыли")
    public EconomicsValue profit_change = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="costs_change",description="Изменение затрат")
    public EconomicsValue costs_change = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="cash",description="Денежные средства")
    public EconomicsValue cash = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="net_fixed_assets",description="Остаточная стоимость основных средств")
    public EconomicsValue net_fixed_assets = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="current_passives",description="Текущие пассивы")
    public EconomicsValue current_passives = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="debt",description="Долг")
    public EconomicsValue debt = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="credit_value",description="Размер кредита")
    public EconomicsValue credit_value = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="share_capital",description="Акционерный капитал")
    public EconomicsValue share_capital = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="retained_earnings",description="Нераспределенная прибыль прошлых лет")
    public EconomicsValue retained_earnings = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="ebitda",description="EBITDA")
    public EconomicsValue ebitda = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="noplat",description="NOPLAT")
    public EconomicsValue noplat = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="investments",description="Investments")
    public EconomicsValue investments = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="variable_costs_coefficient",description="Коэффициент Переменных затрат")
    public EconomicsValue variable_costs_coefficient = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="constant_costs_coefficient",description="Коэффициент Переменных затрат")
    public EconomicsValue constant_costs_coefficient = new EconomicsValue(0.0,9999999999999.0,2,0.0);
}

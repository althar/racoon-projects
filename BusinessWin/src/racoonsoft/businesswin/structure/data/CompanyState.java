package racoonsoft.businesswin.structure.data;

import racoonsoft.businesswin.structure.enums.CreditRating;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="company_state")
public class CompanyState
{
    @DataStructureField(name="sell_price",description="Цена продажи")
    public Double sell_price = 0.0;

    @DataStructureField(name="sell_turn",description="Ход продажи")
    public Integer sell_turn = 0;

    @DataStructureField(name="revenue",description="Выручка")
    public EconomicsValue revenue = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="variable_costs",description="Переменных затраты")
    public EconomicsValue variable_costs = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="operation_profit",description="Операционная прибыль")
    public EconomicsValue operation_profit = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="constant_costs",description="Постоянные затраты")
    public EconomicsValue constant_costs = new EconomicsValue(0.0,9999999999999.0,2,0.0);

    @DataStructureField(name="depreciation",description="Амортизация")
    public EconomicsValue depreciation = new EconomicsValue(0.0,9999999999999.0,2,0.0);

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

    @DataStructureField(name="market_share",description="Доля на рынке")
    public EconomicsValue market_share = new EconomicsValue(0.0,10000000.0,2,0.0);

    // More
    @DataStructureField(name="credit_rating_by_edibta",description="Кредитный рейтинг 1")
    private CreditRating credit_rating_by_edibta = CreditRating.UNKNOWN;

    @DataStructureField(name="credit_rating_by_cash",description="Кредитный рейтинг 2")
    private CreditRating credit_rating_by_cash = CreditRating.UNKNOWN;

    @DataStructureField(name="credit_rating_current",description="Текйщий кредитный рейтинг")
    public CreditRating credit_rating_current = CreditRating.UNKNOWN;

    @DataStructureField(name="credit_rate_of_interest_3_years",description="Ставка процентов по кредиту")
    public EconomicsValue credit_rate_of_interest_3_years = new EconomicsValue(0.0,10000000.0,2,0.0);
    @DataStructureField(name="credit_rate_of_interest_5_years",description="Ставка процентов по кредиту")
    public EconomicsValue credit_rate_of_interest_5_years = new EconomicsValue(0.0,10000000.0,2,0.0);
    @DataStructureField(name="credit_rate_of_interest_7_years",description="Ставка процентов по кредиту")
    public EconomicsValue credit_rate_of_interest_7_years = new EconomicsValue(0.0,10000000.0,2,0.0);

    @DataStructureField(name="power",description="Мощность")
    public EconomicsValue power = new EconomicsValue(0.0,10000000.0,2,0.0);

    @DataStructureField(name="sales_volume",description="Объем продаж")
    public EconomicsValue sales_volume = new EconomicsValue(0.0,10000000.0,2,0.0);

    @DataStructureField(name="production_loading",description="Загрузка производства")
    public EconomicsValue production_loading = new EconomicsValue(0.0,10000000.0,2,0.0);

    @DataStructureField(name="free_power",description="Незагруженная мощность")
    public EconomicsValue free_power = new EconomicsValue(0.0,10000000.0,2,0.0);

    @DataStructureField(name="new_power",description="Новые мощности")
    public EconomicsValue new_power = new EconomicsValue(0.0,10000000.0,2,0.0);

    @DataStructureField(name="company_minimum_price",description="Минимальная цена продажи предприятия")
    public EconomicsValue company_minimum_price = new EconomicsValue(0.0,10000000.0,2,0.0);

    @DataStructureField(name="company_minimum_price_with_card",description="Минимальная цена продажи предприятия (фишка)")
    public EconomicsValue company_minimum_price_with_card = new EconomicsValue(0.0,10000000.0,2,0.0);

    @DataStructureField(name="company_minimum_price_with_bankrupt",description="Минимальная цена продажи предприятия (банкротство)")
    public EconomicsValue company_minimum_price_with_bankrupt = new EconomicsValue(0.0,10000000.0,2,0.0);

    @DataStructureField(name="bankrupt",description="Банкрот")
    public Boolean bankrupt = false;

    @DataStructureField(name="bankrupt_turn",description="Банкрот")
    public Integer bankrupt_turn = 0;



    public void setCredit_rating_by_cash(CreditRating credit_rating_by_cash)
    {
        this.credit_rating_by_cash = credit_rating_by_cash;
        credit_rating_current = credit_rating_by_cash;
        if(credit_rating_by_cash.compareTo(credit_rating_by_edibta)<0)
        {
            credit_rating_current = credit_rating_by_edibta;
        }
        // credit_rate_of_interest
        if(credit_rating_current==CreditRating.AAA)
        {
            credit_rate_of_interest_3_years.set(10.0);
            credit_rate_of_interest_5_years.set(10.05);
            credit_rate_of_interest_7_years.set(10.05);
        }
        if(credit_rating_current==CreditRating.BBB)
        {
            credit_rate_of_interest_3_years.set(15.0);
            credit_rate_of_interest_5_years.set(15.05);
            credit_rate_of_interest_7_years.set(15.1);
        }
        if(credit_rating_current==CreditRating.CCC)
        {
            credit_rate_of_interest_3_years.set(20.0);
            credit_rate_of_interest_5_years.set(20.05);
            credit_rate_of_interest_7_years.set(20.1);
        }
        if(credit_rating_current==CreditRating.DDD)
        {
            credit_rate_of_interest_3_years.set(0.0);
            credit_rate_of_interest_5_years.set(0.0);
            credit_rate_of_interest_7_years.set(0.0);
        }
    }
    public void setCredit_rating_by_edibta(CreditRating credit_rating_by_edibta)
    {
        this.credit_rating_by_edibta = credit_rating_by_edibta;
        credit_rating_current = credit_rating_by_cash;
        if(credit_rating_by_cash.compareTo(credit_rating_by_edibta)<0)
        {
            credit_rating_current = credit_rating_by_edibta;
        }
        // credit_rate_of_interest
        if(credit_rating_current==CreditRating.AAA)
        {
            credit_rate_of_interest_3_years.set(10.0);
            credit_rate_of_interest_5_years.set(10.05);
            credit_rate_of_interest_7_years.set(10.05);
        }
        if(credit_rating_current==CreditRating.BBB)
        {
            credit_rate_of_interest_3_years.set(15.0);
            credit_rate_of_interest_5_years.set(15.05);
            credit_rate_of_interest_7_years.set(15.1);
        }
        if(credit_rating_current==CreditRating.CCC)
        {
            credit_rate_of_interest_3_years.set(20.0);
            credit_rate_of_interest_5_years.set(20.05);
            credit_rate_of_interest_7_years.set(20.1);
        }
        if(credit_rating_current==CreditRating.DDD)
        {
            credit_rate_of_interest_3_years.set(0.0);
            credit_rate_of_interest_5_years.set(0.0);
            credit_rate_of_interest_7_years.set(0.0);
        }
    }

}

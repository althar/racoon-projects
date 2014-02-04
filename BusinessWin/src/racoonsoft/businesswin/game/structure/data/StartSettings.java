package racoonsoft.businesswin.game.structure.data;

import racoonsoft.businesswin.game.structure.enums.ElasticityFunctionType;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@DataStructure(name="start_settings")
public class StartSettings
{
    @DataStructureField(name="company_quantity", description="Количество предприятий")
    public EconomicsValue company_quantity = new EconomicsValue(1.0, 9999999999.0, 2, 1.0);

    @DataStructureField(name="start_economics_sector_size", description="Размер сектора экономики в 0 цикл")
    public EconomicsValue start_economics_sector_size = new EconomicsValue(1.0, 9999999999.0, 2, 1.0) ;

    @DataStructureField(name="balance_for_all_companies", description="Размер остатков средств на счетах компаний")
    public EconomicsValue balance_for_all_companies = new EconomicsValue(1.0, 9999999999.0, 2, 1.0);

    @DataStructureField(name="value_assets_for_all_companies", description="Остаточная стоимость активов всех компаний")
    public EconomicsValue value_assets_for_all_companies = new EconomicsValue(1.0, 9999999999.0, 2, 1.0);

    @DataStructureField(name="total_capacity", description="Мощность общая")
    public EconomicsValue total_capacity = new EconomicsValue(1.0, 9999999999.0, 0, 1.0);

    @DataStructureField(name="total_amortization", description="Амортизация, лет")
    public EconomicsValue total_amortization = new EconomicsValue(1.0, 999.0, 0, 1.0);

    @DataStructureField(name="profit_tax", description="Ставка налога на прибыль")
    public EconomicsValue profit_tax = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="elasticity_function_type", description="Функция эластичности")
    public ElasticityFunctionType elasticity_function_type = ElasticityFunctionType.CONSTANT;

    @DataStructureField(name="elasticity_influence_coefficient", description="Коэффициент влияния на эластичность")
    public EconomicsValue elasticity_influence_coefficient = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="elasticity_decrease", description="Уменьшение эластичности")
    public EconomicsValue elasticity_decrease = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="power_coefficient_max", description="Коэффициент мощности, max")
    public EconomicsValue power_coefficient_max = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="power_coefficient_min", description="Коэффициент мощности, min")
    public EconomicsValue power_coefficient_min = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="power_loading_coefficient_max", description="Коэффициент загрузки мощности, max")
    public EconomicsValue power_loading_coefficient_max = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="power_loading_coefficient_min", description="Коэффициент загрузки мощности, min")
    public EconomicsValue power_loading_coefficient_min = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="value_assets_for_all_companies_coefficient_max", description="Коэффициент остаточной стоимости активов всех компаний, max")
    public EconomicsValue value_assets_for_all_companies_coefficient_max = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="value_assets_for_all_companies_coefficient_min", description="Коэффициент остаточной стоимости активов всех компаний, min")
    public EconomicsValue value_assets_for_all_companies_coefficient_min = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="variable_costs_as_a_percentage_of_revenue_coefficient_max", description="Коэффициент переменных затрат как процент от выручки, max")
    public EconomicsValue variable_costs_as_a_percentage_of_revenue_coefficient_max = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="variable_costs_as_a_percentage_of_revenue_coefficient_min", description="Коэффициент переменных затрат как процент от выручки, min")
    public EconomicsValue variable_costs_as_a_percentage_of_revenue_coefficient_min = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="constant_costs_as_a_percentage_of_revenue_coefficient_max", description="Коэффициент постоянных затрат как процент от выручки, max")
    public EconomicsValue constant_costs_as_a_percentage_of_revenue_coefficient_max = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="constant_costs_as_a_percentage_of_revenue_coefficient_min", description="Коэффициент постоянных затрат как процент от выручки, min")
    public EconomicsValue constant_costs_as_a_percentage_of_revenue_coefficient_min = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="variable_costs_decrease_coefficient_project", description="Проект Повышение Эффективности 1 (Малый). Коэффициент сокращения переменных затрат")
    public EconomicsValue variable_costs_decrease_coefficient_small_project = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="constant_costs_decrease_coefficient_project", description="Проект Повышение Эффективности 2 (Средний). Коэффициент сокращения постоянных затрат")
    public EconomicsValue constant_costs_decrease_coefficient_medium_project = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="investment_constant_costs_coefficient_project", description="Проект Повышение Эффективности 2 (Средний). Инвестиции, коэффициент от постоянных затрат")
    public EconomicsValue investment_constant_costs_coefficient_medium_project = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="current_power_percent_project", description="Проект Повышение Эффективности 3 (Большой). % от существующей мощности")
    public EconomicsValue current_power_percent_big_project = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="exceeds_the_net_book_value_of_fixed_assets_coefficient_project", description="Проект Повышение Эффективности 3 (Большой). Коэффициент превышения над остаточной стоимостью основных средств")
    public EconomicsValue exceeds_the_net_book_value_of_fixed_assets_coefficient_big_project = new EconomicsValue(0.0,9999.0,3,0.1);

    @DataStructureField(name="variable_costs_decrease_coefficient_project", description="Проект Повышение Эффективности 3 (Большой). Коэффициент сокращения переменных затрат")
    public EconomicsValue variable_costs_decrease_coefficient_big_project = new EconomicsValue(0.0,9999.0,3,0.1);
}

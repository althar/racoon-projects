package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.helper.*;
import racoonsoft.library.helper.Cloneable;

import java.util.HashMap;

@DataStructure(name="business_plan_item")
public class BusinessPlanItem implements Cloneable<BusinessPlanItem>
{
    public BusinessPlanItem createInstance()
    {
        return new BusinessPlanItem();
    }
    @DataStructureField(name="turn")
    public Integer turn;

    @DataStructureField(name="revenue")
    public Double revenue;

    @DataStructureField(name="variable_costs")
    public Double variable_costs;

    @DataStructureField(name="operation_profit")
    public Double operation_profit;

    @DataStructureField(name="constant_costs")
    public Double constant_costs;

    @DataStructureField(name="depreciation")
    public Double depreciation;

    @DataStructureField(name="profit_before_taxes")
    public Double profit_before_taxes;

    @DataStructureField(name="profit_taxes")
    public Double profit_taxes;

    @DataStructureField(name="net_profit")
    public Double net_profit;

    @DataStructureField(name="cash")
    public Double cash;

    @DataStructureField(name="net_fixed_assets")
    public Double net_fixed_assets;

    @DataStructureField(name="current_liabilities")
    public Double current_liabilities;

    @DataStructureField(name="debt")
    public Double debt;

    @DataStructureField(name="interest_payment")
    public Double interest_payment;

    @DataStructureField(name="credit_size")
    public Double credit_size;

    @DataStructureField(name="share_capital")
    public Double share_capital;

    @DataStructureField(name="retained_earnings")
    public Double retained_earnings;

    @DataStructureField(name="ebitda")
    public Double ebitda;

    @DataStructureField(name="noplat")
    public Double noplat;

    @DataStructureField(name="investments")
    public Double investments;

    @DataStructureField(name="variable_costs_factor")
    public Double variable_costs_factor;

    @DataStructureField(name="constant_costs_factor")
    public Double constant_costs_factor;

    @DataStructureField(name="market_share")
    public Double market_share;
}

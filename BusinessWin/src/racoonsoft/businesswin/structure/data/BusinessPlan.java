package racoonsoft.businesswin.structure.data;

import racoonsoft.library.database.DBRecord;

import java.util.HashMap;

public class BusinessPlan extends DBRecord
{
    private HashMap<String,Object> ProfitAndLossStatement = new HashMap<String, Object>();
    private HashMap<String,Object> BalanceSheet = new HashMap<String, Object>();
    private HashMap<String,Object> CashFlowStatement = new HashMap<String, Object>();

}

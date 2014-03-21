package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;
import racoonsoft.library.database.DBRecord;

import java.util.ArrayList;
import java.util.HashMap;

@DataStructure(name="business_plan")
public class BusinessPlan
{
    @DataStructureField(name="business_plan_items")
    public ArrayList<BusinessPlanItem> items = new ArrayList<BusinessPlanItem>();
}

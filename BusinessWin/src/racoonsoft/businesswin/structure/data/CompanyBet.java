package racoonsoft.businesswin.structure.data;

import racoonsoft.businesswin.structure.enums.CompanySellReason;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="company_bet")
public class CompanyBet extends GameBindStructure
{
    @DataStructureField(name="time")
    public Long time = 0l;

    @DataStructureField(name="company_id")
    public Long company_id = 0l;

    @DataStructureField(name="value")
    public Double value = 0.0;

    @DataStructureField(name="credit")
    public Credit credit = null;

    @DataStructureField(name="need_credit")
    public Boolean need_credit = false;
}

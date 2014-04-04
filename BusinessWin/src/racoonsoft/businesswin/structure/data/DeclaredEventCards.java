package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="declared_event_cards")
public class DeclaredEventCards extends GameBindStructure
{
    @DataStructureField(name="contract_entire_volume")
    public Integer contract_entire_volume  = 0;

    @DataStructureField(name="sell_company ")
    public Integer sell_company  = 0;

    @DataStructureField(name="efficiency_project_small")
    public Integer efficiency_project_small  = 0;

    @DataStructureField(name="efficiency_project_medium")
    public Integer efficiency_project_medium  = 0;

    @DataStructureField(name="efficiency_project_large")
    public Integer efficiency_project_large  = 0;

    @DataStructureField(name="pocking")
    public Integer pocking = 0;

    @DataStructureField(name="pocking_biggest")
    public Double pocking_biggest = 0.0;

    @DataStructureField(name="pocking_top_three")
    public Double pocking_top_three  = 0.0;
}

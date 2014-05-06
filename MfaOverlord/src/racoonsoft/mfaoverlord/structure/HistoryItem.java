package racoonsoft.mfaoverlord.structure;

import racoonsoft.racoonspring.data.annotation.DataStructureField;
import racoonsoft.racoonspring.data.annotation.DataStructureTable;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;

@DataStructureTable(name="history")
public class HistoryItem extends DatabaseStructure
{
    @DataStructureField(name="ip")
    private String ip;
    @DataStructureField(name="user_agent")
    private String user_agent;
    @DataStructureField(name="tracking_id")
    private String tracking_id;
}

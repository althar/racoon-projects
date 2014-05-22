package racoonsoft.circos.structure;

import racoonsoft.racoonspring.data.annotation.DataStructureField;
import racoonsoft.racoonspring.data.annotation.DataStructureTable;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;

@DataStructureTable(name = "history")
public class HistoryStructure extends DatabaseStructure
{
    @DataStructureField(name = "tracking_id")
    private String trackingId;
    @DataStructureField(name = "user_id")
    private String user_id;
    @DataStructureField(name = "user_agent")
    private String userAgent;
    @DataStructureField(name = "ip")
    private String ip;
    @DataStructureField(name = "host")
    private String host;
    @DataStructureField(name = "url")
    private String url;
    @DataStructureField(name = "page")
    private String page;
    @DataStructureField(name = "referer")
    private String referer;
}

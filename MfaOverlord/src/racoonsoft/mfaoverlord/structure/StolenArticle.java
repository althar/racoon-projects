package racoonsoft.mfaoverlord.structure;

import racoonsoft.racoonspring.data.annotation.DataStructureTable;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;

@DataStructureTable(name = "content")
public class StolenArticle extends DatabaseStructure
{
    public Long search_item_id;
    public Long semantics_id;
    public String title;
    public String domain;
    public String url;
    public String text;
}

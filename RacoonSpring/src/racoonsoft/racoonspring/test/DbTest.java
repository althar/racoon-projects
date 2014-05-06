package racoonsoft.racoonspring.test;

import racoonsoft.racoonspring.data.database.DatabaseProcessor;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;
import racoonsoft.racoonspring.helper.Helper;

public class DbTest
{
    public static void main(String[] params)
    {
        try
        {
            DatabaseProcessor proc = new DatabaseProcessor("localhost","mfa_overlord",5432,"althar","bredokbredok2000","org.postgresql.Driver","jdbc:postgresql:");
            proc.connect();
            HistoryItem item = new HistoryItem();
            item.set("ip","111.11.11.11");
            item.set("user_agent","ass");
            item.set("tracking_id","wdffg");

            proc.insertQuery(item).insert();

        }
        catch (Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
        }
    }
}

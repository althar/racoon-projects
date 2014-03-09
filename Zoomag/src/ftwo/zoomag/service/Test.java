package ftwo.zoomag.service;
import ftwo.library.database.DBProcessor;
import ftwo.zoomag.database.DatabaseProcessor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: AlThar
 * Date: 07.03.14
 * Time: 9:07
 * To change this template use File | Settings | File Templates.
 */
public class Test
{
    public static void main(String[] args)
    {
        long start = new Date().getTime();
        try
        {

            DBProcessor mySqlProc1 = new DatabaseProcessor("mysql.myzoom01.mass.hc.ru","wwwmyzoomagru",3306,"myzoom01","ngw0Laej","com.mysql.jdbc.Driver","jdbc:mysql:");
            DBProcessor mySqlProc2 = new DatabaseProcessor("zverovod.mysql","zverovod_db",3306,"zverovod_mysql","s17vpvth","com.mysql.jdbc.Driver","jdbc:mysql:");
            mySqlProc1.connect();
            System.out.println("Done");
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        finally
        {
            System.out.println("Total time: "+(new Date().getTime()-start));
        }
    }
}

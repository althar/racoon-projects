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
            System.out.println(OldSiteIntegrator.normalizePhone("+7(926) 571-3850"));

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

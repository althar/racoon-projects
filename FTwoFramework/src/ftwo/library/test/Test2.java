package ftwo.library.test;

import ftwo.library.crypting.AESCryptoProcessor;
import ftwo.library.helper.Helper;
import ftwo.library.qiwi.CreateBillRequest;
import ftwo.library.qiwi.CreateBillResponse;
import ftwo.library.qiwi.QIWIProcessor;
import ftwo.library.robokassa.RobocassaProcessor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

public class Test2
{
    public static void main(String[] args)
    {
        long start = new Date().getTime();
        try
        {

            QIWIProcessor proc = new QIWIProcessor("http://ishop.qiwi.ru/xml","4.0","2739","koianc2202","JJ_",9900);
            CreateBillResponse resp = proc.createBill("9265713850","",1000,true,false,false,10);
            System.out.println(resp.getXML());
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

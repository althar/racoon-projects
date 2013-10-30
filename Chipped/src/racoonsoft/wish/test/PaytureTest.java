package racoonsoft.wish.test;

import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.ozon.OzonProcessor;
import racoonsoft.library.payture.BlockRequest;
import racoonsoft.library.payture.PaytureProcessor;

public class PaytureTest
{
    public static void main(String[] params)
    {
        try
        {
            Boolean b = null;
            System.out.println(new StringBuilder("Wow").append(b).toString());
            PaytureProcessor proc = new PaytureProcessor("https://sandbox.payture.com/api","MerchantVGifts");
            BlockRequest r1 = proc.blockRequest("14", 10000, "", "", "", "4111111111111112", "12", "15", "LOBO LOSEV", "123");
//            UnblockRequest r2 = proc.unblock("14",6000);
//            ChargeRequest r3 = proc.chargeRequest("14");
//            r1.doRequest();
//            r3.doRequest();
//            r2.doRequest();

            OzonProcessor ozonProc = new OzonProcessor("newmethod","dsdERfdhgD34i23df","https://ows.ozon.ru/PartnerService");
            JSONProcessor JSONProc = ozonProc.getCatalogueStructure();
            System.out.println(JSONProc.toXMLString());
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }
}

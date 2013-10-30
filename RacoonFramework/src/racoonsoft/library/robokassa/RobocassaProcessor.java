package racoonsoft.library.robokassa;

import racoonsoft.library.exceptions.IncorrectPaymentBill;
import racoonsoft.library.helper.HashHelper;
import racoonsoft.library.settings.Settings;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class RobocassaProcessor
{
    public static CreateBillRequest generateRequest(int id,int amount,String comment,String email,String culture) throws IncorrectPaymentBill,NoSuchAlgorithmException,UnsupportedEncodingException
    {
	String robokassa_api_url = Settings.getStringSetting("robokassa_api_url");
	String robokassa_merchant_login = Settings.getStringSetting("robokassa_merchant_login");
	String robokassa_merchant_pass = Settings.getStringSetting("robokassa_merchant_pass_1");

	CreateBillRequest robo_req = new CreateBillRequest(robokassa_api_url
			    , id
			    , robokassa_merchant_login
			    , amount
			    , comment
			    , ""
			    , email
			    , culture
			    , robokassa_merchant_pass);
	return robo_req;
    }
    public static boolean checkRequestResult(int id,int amount,String signature,CreateBillRequest req) throws UnsupportedEncodingException
    {
	String base_string = amount+".000000"+":"+id+":"+Settings.getStringSetting("robokassa_merchant_pass_2");
	String correct_signature = HashHelper.getMD5Hex(base_string, "CP1251");
	System.out.println("base_string: "+base_string);
	System.out.println("correct_signature: "+correct_signature);
	System.out.println("signature: "+signature);
	return req.getSum()==amount&&correct_signature.equalsIgnoreCase(signature);
    }
}

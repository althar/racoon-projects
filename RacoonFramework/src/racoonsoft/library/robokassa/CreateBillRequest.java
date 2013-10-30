package racoonsoft.library.robokassa;

import racoonsoft.library.helper.HashHelper;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class CreateBillRequest
{
    private String APIURL;
    private String MerchantLogin;
    private String Summ;
    private String InvID;
    private String Description;
    private String Signature;
    private String CurrLabel;
    private String Email;
    private String Culture;
    private String MerchantPass;

    public CreateBillRequest(String api_url,int bill_id,String merchantLogin, int summ, String description, String currLabel,String email,String culture,String merchant_pass) throws NoSuchAlgorithmException,UnsupportedEncodingException
    {
	APIURL = api_url;
	MerchantLogin = merchantLogin;
	Summ = String.valueOf(summ);
	InvID = String.valueOf(bill_id);
	Description = description;
	CurrLabel = currLabel;
	Email = email;
	Culture = culture;
	MerchantPass = merchant_pass;
	Signature = createSignature();
    }
    public String getInvID()
    {
	return InvID;
    }
    public int getSum()
    {
	return Integer.valueOf(Summ);
    }
    public String createSignature() throws NoSuchAlgorithmException,UnsupportedEncodingException
    {
	//sMerchantLogin:nOutSum:nInvId:sMerchantPass1
	String baseString = MerchantLogin+":"+Summ+":"+InvID+":"+MerchantPass;
	String MD5 = HashHelper.getMD5Hex(baseString,"CP1251");
	return MD5;
    }
    public String createResultSignature(String merchant_pass_2) throws UnsupportedEncodingException
    {
	String base_string = Summ+":"+InvID+":"+merchant_pass_2;
	return HashHelper.getMD5Hex(base_string, "CP1251");
    }
    public static String createResultSignature(String sum,String inv_id,String merchant_pass_2) throws UnsupportedEncodingException
    {
	String base_string = sum+":"+inv_id+":"+merchant_pass_2;
	return HashHelper.getMD5Hex(base_string, "CP1251");
    }
    public String getRequestString()
    {
	return APIURL+"?MrchLogin="+MerchantLogin
		+"&OutSum="+Summ
		+"&InvId="+InvID
		+"&Desc="+Description
		+"&SignatureValue="+Signature
		+"&Email="+Email
		+"&Culture="+Culture;
    }
}

package racoonsoft.wish.payture;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class UnblockRequest extends PaytureRequest
{
    public UnblockRequest(String key
            , String order_id
            , String apiHost
            , int amount)
    {
        requestName = "Unblock";
        requestFields = new HashMap<String, Object>();
        responseFields = new HashMap<String, String>();
        requestFields.put("Key",key);
        requestFields.put("OrderId",order_id);
        requestFields.put("Amount",amount);
        this.apiHost = apiHost;
        this.merchantApi = key;
    }
    @Override
    protected String generateParameterString() throws UnsupportedEncodingException
    {
        StringBuilder ParamString = new StringBuilder("/Charge?");
        ParamString.append("Key=");
        ParamString.append(requestFields.get("Key"));
        ParamString.append("&OrderId=");
        ParamString.append(requestFields.get("OrderId"));
        ParamString.append("&Amount=");
        ParamString.append(requestFields.get("Amount"));
        return ParamString.toString();
    }
}

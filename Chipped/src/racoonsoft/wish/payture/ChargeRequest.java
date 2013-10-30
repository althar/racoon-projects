package racoonsoft.wish.payture;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class ChargeRequest extends PaytureRequest
{
    public ChargeRequest(String key
            , String order_id
            , String apiHost)
    {
        requestName = "Charge";
        requestFields = new HashMap<String, Object>();
        responseFields = new HashMap<String, String>();
        requestFields.put("Key",key);
        requestFields.put("OrderId",order_id);
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
        return ParamString.toString();
    }
}

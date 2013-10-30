package racoonsoft.wish.payture;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class BlockRequest extends PaytureRequest
{
    public BlockRequest(String key
            , String order_id
            , int amount
            , String payture_id
            , String customer_key
            , String custom_fields
            , String pan
            , String month
            , String year
            , String card_holder
            , String secure_code
            , String apiHost)
    {
        requestName = "Block";
        requestFields = new HashMap<String, Object>();
        responseFields = new HashMap<String, String>();
        requestFields.put("Key",key);
        requestFields.put("OrderId",order_id);
        requestFields.put("Amount",amount);
        requestFields.put("PaytureId",payture_id);
        requestFields.put("CustomerKey",customer_key);
        requestFields.put("CustomerFields",custom_fields);
        requestFields.put("PAN",pan);
        requestFields.put("EMonth",month);
        requestFields.put("EYear",year);
        requestFields.put("CardHolder",card_holder);
        requestFields.put("SecureCode",secure_code);

        this.apiHost = apiHost;
        this.merchantApi = key;
    }
    @Override
    protected String generateParameterString() throws UnsupportedEncodingException
    {
        StringBuilder PayInfo = new StringBuilder();
        PayInfo.append("PAN=");
        PayInfo.append(requestFields.get("PAN"));
        PayInfo.append(";EMonth=");
        PayInfo.append(requestFields.get("EMonth"));
        PayInfo.append(";EYear=");
        PayInfo.append(requestFields.get("EYear"));
        PayInfo.append(";CardHolder=");
        PayInfo.append(requestFields.get("CardHolder"));
        PayInfo.append(";SecureCode=");
        PayInfo.append(requestFields.get("SecureCode"));
        PayInfo.append(";OrderId=");
        PayInfo.append(requestFields.get("OrderId"));
        PayInfo.append(";Amount=");
        PayInfo.append(requestFields.get("Amount"));
        String payInfoEncoded = URLEncoder.encode(PayInfo.toString(), "UTF-8");

        StringBuilder ParamString = new StringBuilder("/Block?");
        ParamString.append("Key=");
        ParamString.append(requestFields.get("Key"));
        ParamString.append("&PayInfo=");
        ParamString.append(payInfoEncoded);
        ParamString.append("&OrderId=");
        ParamString.append(requestFields.get("OrderId"));
        ParamString.append("&Amount=");
        ParamString.append(requestFields.get("Amount"));
        ParamString.append("&PaytureId=");
        ParamString.append(requestFields.get("PaytureId"));
        ParamString.append("&CustomerKey=");
        ParamString.append(requestFields.get("CustomerKey"));
        ParamString.append("&CustomFields=");
        ParamString.append(requestFields.get("CustomFields"));
        return ParamString.toString();
    }
}

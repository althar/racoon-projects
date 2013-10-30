package racoonsoft.wish.payture;

public class PaytureProcessor
{
    private String apiHost;
    private String merchant;

    public PaytureProcessor(String api_host,String merchant)
    {
        this.apiHost = api_host;
        this.merchant = merchant;
    }
    public PayRequest payRequest(String order_id
            , int amount
            , String payture_id
            , String customer_key
            , String custom_fields
            , String pan
            , String month
            , String year
            , String card_holder
            , String secure_code)
    {
        PayRequest request = new PayRequest(merchant, order_id,amount,payture_id,customer_key,custom_fields,pan,month,year,card_holder,secure_code,apiHost);
        return request;
    }

    public BlockRequest blockRequest(String order_id
            , int amount
            , String payture_id
            , String customer_key
            , String custom_fields
            , String pan
            , String month
            , String year
            , String card_holder
            , String secure_code)
    {
        BlockRequest request = new BlockRequest(merchant, order_id,amount,payture_id,customer_key,custom_fields,pan,month,year,card_holder,secure_code,apiHost);
        return request;
    }
    public ChargeRequest chargeRequest(String order_id)
    {
        ChargeRequest request = new ChargeRequest(merchant, order_id,apiHost);
        return request;
    }
    public UnblockRequest unblock(String order_id, int amount)
    {
        UnblockRequest request = new UnblockRequest(merchant, order_id,apiHost,amount);
        return request;
    }
}

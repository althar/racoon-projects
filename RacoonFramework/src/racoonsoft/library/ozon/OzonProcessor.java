package racoonsoft.library.ozon;

import racoonsoft.library.json.JSONProcessor;

import java.util.ArrayList;
import java.util.HashMap;

public class OzonProcessor extends APIProcessor
{
    public OzonProcessor(String login, String password, String url)
    {
	    super(login, password, url);
    }
    public OzonProcessor()
    {
	    super();
    }

    //<editor-fold desc="User">
    public JSONProcessor registerUser(String email,String password,String user_id,String user_agent,String user_ip) throws Exception
    {
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("partnerClientId", user_id);
        params.put("email", email);
        params.put("clientPassword", password);
        params.put("firstName", "DIma");
        params.put("lastName", "Yasha");
        params.put("middleName", "Lord");
        params.put("userAgent", user_agent);
        params.put("userIp", user_ip);
        params.put("SpamSubscribe", "false");
        JSONProcessor proc = executeAPIMethod("ClientService", "PartnerClientRegistration", params,true);
        return proc;
    }
    public JSONProcessor activateCard(String user_id,String code) throws Exception
    {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put("partnerClientId", user_id);
		params.put("Code", code);
		JSONProcessor proc = executeAPIMethod("ClientService", "PartnerClientApplyDiscountCode", params,true);
		return proc;
    }
    public JSONProcessor getUserInfo(String user_id) throws Exception
    {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put("partnerClientId", user_id);
		JSONProcessor proc = executeAPIMethod("ClientService", "GetClientAccountEntryInformation", params,false);
		return proc;
    }
    //</editor-fold>

    //<editor-fold desc="Catalogue">
    public JSONProcessor getCatalogueStructure() throws Exception
    {
		JSONProcessor proc = executeAPIMethod("PageFlowService", "contextInfoGet", new HashMap<String, String>(),false);
		return proc;
    }
    public JSONProcessor getSubCatalogue(String catalogue) throws Exception
    {
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("startGroupName", catalogue);
        JSONProcessor proc = executeAPIMethod("ItemGroupService", "CatalogsGet", params,false);
        return proc;
    }
	//&partnerClientId=4&startItemGroupId=1149085&sortTags=istName&responseTags=ShortDetail&itemsOnPage=10&pageNumber=1
    public JSONProcessor searchItemsGet(String search_text,String sort_tags,String items_on_page,String page_number) throws Exception
    {
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("searchText", search_text);
        params.put("sortTags", sort_tags);
        params.put("responseTags", "ShortDetail");
        params.put("itemsOnPage", items_on_page);
        params.put("pageNumber", page_number);
        JSONProcessor proc = executeAPIMethod("SearchService", "SearchItemsGet", params,false);
        return proc;
    }
    public JSONProcessor getCatalogueItems(String catalogue_id,String sort_tags,String items_on_page,String page_number) throws Exception
    {
		HashMap<String,String> params = new HashMap<String, String>();
		//params.put("partnerClientId", "4");
		params.put("startItemGroupId", catalogue_id);
		params.put("sortTags", sort_tags);
		params.put("responseTags", "ShortDetail");
		params.put("itemsOnPage", items_on_page);
		params.put("pageNumber", page_number);
		JSONProcessor proc = executeAPIMethod("ItemGroupService", "ItemsRecursiveGet", params,false);
		return proc;
    }
    public JSONProcessor getCatalogueItemsByName(String catalogue_name,String sort_tags,String items_on_page,String page_number) throws Exception
    {
        HashMap<String,String> params = new HashMap<String, String>();
        //params.put("partnerClientId", "4");
        params.put("startGroupName", catalogue_name);
        params.put("sortTags", sort_tags);
        params.put("responseTags", "ShortDetail");
        params.put("itemsOnPage", items_on_page);
        params.put("pageNumber", page_number);
        JSONProcessor proc = executeAPIMethod("ItemGroupService", "ItemsRecursiveGet", params,false);
        return proc;
    }
    public JSONProcessor getCatalogueItemsBestOfPrice(String price_from,String price_to) throws Exception
    {
        HashMap<String,String> params = new HashMap<String, String>();
        //params.put("partnerClientId", "4");
        params.put("price", price_from+"-"+price_to);
        params.put("sort", "bests");
        JSONProcessor proc = executeAPIMethod("FacetService", "GetSectionSearchResult", params,true);
        return proc;
    }
    public JSONProcessor getItem(String good_id) throws Exception
    {
		HashMap<String,String> params = new HashMap<String, String>();
		//params.put("partnerClientId", "4");
		params.put("itemId", good_id);
		JSONProcessor proc = executeAPIMethod("ItemService", "ItemGet", params,false);
		return proc;
    }
    public JSONProcessor contextInfoGet() throws Exception
    {
        JSONProcessor proc = executeAPIMethod("PageFlowService", "contextInfoGet", new HashMap<String, String>(),false);
        return proc;
    }
    public JSONProcessor getItemDetails(String good_id) throws Exception
    {
        HashMap<String,String> params = new HashMap<String, String>();
        //params.put("partnerClientId", "4");
        params.put("detailId", good_id);
        JSONProcessor proc = executeAPIMethod("DetailService", "DetailGet", params,false);
        return proc;
    }
    public HashMap<String,String> getItemDetailsFromJSON(JSONProcessor jproc)
    {
        HashMap<String,String> result = new HashMap<String, String>();
        try
        {
            HashMap<String,Object> hashmap = jproc.getStructure();
            ArrayList<HashMap<String,Object>> attributes = (ArrayList<HashMap<String,Object>>)((HashMap<String,Object>)hashmap.get("Detail")).get("ClassAttributes");
            ArrayList<HashMap<String,Object>> itemAttributes = null;
            for(HashMap<String,Object> map:attributes)
            {
                if(!((String)map.get("Name")).equalsIgnoreCase("Изображение"))
                {
                    String name = (String)map.get("Name");
                    String value = (String)map.get("Value");
                    if(name!=null&&value!=null&&value.length()<=50)
                    {
                        result.put(name,value.replace("\\/",""));
                    }
                }
                if(((String)map.get("Name")).equalsIgnoreCase("Характеристики"))
                {
                    result = new HashMap<String, String>();
                    HashMap<String,Object> details = (HashMap<String,Object>)map.get("Detail");
                    itemAttributes =  (ArrayList<HashMap<String,Object>>)details.get("ClassAttributes");
                    for(HashMap<String,Object> attr:itemAttributes)
                    {
                        String name = (String)attr.get("Name");
                        String value = (String)attr.get("Value");
                        if(value.length()<=50)
                        {
                            result.put(name,value.replace("\\/",""));
                        }
                    }
                    break;
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        return result;
    }
    //</editor-fold>

    //<editor-fold desc="Order">
    public JSONProcessor startOrder(String user_id) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            params.put("isPredRelease", "false");
            JSONProcessor proc = executeAPIMethod("CheckoutService", "CheckoutStart", params,false);
            return proc;
    }
    public JSONProcessor getAvalableRegions(String user_id, String order_guid) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            params.put("guidValue", order_guid);
            params.put("isPredRelease", "false");
            JSONProcessor proc = executeAPIMethod("CheckoutService", "DeliveryAddressesGet", params,false);
            return proc;
    }
    public JSONProcessor getDeliveryVariants(String user_id, String order_guid,String address_id,String area_id) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            params.put("guidValue", order_guid);
            params.put("addressId", address_id);
            params.put("areaId", area_id);
            JSONProcessor proc = executeAPIMethod("CheckoutService", "DeliveryVariantsGet", params,false);
            return proc;
    }
    public JSONProcessor getDeliveryChoices() throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            JSONProcessor proc = executeAPIMethod("CheckoutService", "DeliveryChoicesGet", params,false);
            return proc;
    }
    public JSONProcessor getPaymentVariants(String user_id, String order_guid,String address_id,String area_id,String delivery_variant_id) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            params.put("guidValue", order_guid);
            params.put("addressId", address_id);
            params.put("deliveryVariantId", delivery_variant_id);
            params.put("areaId", area_id);
            JSONProcessor proc = executeAPIMethod("CheckoutService", "PaymentsVariantsGet", params,false);
            return proc;
    }
    public JSONProcessor getOrderParametersForCollection(String user_id, String order_guid,String address_id,String area_id,String delivery_variant_id, String payment_type_id,String zip) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            params.put("guidValue", order_guid);
            params.put("addressId", address_id);
            params.put("deliveryVariantId", delivery_variant_id);
            params.put("areaId", area_id);
            params.put("paymentTypeId", payment_type_id);
            params.put("zipCode", zip);
            JSONProcessor proc = executeAPIMethod("CheckoutService", "OrderParametersGetForCollect", params,false);
            return proc;
    }
    public JSONProcessor saveOrder(String user_id
                    , String order_guid
                    , String address_id
                    , String area_id
                    , String delivery_variant_id
                    , String payment_type_id
                    , String zip
                    , String country
                    , String region
                    , String district
                    , String city
                    , String addressee
                    , String address_tail
                    , String comment
                    , String phone
                    , String metro_id
                    , String first_name
                    , String middle_name
                    , String last_name) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            params.put("guidValue", order_guid);
            params.put("addressId", address_id);
            params.put("deliveryVariantId", delivery_variant_id);
            params.put("areaId", area_id);
            params.put("paymentTypeId", payment_type_id);
            params.put("zipCode", zip);
            params.put("country", country);
            params.put("region", region);
            params.put("district", district);
            params.put("city", city);
            params.put("addressee", addressee); // Poluchatel`
            params.put("addressTail", address_tail);// Deliver street house
            params.put("comment", "No_comments");
            params.put("phone", phone);
            params.put("metroId", metro_id);
            params.put("firstName", first_name);
            params.put("middleName", middle_name);
            params.put("lastName", last_name);
            JSONProcessor proc = executeAPIMethod("CheckoutService", "OrderParametersSave", params,true);
            return proc;
    }
    public JSONProcessor checkOrder(String user_id
                    , String order_guid
                    , String address_id
                    , String area_id
                    , String delivery_variant_id
                    , String delivery_choice_id
                    , String payment_type_id
                    , String summ
                    , String preorder) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            params.put("guidValue", order_guid);
            params.put("addressId", address_id);
            params.put("areaId", area_id);
            params.put("deliveryVariantId", delivery_variant_id);
            params.put("deliveryChoiceId", delivery_choice_id);
            params.put("paymentTypeId", payment_type_id);

            params.put("clientAccountSum", summ);
            params.put("isPredRelease", preorder);
            JSONProcessor proc = executeAPIMethod("CheckoutService", "OrderParametersCheck", params,false);
            return proc;
    }
    public JSONProcessor summaryOrder(String user_id
                    , String order_guid
                    , String address_id
                    , String delivery_variant_id
                    , String payment_type_id
                    , String delivery_choice_id
                    , String summ
                    , String preorder) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            params.put("guidValue", order_guid);
            params.put("addressId", address_id);
            params.put("deliveryVariantId", delivery_variant_id);
            params.put("paymentTypeId", payment_type_id);
            params.put("deliveryChoiceId", delivery_choice_id);
            params.put("clientAccountSum", summ);
            JSONProcessor proc = executeAPIMethod("CheckoutService", "OrderSummaryGet", params,false);
            return proc;
    }
    public JSONProcessor createOrder(String user_id
                    , String order_guid
                    , String address_id
                    , String delivery_variant_id
                    , String payment_type_id
                    , String delivery_choice_id
                    , String summ
                    , String phone
                    , String comment
                    , String email
                    , String addressee
                    , String isPreRelease
                    , String metroId
                    , String firstName
                    , String lastName) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            params.put("guidValue", order_guid);
            params.put("addressId", address_id);
            params.put("firstName", firstName);
            params.put("lastName", firstName);
            params.put("lastName", firstName);
            params.put("deliveryVariantId", delivery_variant_id);
            params.put("deliveryChoiceId", delivery_choice_id);
            params.put("paymentTypeId", payment_type_id);

            params.put("phone", phone);
            params.put("comment", comment);
            params.put("email", email);
            params.put("addressee", addressee);
            params.put("isPredrelease", isPreRelease);
            params.put("metroId", metroId);

            params.put("clientAccountSum", summ);
            JSONProcessor proc = executeAPIMethod("CheckoutService", "OrderCreate", params,true);
            return proc;
    }

    // Orders info
    public JSONProcessor getOrders(String user_id) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            JSONProcessor proc = executeAPIMethod("OrderService", "OrdersGet", params,false);
            return proc;
    }
    public JSONProcessor getOrder(String user_id,String orderNumber) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            params.put("orderNumber", orderNumber);
            JSONProcessor proc = executeAPIMethod("OrderService", "OrderGet", params,false);
            return proc;
    }
    //</editor-fold>

    //<editor-fold desc="Cart">
    public JSONProcessor cartAdd(String user_id,String item_id) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            params.put("cartItems", item_id);
            params.put("partnerAgentId", "1");
            params.put("delayCartUpdate", "false");
            JSONProcessor proc = executeAPIMethod("CartService", "CartAdd", params,true);
            return proc;
    }
    public JSONProcessor cartGet(String user_id) throws Exception
    {
            HashMap<String,String> params = new HashMap<String, String>();
            params.put("partnerClientId", user_id);
            JSONProcessor proc = executeAPIMethod("CartService", "CartGet", params,false);
            return proc;
    }
    public JSONProcessor cartRemove(String user_id,String item_ids) throws Exception
    {
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("partnerClientId", user_id);
        params.put("cartItems", item_ids);
        JSONProcessor proc = executeAPIMethod("CartService", "CartRemove", params,true);
        return proc;
    }
    //</editor-fold>

    //<editor-fold desc="Facet">

    // Allows to get category content by search parameters.
    public JSONProcessor getSectionSearchResult(String section_name,String page_size,String page,String price_range,String catalogue_id) throws Exception
    {
        StringBuilder builder = new StringBuilder("{");
        builder.append("\"Data\": {\"SectionName\": ");
        if(section_name!=null&&!section_name.equalsIgnoreCase(""))
        {
            builder.append("\""+section_name+"\"");
        }
        else
        {
            builder.append("null");
        }
        if(page_size!=null&&!page_size.equalsIgnoreCase(""))
        {
            builder.append(",\"ItemsOnPage\":");
            builder.append(page_size);
        }
        builder.append(",\"SearchText\": null,\"FacetParams\": [{\"Name\": \"price\", \"Value\": \""+price_range+"\"},{\"Name\": \"sort\", \"Value\": \"bests\"}");
        builder.append(",{\"Name\": \"store\", \"Value\": \"1\"}");
        if(catalogue_id!=null&&!catalogue_id.equalsIgnoreCase(""))
        {
            builder.append(",{\"Name\": \"catalog\", \"Value\": \""+catalogue_id+"\"}");
        }
        if(page!=null&&!page.equalsIgnoreCase(""))
        {
            builder.append(",{\"Name\": \"page\", \"Value\": "+page+"}");
        }
        builder.append("],\"Attributes\": null" +
                ", \"ItemsOnPage\": ");
        builder.append(page_size);
        builder.append("},\"Login\": \"");
        builder.append(OzonLogin);
        builder.append("\", \"Password\": \"");
        builder.append(OzonPassword);
        builder.append("\",\"PartnerClientId\": null}");


        JSONProcessor proc = executeFacetAPIMethod("FacetService", "GetSectionSearchResult",builder.toString(), new HashMap<String, String>(),true);
        return proc;
    }
    public JSONProcessor getStieSections() throws Exception
    {
        StringBuilder builder = new StringBuilder("");
        builder.append("{\"Login\": \""+OzonLogin+"\",\"Password\": \""+OzonPassword+"\",\"PartnerClientId\": null}");
        JSONProcessor proc = executeFacetAPIMethod("FacetService", "GetSiteSections",builder.toString(), new HashMap<String, String>(),true);
        return proc;
    }
    //</editor-fold>
}

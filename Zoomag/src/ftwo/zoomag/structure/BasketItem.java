package ftwo.zoomag.structure;

import ftwo.library.database.DBRecord;
import java.util.HashMap;

public class BasketItem extends DBRecord
{
    public BasketItem(int good_id,int quantity,String name,String photo_url,int price,String desc,String weight,String articul,String nameForOwl)
    {
        super(new HashMap<String, Object>());
        Fields.put("good_id", good_id);
        Fields.put("quantity", quantity);
        Fields.put("name", name);
        Fields.put("name_for_order", nameForOwl);
        Fields.put("photo_url", photo_url);
        Fields.put("price", price);
        Fields.put("description", desc);
        Fields.put("weight", weight);
        Fields.put("article", articul);
    }
    public void add(int quantity)
    {
	    setValue("quantity", getQuantity()+quantity);
    }
    public int getGoodID()
    {
	    return (int)getIntValue("good_id");
    }
    public int getQuantity()
    {
	    return (int)getIntValue("quantity");
    }
    public String getName()
    {
	    return (String)getStringValue("name");
    }
    public String getNameForOrder()
    {
        return (String)getStringValue("name_for_order");
    }
    public String getPhotoUrl()
    {
	    return (String)getStringValue("photo_url");
    }
    public int getTotalPrice()
    {
        return getGoodPrice()*getIntValue("quantity");
    }
    public int getGoodPrice()
    {
        return Warehouse.getGood(getIntValue("good_id")).getIntValue("price");
    }
}

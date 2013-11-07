package ftwo.zoomag.structure;

import java.util.HashMap;
import java.util.Iterator;

public class Basket
{
    private int UserIdedtifier;

    private HashMap<Integer,Object> BasketItems = new HashMap<Integer,Object>();

    public Basket(int user_id)
    {
	    UserIdedtifier = user_id;
    }
    public int getIdentifier()
    {
	return UserIdedtifier;
    }
    public int get(int good_id)
    {
        BasketItem item = (BasketItem)BasketItems.get(good_id);
        if(item!=null)
        {
                return item.getQuantity();
        }
        return 0;
    }
    public HashMap<Integer,Object> getItems()
    {
	    return BasketItems;
    }
    public BasketItem getItem(int good_id)
    {
        return (BasketItem)BasketItems.get(good_id);
    }
    public int size()
    {
        return getItems().size();
    }
    public int getPrice()
    {
        int total_price = 0;
        Iterator<Object> iter = BasketItems.values().iterator();
        while(iter.hasNext())
        {
                BasketItem item = (BasketItem)iter.next();
                total_price+=item.getTotalPrice();
        }
        return total_price;
    }
    public void addGood(int id,int quantity,String name,String photo_url,int price,String desc,String weight,String articul,String nameForOwl)
    {
        BasketItem item = (BasketItem)BasketItems.get(id);
        if(item==null)
        {
                setGood(id, quantity,name,photo_url,price,desc,weight,articul,nameForOwl);
        }
        else
        {
                item.add(quantity);
        }
    }
    public void setGood(int id,int quantity,String name,String photo_url,int price,String desc,String weight,String articul,String nameForOwl)
    {
        if(quantity>0)
        {
                BasketItem item = new BasketItem(id, quantity,name,photo_url,price,desc,weight,articul,nameForOwl);
                BasketItems.put(id, item);
        }
        else
        {
                BasketItems.remove(id);
        }
    }
    public void clear()
    {
        BasketItems.clear();
    }
    public void clearGood(int id)
    {
	    BasketItems.remove(id);
    }
}

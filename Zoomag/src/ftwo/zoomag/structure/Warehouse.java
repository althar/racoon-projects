package ftwo.zoomag.structure;

import ftwo.zoomag.servlets.BaseServlet;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse
{
    private static HashMap<Integer,Basket> Baskets = new HashMap<Integer, Basket>();
    private static HashMap<Integer,Good> Goods = new HashMap<Integer, Good>();
    private static ArrayList<String> Brands = new ArrayList<String>();
    private static HashMap<String,ArrayList<String>> AnimalFoodTypes = new HashMap<String, ArrayList<String>>();
    private static HashMap<String,HashMap<String,ArrayList<String>>> BrandAnimalFoodTypes = new HashMap<String,HashMap<String,ArrayList<String>>>();

    public static void load() throws SQLException// Load goods, etc.
    {
        try
        {
            BaseServlet.siteIntegrator.start();
            Goods = BaseServlet.dbProc().getGoods();
            Brands = BaseServlet.dbProc().getBrands();
            AnimalFoodTypes = BaseServlet.dbProc().getAnimalFoodTypes(null);
            BrandAnimalFoodTypes = BaseServlet.dbProc().getBrandAnimalFoodTypes();
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }
    
    public static void setGoodInBasket(int user_id,int good_id,int quantity) throws UnsupportedEncodingException
    {
        Basket b = Baskets.get(user_id);
        if(b == null)
        {
            b = new Basket(user_id);
        }
        String name = Goods.get(good_id).getStringValue("name_for_shop");
        String nameForOrder = Goods.get(good_id).getStringValue("name_for_order");
        String description = Goods.get(good_id).getStringValue("description");
        String weight_product = Goods.get(good_id).getStringValue("weight_product");
        String article = Goods.get(good_id).getStringValue("articul");
        String photo_url = URLEncoder.encode(Goods.get(good_id).getStringValue("photo_url"),"UTF-8");
        int price = Goods.get(good_id).getIntValue("price");
        b.setGood(good_id, quantity,name,photo_url,price,description,weight_product,article,nameForOrder);
    }
    public static void putGoodInBasket(int user_id,int good_id) throws UnsupportedEncodingException
    {
        Basket b = Baskets.get(user_id);
        if(b == null)
        {
            b = new Basket(user_id);
            Baskets.put(user_id, b);
        }
        String name = Goods.get(good_id).getStringValue("name_for_shop");
        String nameForOrder = Goods.get(good_id).getStringValue("name_for_order");
        String description = Goods.get(good_id).getStringValue("description");
        String weight_product = Goods.get(good_id).getStringValue("weight_product");
        String article = Goods.get(good_id).getStringValue("articul");
        String photo_url = URLEncoder.encode(Goods.get(good_id).getStringValue("photo_url"),"UTF-8");
        int price = Goods.get(good_id).getIntValue("price");
        b.addGood(good_id, 1, name, photo_url,price,description,weight_product,article,nameForOrder);
    }
    public static Basket getBasket(int user_id)
    {
	return Baskets.get(user_id);
    }
    public static void setUserBasket(int user_id,Basket b)
    {
        Baskets.put(user_id, b);
    }
    public static ArrayList<String> getBrands()
    {
	return Brands;
    }
    public static HashMap<String,ArrayList<String>> getAnimalFoodTypes()
    {
	    return AnimalFoodTypes;
    }
    public static HashMap<String,HashMap<String,ArrayList<String>>> getBrandAnimalFoodTypes()
    {
	return BrandAnimalFoodTypes;
    }
    public static HashMap<String,HashMap<String,ArrayList<String>>> getBrandAnimalFoodTypes(String[] brands)
    {
        HashMap<String,HashMap<String,ArrayList<String>>> res = new HashMap<String, HashMap<String, ArrayList<String>>>();
        for(int i=0; i<brands.length; i++)
        {
                res.put(brands[i].replace(" ", "_"), BrandAnimalFoodTypes.get(brands[i]));
        }
        return res;
    }
    public static Good getGood(int id)
    {
	return Goods.get(id);
    }
    public static ArrayList<Good> getGoods(String keyword
	    ,String weight
	    ,String company
	    ,String food_type
	    ,String food_type_age
	    ,String food_type_category
	    ,String animal
		,String id) throws SQLException
    {
	    return BaseServlet.dbProc().findGoods(keyword, weight, company, food_type, food_type_age, food_type_category, animal,id);
    }
}

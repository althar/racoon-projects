package racoonsoft.wish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.repository.util.ClassUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.User;
import racoonsoft.library.access.UserProcessor;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.ozon.*;
import racoonsoft.library.payture.PayRequest;
import racoonsoft.library.payture.PaytureProcessor;
import racoonsoft.library.xml.XMLProcessor;
import racoonsoft.wish.database.PGSQLDataSource;
import racoonsoft.wish.struct.FacebookFriendList;
import racoonsoft.wish.struct.FacebookFriendWishList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UserController
{
    //<editor-fold desc="Init">
    @Value("${main.domain}")
    public String domain;

    @Autowired
    private OzonProcessor ozonProcessor;

    @Autowired
    private PGSQLDataSource dbProc;

    @Autowired
    private PaytureProcessor paytureProc;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleIOException(Exception ex, HttpServletRequest request) {
        return new ModelAndView("/error/unknown_error");
    }
    //</editor-fold>

    //<editor-fold desc="Main">
    @RequestMapping("/home")
    public ModelAndView home(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("home");
        Enumeration paramNames = request.getParameterNames();

        return model;
    }
    @RequestMapping("/main_choose")
    public ModelAndView mainChoose(HttpServletRequest request) throws Exception
    {

        ModelAndView model = new ModelAndView("main_choose");
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }
        return model;
    }
    @RequestMapping("/terms_of_service")
    public ModelAndView termsOfService(HttpServletRequest request) throws Exception
    {
        ModelAndView model = new ModelAndView("terms_of_service");
        return model;
    }
    @RequestMapping("/terms_of_payment")
    public ModelAndView termsOfPayment(HttpServletRequest request) throws Exception
    {
        ModelAndView model = new ModelAndView("terms_of_payment");
        return model;
    }
    @RequestMapping("/catalogue")
    public ModelAndView catalogue(HttpServletRequest request) throws Exception
    {
        ModelAndView model = new ModelAndView("catalogue");
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }
        CatalogueStructure ozonCatalogue = Catalogue.getInstance().getCatalogueStructure(ozonProcessor);
        model.addObject("catalogue",ozonCatalogue);
        return model;
    }
    @RequestMapping("/get_goods")
    public ModelAndView getGoods(HttpServletRequest request,Integer category_id,Integer page,Integer size) throws Exception
    {
        ModelAndView model = new ModelAndView("catalogue_body");
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }
        GoodListStructure goods = Catalogue.getInstance().getGoodListStructure(ozonProcessor,category_id,size,page);
        model.addObject("goods",goods);
        return model;
    }
    @RequestMapping("/wish_goods")
    public ModelAndView wishGoods(HttpServletRequest request,String id_price,HttpServletResponse response,String to_friends) throws Exception
    {
        ModelAndView model = new ModelAndView("wish_goods");
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }
        String[] pairs = id_price.split("\\|");
        ArrayList<Good> goods = new ArrayList<Good>();
        for(String pair:pairs)
        {
            String[] pair_str = pair.split("\\_");
            if(pair_str.length==3)
            {
                Long good_id = Long.valueOf(pair_str[0]);
                HashMap<String,Object> item = ozonProcessor.getItem(good_id.toString()).getStructure();
                JSONProcessor item_details = ozonProcessor.getItemDetails(good_id.toString());
                HashMap<String,String> characteristics = ozonProcessor.getItemDetailsFromJSON(item_details);
                //HashMap<String,Object> details = ozonProcessor.getItemDetails(good_id.toString()).getStructure();
                if(item.get("Status").toString().equalsIgnoreCase("1")||item.get("Item")==null)
                {
                    return new ModelAndView("error/no_item_error");
                }
                Good good = new Good(item);
                good.setCharacteristics(characteristics);
                goods.add(good);
            }
        }
        model.addObject("goods",goods);
        model.addObject("ids",id_price);
        return model;
    }
    @RequestMapping("/make_wish")
    public ModelAndView makeWish(HttpServletRequest request,String id_price,String reason,Date date,Boolean to_friends
//        ,String delivery_region_id,String delivery_area_id,String delivery_group_id,String delivery_variant_id,Double delivery_summ
    ) throws Exception
    {   request.setCharacterEncoding("UTF-8");
        ModelAndView model = new ModelAndView("main_choose");
        if(reason.equalsIgnoreCase("undefined"))
        {
            reason = "Просто так";
        }
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }
        if(to_friends)
        {
            FacebookFriendList friends = FacebookController.getFriendList(u.getStringValue("facebook_access_token"));
            ModelAndView m = new ModelAndView("gift_friends_list");
            m.addObject("ids",id_price);
            m.addObject("reason",reason);
            m.addObject("date",date);
            m.addObject("friends",friends);
//            m.addObject("delivery_summ",delivery_summ);
//            m.addObject("delivery_region_id",delivery_region_id);
//            m.addObject("delivery_area_id",delivery_area_id);
//            m.addObject("delivery_group_id",delivery_group_id);
//            m.addObject("delivery_variant_id",delivery_variant_id);
            return m;
        }
        String[] pairs = id_price.split("\\|");
        ArrayList<Good> goods = new ArrayList<Good>();
        for(String pair:pairs)
        {
            String[] pair_str = pair.split("\\_");
            if(pair_str.length==3)
            {
                Long good_id = Long.valueOf(pair_str[0]);
                Double price = Double.valueOf(pair_str[1]);
                String good_name = pair_str[2].toString();
                Long user_id = u.getID();
                String facebook_id = u.getStringValue("facebook_id");
                Long wish_id = dbProc.insertWish(facebook_id,user_id,good_id,price,reason,date,good_name
//                        ,delivery_region_id,delivery_area_id,delivery_group_id,delivery_variant_id,delivery_summ
                );
                Good good = new Good(ozonProcessor.getItem(good_id.toString()).getStructure());
                FacebookController.publishOnWall(u.getStringValue("facebook_access_token")
                        ,u.getStringValue("facebook_id")
                        ,""
                        ,domain+"/user/good_details?friends_wish=true&wish_id="+wish_id
                        ,good.getImagePath()
                        ,"Хочу на "+reason
                        ,good_name
                );
            }
        }
        return model;
    }
    @RequestMapping("/make_friend_wish")
    public ModelAndView makeFriendWish(HttpServletRequest request,String id_price,String reason,Date date,String friend_facebook_id
            ,String delivery_region_id,String delivery_area_id,String delivery_group_id,String delivery_variant_id,Double delivery_summ) throws Exception
    {
        ModelAndView model = new ModelAndView("main_choose");
        if(reason.equalsIgnoreCase("undefined"))
        {
            reason = "Просто так";
        }
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }
        String[] pairs = id_price.split("\\|");
        DBRecord friend = dbProc.getUserByFacebookId(friend_facebook_id);
        // We need to create that user...
        if(friend==null)
        {
            HashMap<String,Object> pars = new HashMap<String, Object>();
            pars.put("facebook_id",friend_facebook_id);
            dbProc.executeInsert("users",pars);
        }
        for(String pair:pairs)
        {
            String[] pair_str = pair.split("\\_");
            if(pair_str.length==3)
            {
                Long good_id = Long.valueOf(pair_str[0]);
                Double price = Double.valueOf(pair_str[1]);
                String good_name = pair_str[2].toString();
                Long friend_id = dbProc.createIfNotExistsUser(friend_facebook_id);
                Long wish_id = dbProc.insertWishToFriend(friend_id,friend_facebook_id,good_id,price,reason,date,good_name,delivery_region_id,delivery_area_id,delivery_group_id,delivery_variant_id,delivery_summ);
                Good good = new Good(ozonProcessor.getItem(good_id.toString()).getStructure());
                FacebookController.publishOnWall(u.getStringValue("facebook_access_token")
                        ,friend_facebook_id
                        ,""
                        ,domain+"/user/good_details?friends_wish=true&wish_id="+wish_id
                        ,good.getImagePath()
                        ,"Хочу на "+reason
                        ,good_name
                );
            }

        }


        return model;
    }
    @RequestMapping("/wish_list")
    public ModelAndView friendsWish(HttpServletRequest request,Boolean friends_wish) throws Exception
    {
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }

        ModelAndView model = new ModelAndView("wish_list");
        if(friends_wish)
        {
            FacebookFriendList friends = FacebookController.getFriendList(u.getStringValue("facebook_access_token"));
            ArrayList<FacebookFriendWishList> wishList = dbProc.getFriendsWishList(friends);
            if(wishList.size()>0&&wishList.get(0)!=null)
            {
                model.addObject("wishList",wishList);
            }
            model.addObject("friends_wish",true);
            model.addObject("title","Лист желаний твоих друзей");
        }
        else
        {
            ArrayList<FacebookFriendWishList> wishList = dbProc.getMyWishList(u.getStringValue("facebook_id"),u.getStringValue("facebook_name"));
            if(wishList.size()>0&&wishList.get(0)!=null)
            {
                model.addObject("wishList",wishList);
            }
            model.addObject("user",u);
            model.addObject("friends_wish",false);
            model.addObject("title","Твой лист желаний");
        }
        return model;
    }
    @RequestMapping("/good_details")
    public ModelAndView friendsGoodDetails(HttpServletRequest request,Long wish_id,Boolean friends_wish) throws Exception
    {
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }

        ModelAndView model = new ModelAndView("wish_details");

        DBRecord wish = dbProc.getWish(wish_id);
        Long good_id = wish.getLongValue("good_id");

        JSONProcessor goodDetailsJSON = ozonProcessor.getItemDetails(good_id.toString());
        JSONProcessor goodJSON = ozonProcessor.getItem(good_id.toString());
        Good good = new Good(goodJSON.getStructure());
        HashMap<String,String> characteristics = ozonProcessor.getItemDetailsFromJSON(goodDetailsJSON);

        // Get price, available money and
        DBRecord user = dbProc.getUserByWishId(wish_id);
        ArrayList<DBRecord> members = dbProc.getWishMembers(wish_id);
        int progress = (int)((user.getDoubleValue("amount"))/good.getPrice());
        if(progress>100)
        {
            progress = 100;
        }
        model.addObject("progress",progress);
        model.addObject("good",good);
        model.addObject("amount",(int)(u.getDoubleValue("amount")/100.0));
        model.addObject("user",user);
        model.addObject("wish_id",wish_id);
        model.addObject("members",members);
        model.addObject("to_pay",(int)(good.getPrice()*100-user.getDoubleValue("amount").intValue())/100);
        model.addObject("characteristics",characteristics);
        model.addObject("friends_wish",friends_wish);
        return model;
    }
    //</editor-fold>

    //<editor-fold desc="Payments">
    @RequestMapping("/payment")
    public ModelAndView payment(HttpServletRequest request,Long wish_id,Integer to_pay,String friends_wish) throws Exception
    {
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }
        if(to_pay<0)
        {
            to_pay = 0;
        }
        ModelAndView model = new ModelAndView("payment");
        DBRecord wish = dbProc.getWish(wish_id);

        model.addObject("payment_type","casual");
        model.addObject("wish_id",wish_id);
        model.addObject("user_id",u.getID());
        model.addObject("to_pay",to_pay);
        model.addObject("friends_wish",friends_wish);
        model.addObject("for_user_id",wish.getLongValue("user_id"));
        return model;
    }
    @RequestMapping("/make_payment")
    public void makePayment(HttpServletRequest request,HttpServletResponse response, Long wish_id,String name,Long amount,String card_number,String cvv,String month,String year, String friends_wish) throws Exception
    {
        PrintWriter out = response.getWriter();
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            out.print(false);
            return;
        }

        DBRecord wish = dbProc.getWish(wish_id);
        Long user_id = u.getID();
        Long for_user_id = wish.getLongValue("user_id");
        String order_id = dbProc.createTransaction(user_id,for_user_id,wish_id,amount)+"";
        PayRequest payRequest = paytureProc.payRequest(order_id, amount.intValue(), "", "", "", card_number, month, year, name, cvv);
        boolean result = payRequest.doRequest();
        if(result)
        {
            dbProc.acceptTransaction(order_id, "Средства зачислены на счет");
        }
        else
        {
            dbProc.declineTransaction(order_id, "Средства не были зачислены: "+payRequest.getResponseParameter(""));
        }
        out.print(result+"|"+wish_id);
        return;
    }
    //</editor-fold>

    //<editor-fold desc="Order">
    @RequestMapping("/region_choose")
    public void deliveryChoose(HttpServletRequest request,String good_id,HttpServletResponse response) throws Exception
    {
        User u = FacebookController.getUser(request,dbProc);
        JSONProcessor cartAdd = ozonProcessor.cartAdd("666",good_id+":1");
        JSONProcessor cart = ozonProcessor.cartGet("666");
        JSONProcessor startOrderResp = ozonProcessor.startOrder("666");
        String guid = (String)startOrderResp.getValue("OrderGuid");
        JSONProcessor getAvalableRegionsResp = ozonProcessor.getAvalableRegions("666", guid);
        response.setCharacterEncoding("UTF-8");
        XMLProcessor proc = getAvalableRegionsResp.toXMLProcessor();
        proc.addNode("root","guid",guid);
        response.getWriter().print(proc.toXMLString());
        response.getWriter().flush();
        response.getWriter().close();
    }
    @RequestMapping("/get_regions")
    public void getRegions(HttpServletRequest request,String guid,HttpServletResponse response) throws Exception
    {
        User u = FacebookController.getUser(request,dbProc);
        JSONProcessor getAvalableRegionsResp = ozonProcessor.getAvalableRegions("666", guid);
        response.setCharacterEncoding("UTF-8");
        XMLProcessor proc = getAvalableRegionsResp.toXMLProcessor();
        proc.addNode("root","guid",guid);
        response.getWriter().print(proc.toXMLString());
        response.getWriter().flush();
        response.getWriter().close();
    }
    @RequestMapping("/delivery_choose")
    public void deliveryChoose(HttpServletRequest request,HttpServletResponse response,String area_id,String guid) throws Exception
    {
        User u = FacebookController.getUser(request,dbProc);
        JSONProcessor getDeliveryVariantsResp = ozonProcessor.getDeliveryVariants("666", guid, "0", area_id);
        response.setCharacterEncoding("UTF-8");
        XMLProcessor proc = getDeliveryVariantsResp.toXMLProcessor();
        proc.addNode("root","guid",guid);
        proc.addNode("root","areaId",area_id);
        response.getWriter().print(proc.toXMLString());
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping("/order")
    public ModelAndView order(HttpServletRequest request,Long wish_id) throws Exception
    {
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }

        // 1
        DBRecord wish = dbProc.getWish(wish_id);
        Long good_id = wish.getLongValue("good_id");
        clearCart("666");
        JSONProcessor cartAdd = ozonProcessor.cartAdd("666",good_id+":1");
        JSONProcessor cart = ozonProcessor.cartGet("666");
        ArrayList<Object> cartItems = (ArrayList<Object>)cart.getStructure().get("CartItems");
        if(cartItems==null||cartItems.size()==0)
        {
            ModelAndView model = new ModelAndView("error/message");
            model.addObject("message","В данный момент товара нет на складе. Попробуйте оформление заказа позже...");
            return model;
        }
        JSONProcessor startOrderResp = ozonProcessor.startOrder("666");
        String guid = (String)startOrderResp.getValue("OrderGuid");
        ModelAndView model = new ModelAndView("order");
        model.addObject("guid",guid);
        model.addObject("amount",u.getDoubleValue("amount")/100.0);
        model.addObject("wish_id",wish_id);

        return model;
    }
    @RequestMapping("/make_order")
    public ModelAndView makeOrder(HttpServletRequest request
            ,String guid
            ,String address
            ,String area_id
            ,String delivery_variant_id
            ,String zip
            ,String country
            ,String region
            ,String district
            ,String city
            ,String phone
            ,String metro_id
            ,String first_name
            ,String middle_name
            ,Long wish_id) throws Exception
    {
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }
        JSONProcessor userJson = ozonProcessor.getUserInfo("666");

        JSONProcessor paymentJson = ozonProcessor.getPaymentVariants("666",guid,"0",area_id,delivery_variant_id);
        JSONProcessor getForCollectionJson = ozonProcessor.getOrderParametersForCollection("666",guid,"0",area_id,delivery_variant_id,"8",zip);
        JSONProcessor saveJson = ozonProcessor.saveOrder("666", guid,"0", area_id,delivery_variant_id, "8",zip,country,region,district,city, address,address, "comm",phone, "36152",first_name,middle_name,"none");
        String addressId = saveJson.getStructure().get("NewAddressId").toString();
        JSONProcessor checkJson = ozonProcessor.checkOrder("666",guid, addressId,area_id,delivery_variant_id,"1","8","0","false");
        JSONProcessor summaryJson = ozonProcessor.summaryOrder("666",guid,addressId,delivery_variant_id,"8","1","0","false");
        ModelAndView model = new ModelAndView("create_order");
        model.addObject("guid",guid);
        model.addObject("addressId",addressId);
        model.addObject("deliveryVariantId",delivery_variant_id);
        model.addObject("address",address);
        model.addObject("first_name",first_name);
        model.addObject("middle_name",middle_name);
        return createOrder(request,guid,addressId,delivery_variant_id,address,first_name,middle_name,phone,wish_id);

    }

    @RequestMapping("/create_order")
    public ModelAndView createOrder(HttpServletRequest request, String guid,String address_id,String delivery_variant_id,String address,String first_name,String last_name,String phone,Long wish_id) throws Exception
    {
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }
        JSONProcessor summaryJson = ozonProcessor.summaryOrder("666",guid,address_id,delivery_variant_id,"8","1","0","false");

        Double orderPrice = Double.valueOf(((HashMap<String,Object>)summaryJson.getStructure().get("TotalOrder")).get("ClientAccountPayment").toString());
        Double userAmount = (u.getDoubleValue("amount")/100.0);
        Double deltaPrice = userAmount-orderPrice;
        if(deltaPrice<0)
        {
            ModelAndView model = new ModelAndView("payment");
            model.addObject("payment_type","preorder");
            model.addObject("user_id",u.getID());
            model.addObject("to_pay",-(deltaPrice)+1);
            model.addObject("for_user_id",u.getID());
            model.addObject("guid",guid);
            model.addObject("addressId",address_id);
            model.addObject("deliveryVariantId",delivery_variant_id);
            model.addObject("address",address);
            model.addObject("wish_id",wish_id);
            model.addObject("firstName",first_name);
            model.addObject("lastName",last_name);
            model.addObject("phone",phone);
            return model;
        }
        else// It`s ok, can order...
        {
            JSONProcessor createJson = ozonProcessor.createOrder("666",guid,address_id,delivery_variant_id,"8","1","0",phone,"comm","email@email.com",address,"false","8",first_name,last_name);
            Integer status = createJson.getIntValue("Status");
            ModelAndView model = new ModelAndView("error/order_info");
            if(status==2)
            {
                dbProc.executeNonQuery("UPDATE wish_list SET status=2 WHERE id="+wish_id);
                dbProc.executeNonQuery("UPDATE users SET amount=amount-"+orderPrice*100+" WHERE id="+u.getID());
                model.addObject("order_number"," №"+createJson.getValue("OrderNumber").toString());
                model.addObject("status","был успешно сформирован. В ближайшее время с вами свяжутся");
            }
            else
            {
                model.addObject("status","не был оформлен из-за внутренней ошибки.");
            }
            return model;
        }
    }

    @RequestMapping("/order_info")
    public ModelAndView orderInfo(HttpServletRequest request
            ,String guid
            ,String address
            ,String area_id
            ,String delivery_variant_id
            ,String zip
            ,String country
            ,String region
            ,String district
            ,String city
            ,String phone
            ,String metro_id
            ,String first_name
            ,String middle_name) throws Exception
    {
        User u = FacebookController.getUser(request,dbProc);
        if(u==null)
        {
            return new ModelAndView("redirect:/facebook/login");
        }
        //JSONProcessor userJson = ozonProcessor.getUserInfo("666");

        //JSONProcessor deliveryJson = ozonProcessor.getPaymentVariants("666",guid,"0",area_id,delivery_variant_id);
        //JSONProcessor saveJson = ozonProcessor.saveOrder("666", guid,"0", area_id,delivery_variant_id,"8",zip,country,region,district,city, address,address, "comm",phone,"8",first_name,middle_name,"fuck");
        //JSONProcessor checkJson = ozonProcessor.checkOrder("666",guid,"0",area_id,delivery_variant_id, "1","8", "0","false");

        //JSONProcessor summaryJson = ozonProcessor.summaryOrder("666",guid,"0",delivery_variant_id,"8","1","0","false");

        return new ModelAndView("redirect:/user/payment");
    }
    //</editor-fold>


    private void clearCart(String userLogin) throws Exception
    {
        JSONProcessor cart = ozonProcessor.cartGet(userLogin);
        ArrayList<HashMap<String,Object>> proposalItems = (ArrayList<HashMap<String,Object>>)cart.getStructure().get("ProposalCartItems");
        ArrayList<HashMap<String,Object>> cartItems = (ArrayList<HashMap<String,Object>>)cart.getStructure().get("CartItems");
        String itemIDS = "";
        if(proposalItems!=null)
        {
            for(HashMap<String,Object> map: proposalItems)
            {
                ozonProcessor.cartRemove(userLogin,map.get("ItemId").toString());
            }
        }
        if(cartItems!=null)
        {
            for(HashMap<String,Object> map: cartItems)
            {
                ozonProcessor.cartRemove(userLogin,map.get("ItemId").toString());
            }
        }
    }
}

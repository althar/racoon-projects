package racoonsoft.knauf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.mail.MailMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class PageController extends KnaufController
{
    @RequestMapping(value = "")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        //database.synchronize();
        ModelAndView model = model("main");
        model = addAmount(model,request);
        model = addCatalogue(model,request);
        JSONProcessor goods500 = ozon.ozonProc.getSectionSearchResult("div_tech","4","0","0-2499","");
        JSONProcessor goods1000 = ozon.ozonProc.getSectionSearchResult("div_tech","4","0","2500-4999","");
        JSONProcessor goods2000 = ozon.ozonProc.getSectionSearchResult("div_tech","4","0","5000-9999","");
        JSONProcessor goodsExpensive = ozon.ozonProc.getSectionSearchResult("div_tech","4","0","10000-500000","");
        model.addObject("base_section",true);
        model.addObject("catalogue_name","div_tech");
        model.addObject("title","Luxe");
        model.addObject("goods500",goods500.BodyStructure);
        model.addObject("goods1000",goods1000.BodyStructure);
        model.addObject("goods2000",goods2000.BodyStructure);
        model.addObject("goodsExpensive",goodsExpensive.BodyStructure);
        return model;
    }
    @RequestMapping("/{page}")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response,@PathVariable("page") String page) throws Exception
    {
        ModelAndView model = model(page);
        model = flushAllParameters(request,model);
        model = addAmount(model,request);
        model = addCatalogue(model,request);
        return model;
    }
    @RequestMapping(value = "/catalogue/by_price")
    public ModelAndView catalogueByPrice(HttpServletRequest request, HttpServletResponse response, String catalogue,String price) throws Exception
    {
        ModelAndView model = model("catalogue");
        model = addAmount(model,request);
        model = addCatalogue(model,request);
        if(catalogue!=null)
        {
            model.addObject("catalogue_name",catalogue);
            JSONProcessor goods = ozon.ozonProc.getSectionSearchResult(catalogue,"200","0",price,"");
            model.addObject("goods",goods.BodyStructure);
            model.addObject("by_price",true);
        }
        model.addObject("title",request.getParameter("title"));
        return model;
    }
    @RequestMapping(value = "/catalogue/items/**")
    public ModelAndView catalogue(HttpServletRequest request, HttpServletResponse response, String catalogue,String catalogue_id,String search,String parent_catalogue) throws Exception
    {
//        JSONProcessor best = ozon.ozonProc.getCatalogueItemsBestOfPrice("1000","6000");
        ModelAndView model = model("catalogue");
        model = addAmount(model,request);
        model = addCatalogue(model,request);
        if(parent_catalogue==null||parent_catalogue.equalsIgnoreCase(""))
        {
            parent_catalogue = catalogue;
        }
        model.addObject("parent_catalogue",parent_catalogue);
        JSONProcessor items = ozon.getItems(catalogue_id,catalogue,1,search);
        if(catalogue!=null)
        {
            model.addObject("catalogue_name",catalogue);

            if(catalogue_id==null)
            {
                JSONProcessor goods500 = ozon.ozonProc.getSectionSearchResult(catalogue,"4","0","0-2499","");
                JSONProcessor goods1000 = ozon.ozonProc.getSectionSearchResult(catalogue,"4","0","2500-4999","");
                JSONProcessor goods2000 = ozon.ozonProc.getSectionSearchResult(catalogue,"4","0","5000-9999","");
                JSONProcessor goodsExpensive = ozon.ozonProc.getSectionSearchResult(catalogue,"4","0","10000-5000000","");
                model.addObject("base_section",true);
                model.addObject("catalogue_name",catalogue);
                model.addObject("goods500",goods500.BodyStructure);
                model.addObject("goods1000",goods1000.BodyStructure);
                model.addObject("goods2000",goods2000.BodyStructure);
                model.addObject("goodsExpensive",goodsExpensive.BodyStructure);
            }
        }
        if(catalogue_id!=null)
        {
            model.addObject("catalogue_id",catalogue_id);
        }
        if(search!=null)
        {
            model.addObject("search",search);
        }
        model.addObject("goods",items.getStructure());
        model.addObject("title",request.getParameter("title"));
        return model;
    }
    @RequestMapping(value = "/good")
    public ModelAndView good(HttpServletRequest request, HttpServletResponse response,String id) throws Exception
    {
        ModelAndView model = model("good");
        model = addAmount(model,request);
        model = addCatalogue(model,request);
        JSONProcessor json = ozon.ozonProc.getItemDetails(id);
        JSONProcessor jsonItem = ozon.ozonProc.getItem(id);
        model.addObject("good",json.getStructure());
        model.addObject("good_short",jsonItem.getStructure());
        return model;
    }
    @RequestMapping(value = "/history")
    public ModelAndView history(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = model("history");
        model = addAmount(model,request);
        model = addCatalogue(model,request);
        JSONProcessor json = ozon.ozonProc.getOrders(ozonId(request).toString());
        model.addObject("orders",json.getStructure());
        return model;
    }

    //<editor-fold desc="Order">
    @RequestMapping(value = "/order/address")
    public ModelAndView orderAddress(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = model("order_1");
        model = addAmount(model,request);
//        model = addCatalogue(model,request);
        JSONProcessor json = ozon.ozonProc.startOrder(ozonId(request).toString());
        JSONProcessor jsonBasket = ozon.ozonProc.cartGet(ozonId(request).toString());
        String status = json.getValue("Status").toString();
        if(!status.equalsIgnoreCase("2"))
        {
            return new ModelAndView("redirect:/ozon_error");
        }
        String guid = json.getValue("OrderGuid").toString();
        model.addObject("guid",guid);
        model.addObject("basket",jsonBasket.getStructure());

        return model;
    }
    @RequestMapping(value = "/order/delivery")
    public ModelAndView orderDelivery(HttpServletRequest request, HttpServletResponse response,String guid,String area_id) throws Exception
    {
        String areaId = request.getParameter("locale["+area_id+"]");
        ModelAndView model = model("order_2");
        model = addAmount(model,request);

        JSONProcessor jsonBasket = ozon.ozonProc.cartGet(ozonId(request).toString());
        JSONProcessor jsonDelivery = ozon.ozonProc.getDeliveryVariants(ozonId(request).toString(),guid,"0",areaId);
        model.addObject("guid",guid);
        model.addObject("area_id",areaId);
        model.addObject("basket",jsonBasket.getStructure());
        model.addObject("delivery",jsonDelivery.getStructure());

        return model;
    }
    @RequestMapping(value = "/order/information")
    public ModelAndView orderInformation(HttpServletRequest request, HttpServletResponse response
            ,String guid
            ,String area_id
            ,String delivery_group_id
            ,Double delivery_price) throws Exception
    {
        String deliveryPointAddressId = request.getParameter("delivery-variant-id["+delivery_group_id+"]");
        ModelAndView model = model("order_3");
        model = addAmount(model,request);

        JSONProcessor jsonBasket = ozon.ozonProc.cartGet(ozonId(request).toString());
        model.addObject("guid",guid);
        model.addObject("area_id",area_id);
        model.addObject("delivery_variant_id",delivery_group_id);
        model.addObject("delivery_point_address_id",deliveryPointAddressId);
        model.addObject("delivery_price",delivery_price);
        model.addObject("basket",jsonBasket.getStructure());

        return model;
    }
    @RequestMapping(value = "/order/confirm")
    public ModelAndView orderConfirm(HttpServletRequest request, HttpServletResponse response
            ,String guid
            ,String area_id
            ,String delivery_variant_id
            ,String delivery_point_address_id
            ,String first_name
            ,String middle_name
            ,String last_name
            ,String zip
            ,String country
            ,String region
            ,String district
            ,String city
            ,String address
            ,String addressee
            ,String phone
            ,String comment) throws Exception
    {
        JSONProcessor jsonForCollection = ozon.ozonProc.getOrderParametersForCollection(ozonId(request).toString(),guid,"0",area_id,delivery_variant_id,"8",zip);
        if(delivery_point_address_id==null||delivery_point_address_id.equalsIgnoreCase(""))
        {
            delivery_point_address_id = "0";
        }
        JSONProcessor saveJson = ozon.ozonProc.saveOrder(ozonId(request).toString()
                ,guid
                ,delivery_point_address_id
                ,area_id
                ,delivery_variant_id
                ,"8"
                ,zip
                ,country
                ,region
                ,district
                ,city
                ,addressee
                ,address
                ,comment
                ,phone
                ,"36152"
                ,first_name
                ,middle_name
                ,last_name);
        String newAddressId = saveJson.getValue("NewAddressId").toString();
        Integer status = saveJson.getIntValue("Status");
        if(status!=2)
        {
            ModelAndView errModel = model("api_failed");
            return errModel;
        }
        JSONProcessor summaryJson = ozon.ozonProc.summaryOrder(ozonId(request).toString(), guid,newAddressId,delivery_variant_id,"8","1","0","false");
        String deliveryName = summaryJson.getValue("TotalOrder.DeliveryVariantName").toString();
        String deliverySumm = summaryJson.getValue("TotalOrder.DeliverySumm").toString();
        String fullOrderSumm = summaryJson.getValue("TotalOrder.FullOrderSumm").toString();
        System.out.println(" !!!!!! ");
        System.out.println("AddressId: "+newAddressId+";   deliverySumm: "+deliverySumm);
        System.out.println(" !!!!!! ");
        ModelAndView model = model("order_confirm");
        model = addAmount(model,request);
        JSONProcessor jsonBasket = ozon.ozonProc.cartGet(ozonId(request).toString());
        model.addObject("guid",guid);
        model.addObject("area_id",area_id);
        model.addObject("delivery_variant_id",delivery_variant_id);
        model.addObject("address_id",newAddressId);
        model.addObject("basket",jsonBasket.getStructure());
        model.addObject("address",city+","+address);
        model.addObject("phone",phone);
        model.addObject("first_name",first_name);
        model.addObject("last_name",last_name);
        model.addObject("comment",comment);
        model.addObject("delivery_name",deliveryName);
        model.addObject("delivery_summ",deliverySumm);
        model.addObject("addressee",addressee);
        return model;
    }
    @RequestMapping(value = "/order/done")
    public ModelAndView orderDone(HttpServletRequest request, HttpServletResponse response
            ,String guid
            ,String address_id
            ,String delivery_variant_id
            ,String first_name
            ,String last_name
            ,String addressee
            ,String phone
            ,String comment) throws Exception
    {
        ModelAndView model = model("order_done");
        model = addAmount(model,request);
        JSONProcessor jsonCreate = ozon.ozonProc.createOrder(ozonId(request).toString(),guid, address_id,delivery_variant_id,"8","1","0",phone,comment, "email@email.com",addressee,"false","36152",first_name,last_name);
        Integer status = jsonCreate.getIntValue("Status");
        if(status!=2)
        {
            ModelAndView errModel = model("api_failed");
            return errModel;
        }
        String order_number = jsonCreate.getValue("OrderNumber").toString();
        model.addObject("order_number",order_number);
        return model;
    }
    //</editor-fold>
}

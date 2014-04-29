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
        ModelAndView model = model("main");
        model = addAmount(model,request);
        model = addCatalogue(model,request);
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
    @RequestMapping(value = "/catalogue/items/**")
    public ModelAndView catalogue(HttpServletRequest request, HttpServletResponse response, String catalogue,String catalogue_id,String search) throws Exception
    {
//        JSONProcessor best = ozon.ozonProc.getCatalogueItemsBestOfPrice("1000","6000");
        ModelAndView model = model("catalogue");
        model = addAmount(model,request);
        model = addCatalogue(model,request);
        JSONProcessor items = ozon.getItems(catalogue_id,catalogue,1,search);
        if(catalogue!=null)
        {
            model.addObject("catalogue_name",catalogue);
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
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,String id) throws Exception
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

    //<editor-fold desc="Order">
    @RequestMapping(value = "/order/address")
    public ModelAndView orderAddress(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = model("order_1");
        model = addAmount(model,request);
//        model = addCatalogue(model,request);
        JSONProcessor json = ozon.ozonProc.startOrder(id(request).toString());
        JSONProcessor jsonBasket = ozon.ozonProc.cartGet(id(request).toString());
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
    public ModelAndView orderDelivery(HttpServletRequest request, HttpServletResponse response,String guid) throws Exception
    {
        ModelAndView model = model("order_2");
        model = addAmount(model,request);
//        model = addCatalogue(model,request);
        JSONProcessor json = ozon.ozonProc.startOrder(id(request).toString());
        JSONProcessor jsonBasket = ozon.ozonProc.cartGet(id(request).toString());
        String status = json.getValue("Status").toString();
        if(!status.equalsIgnoreCase("2"))
        {
            return new ModelAndView("redirect:/ozon_error");
        }
        model.addObject("guid",guid);
        model.addObject("basket",jsonBasket.getStructure());

        return model;
    }
    @RequestMapping(value = "/order/information")
    public ModelAndView orderInformation(HttpServletRequest request, HttpServletResponse response,String guid,String area_id) throws Exception
    {
        ModelAndView model = model("order_3");
        model = addAmount(model,request);
//        model = addCatalogue(model,request);
        JSONProcessor json = ozon.ozonProc.startOrder(id(request).toString());
        JSONProcessor jsonBasket = ozon.ozonProc.cartGet(id(request).toString());
        String status = json.getValue("Status").toString();
        if(!status.equalsIgnoreCase("2"))
        {
            return new ModelAndView("redirect:/ozon_error");
        }
        model.addObject("guid",guid);
        model.addObject("area_id",area_id);
        model.addObject("basket",jsonBasket.getStructure());

        return model;
    }
    @RequestMapping(value = "/order/confirm")
    public ModelAndView orderConfirm(HttpServletRequest request, HttpServletResponse response,String guid,String area_id,String delivery_variant_id) throws Exception
    {
        ModelAndView model = model("order_confirm");
        model = addAmount(model,request);
//        model = addCatalogue(model,request);
        JSONProcessor json = ozon.ozonProc.startOrder(id(request).toString());
        JSONProcessor jsonBasket = ozon.ozonProc.cartGet(id(request).toString());
        String status = json.getValue("Status").toString();
        if(!status.equalsIgnoreCase("2"))
        {
            return new ModelAndView("redirect:/ozon_error");
        }
        model.addObject("guid",guid);
        model.addObject("area_id",area_id);
        model.addObject("delivery_variant_id",delivery_variant_id);
        model.addObject("basket",jsonBasket.getStructure());

        return model;
    }
    @RequestMapping(value = "/order/done")
    public ModelAndView orderDone(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = model("order_done");
        model = addAmount(model,request);
//        model = addCatalogue(model,request);
        JSONProcessor json = ozon.ozonProc.startOrder(id(request).toString());
        JSONProcessor jsonBasket = ozon.ozonProc.cartGet(id(request).toString());
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
    //</editor-fold>
}

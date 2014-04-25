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
@RequestMapping("/service")
public class ServiceController extends KnaufController
{
    @RequestMapping("/feedback")
    public ModelAndView feedback(HttpServletRequest request, HttpServletResponse response,String feedback_fio,String feedback_email,String feedback_title,String feedback_text) throws Exception
    {
        MailMessage mess = new MailMessage("dfedorovich85@gmail.com"
                ,"dfedorovich85@gmail.com"
                ,feedback_title
                ,"Обратная связь.<br> От:"+feedback_fio+"  ("+feedback_email+")<br>"+feedback_text+"<br>",new HashMap<String, Object>());
        mail.sendMail(mess);
        ModelAndView model = model("main");
        model = addAmount(model,request);
        model = addCatalogue(model,request);
        return model;
    }
    @RequestMapping(value = "/more_goods",method={RequestMethod.GET})
    public ModelAndView catalogueInner(HttpServletRequest request, HttpServletResponse response, String catalogue,String catalogue_id,Integer page,String search) throws Exception
    {
        ModelAndView model = new ModelAndView("widget/catalogue");
        JSONProcessor items = ozon.getItems(catalogue_id,catalogue,page,search);
        model.addObject("goods",items.getStructure());
        model.addObject("page_number",page);
        model.addObject("search",search);
        return model;
    }
    @RequestMapping("/activate_certificate")
    public ModelAndView activateCert(HttpServletRequest request, HttpServletResponse response,String code) throws Exception
    {
        JSONProcessor json = ozon.ozonProc.activateCard(id(request).toString(),code);
        System.out.println(json.jsonString());
        ModelAndView model = model("certificate_result");
        model = addAmount(model,request);
        if(json.getIntValue("Status")!=2||json.getDoubleValue("DCode.DiscountValue")==0.0)
        {
            model.addObject("success",false);
        }
        else
        {
            model.addObject("success",true);
        }
        model.addObject("cert_amount",json.getValue("DCode.DiscountValue"));

        return model;
    }
    @RequestMapping("/get_basket")
    public ModelAndView getBasket(HttpServletRequest request, HttpServletResponse response,String good_id) throws Exception
    {
        ModelAndView model = new ModelAndView("widget/basket");
        model = addAmount(model,request);
        return model;
    }
    @RequestMapping("/remove_good")
    public ModelAndView removeGood(HttpServletRequest request, HttpServletResponse response,String good_id) throws Exception
    {
        ozon.ozonProc.cartRemove(id(request).toString(),good_id);
        ModelAndView model = new ModelAndView("widget/basket-inner");
        model = addAmount(model,request);
        return model;
    }
    @RequestMapping("/change_good")
    public ModelAndView changeGood(HttpServletRequest request, HttpServletResponse response,String good_id,Integer count) throws Exception
    {
        JSONProcessor json = ozon.addGood(good_id, user(request).getID().toString(), count);
        ModelAndView model = new ModelAndView("widget/basket-inner");
        model = addAmount(model,request);
        return model;
    }
    @RequestMapping("/add_good")
    public ModelAndView addGood(HttpServletRequest request, HttpServletResponse response,String good_id,Integer count) throws Exception
    {
        ModelAndView model = new ModelAndView("plain");
        if(id(request)==-1)
        {
            model.addObject("value","need_registration");
            return model;
        }
        ozon.addGood(good_id,user(request).getID().toString(),count);
        model.addObject("value","ok");
        return model;
    }

}

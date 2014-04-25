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
    @RequestMapping(value = "",method={RequestMethod.GET})
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
    @RequestMapping(value = "/catalogue/items/**",method={RequestMethod.GET})
    public ModelAndView catalogue(HttpServletRequest request, HttpServletResponse response, String catalogue,String catalogue_id,String search) throws Exception
    {
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
    @RequestMapping(value = "/good",method={RequestMethod.GET})
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

}

package racoonsoft.knauf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.json.JSONProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ServiceController extends KnaufController
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
        return model;
    }
    @RequestMapping(value = "/catalogue/items/**",method={RequestMethod.GET})
    public ModelAndView catalogue(HttpServletRequest request, HttpServletResponse response, String catalogue,String catalogue_id) throws Exception
    {
        ModelAndView model = model("catalogue");
        model = addAmount(model,request);
        model = addCatalogue(model,request);
        JSONProcessor items = ozon.getItems(catalogue_id,catalogue);
        model.addObject("goods",items.getStructure());
        model.addObject("title",request.getParameter("title"));
        return model;
    }

    @RequestMapping("/service/activate_certificate")
    public ModelAndView activateCert(HttpServletRequest request, HttpServletResponse response,String code) throws Exception
    {
        JSONProcessor json = ozon.ozonProc.activateCard(id(request).toString(),code);
        System.out.println(json.jsonString());
        ModelAndView model = model("certificate_result");
        if(json.getIntValue("Status")!=2||json.getValue("DCode.Message").toString().contains("Неправильно"))
        {
            model.addObject("success",false);
        }
        else
        {
            model.addObject("success",true);
        }
        model.addObject("amount",json.getValue("DCode.ResultValue"));

        return model;
    }
}

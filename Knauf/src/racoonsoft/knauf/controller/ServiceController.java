package racoonsoft.knauf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.web.controller.MainController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Controller
public class ServiceController extends KnaufController
{
    @RequestMapping("")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return model("main");
    }

    @RequestMapping("/{page}")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response,@PathVariable("page") String page) throws Exception
    {
        ozon.catalogueStructure();
        return model(page);
    }

    @RequestMapping("/service/activate_certificate")
    public ModelAndView activateCert(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = model("certificate_result");
        model.addObject("success",new Random().nextBoolean());
        model.addObject("amount",125.0);
        return model;
    }
}

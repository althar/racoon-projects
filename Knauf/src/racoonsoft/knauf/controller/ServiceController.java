package racoonsoft.knauf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.web.controller.MainController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ServiceController extends KnaufController
{
    @RequestMapping("")
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return model("main");
    }
}

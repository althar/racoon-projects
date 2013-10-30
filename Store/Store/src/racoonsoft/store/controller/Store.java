package racoonsoft.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class Store
{
    @RequestMapping("/")
    public ModelAndView profile(HttpServletRequest req, String cmd)
    {

        ModelAndView model = new ModelAndView("profile");

        model.addObject("keywords", "");
        return model;
    }
    public ModelAndView getErrorView()
    {
        ModelAndView model = new ModelAndView("not_found");
        return model;
    }
}
package racoonsoft.chaos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class User
{
    @RequestMapping("/base/**")
    public ModelAndView adminLogin(HttpServletRequest req, String keyword)
    {
        String path = req.getServletPath();
        path = path.substring(1);
        String[] parts = path.split("\\/");
        if(parts.length<2)
        {
            return getErrorView();
        }
        String type = parts[0];
        String genre = parts[1];
        String[] keywords = new String[parts.length-2];
        for(int i=2; i<parts.length; i++)
        {
            keywords[i-2] = parts[i];
        }
        ModelAndView model = new ModelAndView("main");
        //model.addObject("tbUrl", "Все для кухни");


        model.addObject("keywords", keywords);
        return model;
    }
    public ModelAndView getErrorView()
    {
        ModelAndView model = new ModelAndView("not_found");
        return model;
    }
}
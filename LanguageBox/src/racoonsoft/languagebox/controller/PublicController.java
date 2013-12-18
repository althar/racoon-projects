package racoonsoft.languagebox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class PublicController
{
    @RequestMapping("")
    public ModelAndView main()
    {
        return new ModelAndView("page/public/main");
    }
    @RequestMapping("/functions")
    public ModelAndView functions()
    {
        return new ModelAndView("page/public/functions");
    }
    @RequestMapping("/price")
    public ModelAndView price()
    {
        return new ModelAndView("page/public/price");
    }
    @RequestMapping("/about")
    public ModelAndView about()
    {
        return new ModelAndView("page/public/about");
    }
    @RequestMapping("/faq")
    public ModelAndView faq()
    {
        return new ModelAndView("page/public/faq");
    }
    @RequestMapping("/partners")
    public ModelAndView partners()
    {
        return new ModelAndView("page/public/partners");
    }
}

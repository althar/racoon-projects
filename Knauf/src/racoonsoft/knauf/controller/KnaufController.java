package racoonsoft.knauf.controller;

import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.web.controller.MainController;

public class KnaufController extends MainController
{
    public ModelAndView model(String page)
    {
        ModelAndView model = new ModelAndView("structure/page");
        model.addObject("page",page);
        return model;
    }
    public ModelAndView adminModel(String page)
    {
        ModelAndView model = new ModelAndView("structure/admin-page");
        model.addObject("page",page);
        return model;
    }
}

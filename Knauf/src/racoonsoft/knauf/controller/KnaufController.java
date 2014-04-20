package racoonsoft.knauf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.knauf.service.OzonService;
import racoonsoft.library.web.controller.MainController;

public class KnaufController extends MainController
{
    @Autowired
    protected OzonService ozon;

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

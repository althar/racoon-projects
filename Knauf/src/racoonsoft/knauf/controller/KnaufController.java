package racoonsoft.knauf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.knauf.service.OzonService;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.web.controller.MainController;

import javax.servlet.http.HttpServletRequest;

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
    public ModelAndView addAmount(ModelAndView model,HttpServletRequest request) throws Exception
    {
        String id = id(request).toString();
        JSONProcessor json = ozon.ozonProc.getUserInfo(id);
        Double amount = json.getDoubleValue("ClientAccountEntryInformationForWeb.Accessible");
        model.addObject("amount",amount);
        return model;
    }
}

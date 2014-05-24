package racoonsoft.circos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.circos.service.VkontakteService;
import racoonsoft.racoonspring.data.database.DatabaseProcessor;
import racoonsoft.racoonspring.mail.MailProcessor;
import racoonsoft.racoonspring.mvc.controller.MainController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class CircosController extends MainController
{
    @Autowired
    protected MailProcessor mail;

    @Autowired
    protected DatabaseProcessor database;

    @Autowired
    protected VkontakteService vk;

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
    public ModelAndView partnerModel(String page)
    {
        ModelAndView model = new ModelAndView("structure/partner-page");
        model.addObject("page",page);
        return model;
    }
    public ModelAndView addHint(ModelAndView model,String message) throws Exception
    {
        ArrayList<String> hint = (ArrayList<String>)model.getModel().get("hints");
        if(hint == null)
        {
            hint = new ArrayList<String>();
        }
        hint.add(message);
        model.addObject("hints",hint);
        return model;
    }
}

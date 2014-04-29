package racoonsoft.knauf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.knauf.service.DatabaseService;
import racoonsoft.knauf.service.OzonService;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.mail.MailProcessor;
import racoonsoft.library.web.controller.MainController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class KnaufController extends MainController
{
    @Autowired
    protected OzonService ozon;

    @Autowired
    protected MailProcessor mail;

    @Autowired
    protected DatabaseService database;

    private static ArrayList<String> catalogueCategories = new ArrayList<String>()
    {{
            add("div_tech");
            add("div_appliance");
            add("div_bs");
            add("div_beauty");
            add("div_fashion");
            add("div_gifts");
            add("div_kid");
            add("div_home");
    }};

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
        JSONProcessor jsonCart = ozon.ozonProc.cartGet(id);
        Double amount = json.getDoubleValue("ClientAccountEntryInformationForWeb.Accessible");
        model.addObject("amount",amount);
        model.addObject("cart",jsonCart.getStructure());
        return model;
    }
    public ModelAndView addCatalogue(ModelAndView model,HttpServletRequest request) throws Exception
    {
        String cat = request.getParameter("catalogue");
        JSONProcessor catalogue = ozon.catalogue();
        model.addObject("catalogue",catalogue.getStructure());
        model.addObject("catalogue_categories",catalogueCategories);
        JSONProcessor subcatalogue = null;
        if(cat!=null)
        {
            subcatalogue = ozon.subCatalogue(cat);
            model.addObject("subcatalogue",subcatalogue.getStructure());
            model.addObject("subcatalogue_name",cat);
        }
        return model;
    }

}

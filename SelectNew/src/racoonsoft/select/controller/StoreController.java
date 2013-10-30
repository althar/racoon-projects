package racoonsoft.select.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.database.DBRecord;
import racoonsoft.select.database.PGSQLDataSource;
import racoonsoft.select.service.AerseService;
import racoonsoft.select.service.StoreService;
import racoonsoft.select.structure.CatalogueGood;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

@Controller
@RequestMapping("/")
public class StoreController
{
    @Autowired
    private StoreService store;

    @Autowired
    private PGSQLDataSource dbProc;

    //<editor-fold desc="Catalogue">
    @RequestMapping("/catalog/**")
    public ModelAndView get(HttpServletRequest req,String brand,Integer page,Long id,String keywords,String special) throws Exception
    {
        ModelAndView model = new ModelAndView("body/catalog");

        return model;
    }
    @RequestMapping("/good")
    public ModelAndView goodDetail(Long id) throws Exception
    {
        ModelAndView model = new ModelAndView("body/good");

        return model;
    }
    @RequestMapping("/goods")
    public ModelAndView goods() throws Exception
    {
        ModelAndView model = new ModelAndView("body/goods");

        return model;
    }
    //</editor-fold>

    //<editor-fold desc="Helpers">
    private String getKeywords(String category,String subcategory)
    {
        String keywords = "";
        if(category!=null)
        {
            keywords = category+", купить "+category;
        }
        if(subcategory!=null)
        {
            keywords += ", "+(category+" "+subcategory)+", купить "+(category+" "+subcategory);
        }
        if(keywords!="")
        {
            keywords+=", ";
        }
        return keywords;
    }
    private String getTitle(String category,String subcategory)
    {
        String res = "Купить сантехнику";
        if(category!=null&&!category.equalsIgnoreCase(""))
        {
            res = "Купить "+category;
        }
        if(subcategory!=null)
        {
            res+=" "+subcategory;
        }
        return res;
    }
    private String getRandomArticleTitle()
    {
        String[] titles = new String[10];
        titles[0] = "Интеренет магазин сантехники";
        titles[1] = "Купить сантехнику";
        titles[2] = "Интернет магазин";
        titles[3] = "Выбрать сантехнику";
        titles[4] = "Сантехника онлайн";
        titles[5] = "Покупка сантехники";
        titles[6] = "Сантехника с доставкой";
        titles[7] = "Сантехника со скидкой";
        titles[8] = "Выбор сантехники";
        titles[9] = "Интеренет магазин сантехники";
        Random r = new Random();
        return titles[r.nextInt(10)];
    }
    private String getHeaderByGood(CatalogueGood good)
    {
        String category = good.getStringValue("category").split("\\|")[0];
        if(category.equalsIgnoreCase("Смесители"))
        {
            return "Купить смеситель";
        }
        else if(category.equalsIgnoreCase("Мойки"))
        {
            return "Купить мойку";
        }
        else if(category.equalsIgnoreCase("Измельчители"))
        {
            return "Купить измельчитель";
        }
        else if(category.equalsIgnoreCase("Душевые комплекты"))
        {
            return "Купить душевой комплект";
        }
        else if(category.equalsIgnoreCase("Аксессуары"))
        {
            return "Купить сантехнику";
        }
        return "Купить сантехнику";
    }
    //</editor-fold>

    //<editor-fold desc="Pages">
    @RequestMapping("/about")
    public ModelAndView about() throws Exception
    {
        ModelAndView model = new ModelAndView("body/about");
        return model;
    }
    @RequestMapping("/delivery")
    public ModelAndView delivery() throws Exception
    {
        ModelAndView model = new ModelAndView("body/delivery");
        return model;
    }
    @RequestMapping("/contact")
    public ModelAndView contact() throws Exception
    {
        ModelAndView model = new ModelAndView("body/contact");
        return model;
    }
    @RequestMapping("/guarantees")
    public ModelAndView guarantees()
    {
        ModelAndView model = new ModelAndView("body/guarantees");
        return model;
    }
    @RequestMapping("/payment")
    public ModelAndView payment() throws Exception
    {
        ModelAndView model = new ModelAndView("body/payment");
        return model;
    }
    @RequestMapping("/yandex_4092d262164ace17.html")
    public ModelAndView yandex() throws SQLException
    {
        ModelAndView model = new ModelAndView("/seo/yandex");
        return model;
    }
    @RequestMapping("/")
    public ModelAndView getMain() throws Exception
    {
        ModelAndView model = new ModelAndView("/body/main");
        ArrayList<DBRecord> articles = dbProc.getArticles("Главная","Сантехника|Интернет магазин сантехники");
        model.addObject("articlesTitle","Купить сантехнику");
        model.addObject("articles",articles);
        model.addObject("title","Купить сантехнику");
        model.addObject("metaKeywords","сантехнику");
        return model;
    }

    //</editor-fold>
}
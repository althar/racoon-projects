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

    private Integer pageSize = 60;

    //<editor-fold desc="Catalogue">
    @RequestMapping("/Каталог/**")
    public ModelAndView get(HttpServletRequest req,String brand,Integer page,Long id,String keywords,String special) throws Exception
    {
        String path = req.getServletPath();
        path = path.substring(1);
        String[] cats = path.split("\\/");
        String category = "";
        String subcategory = "";
        if(cats.length>1)
        {
            category = cats[1];
        }
        if(cats.length>2)
        {
            subcategory = cats[2];
        }
        return getGoods(category,subcategory,brand,page,id,keywords,special);
    }
    @RequestMapping("/good")
    public ModelAndView goodDetail(Long id) throws Exception
    {
        ModelAndView model = new ModelAndView("/catalogue/good_detail");
        CatalogueGood g = dbProc.getGood(id);
        model.addObject("good",g);
        return model;
    }
    @RequestMapping("/Товар")
    public ModelAndView good(Long id) throws Exception
    {
        ModelAndView model = new ModelAndView("catalogue/good_page");
        CatalogueGood g = dbProc.getGood(id);
        String category = g.getStringValue("category").split("\\|")[0];
        ArrayList<String> specialTags = dbProc.getSpecial(category);
        ArrayList<CatalogueGood> related = dbProc.getRelatedGoods(id);
        ArrayList<CatalogueGood> similar = dbProc.getSimilarGoods(id);
        ArrayList<DBRecord> articles = dbProc.getArticles("Каталог",g.getStringValue("category").split("\\|")[0]);
        model.addObject("title",getHeaderByGood(g));
        model.addObject("header1",getHeaderByGood(g));
        model.addObject("articlesTitle",getRandomArticleTitle());
        model.addObject("articles",articles);
        model.addObject("good",g);
        model.addObject("related",related);
        model.addObject("similar",similar);
        model.addObject("category",category);
        model.addObject("specialTags",specialTags);
        return model;
    }

    public ModelAndView getGoods(String category,String subcategory,String brand,Integer page,Long id,String keywords,String special) throws Exception
    {
        ModelAndView model = new ModelAndView("/catalogue/catalogue");
        ArrayList<CatalogueGood> goods = dbProc.getGoods(category,subcategory,brand,page,pageSize,id,keywords,special);
        long count = dbProc.getGoodCount(category,subcategory,brand,id,keywords,special);
        ArrayList<String> specialTags = dbProc.getSpecial(category);
        String articleTitle = "";
        if(category.equalsIgnoreCase(""))
        {
            category = "Сантехника";
            articleTitle = "Купить сантехнику";
            model.addObject("category","Сантехника");
            model.addObject("title","Купить сантехнику");
        }
        else
        {
            model.addObject("category",category);
            model.addObject("title","Купить "+category);
            articleTitle = "Купить "+category;
        }
        model.addObject("title",getTitle(category,subcategory));
        model.addObject("metaKeywords",getKeywords(category,subcategory));
        model.addObject("categories",dbProc.getCategories());
        model.addObject("subcategory",subcategory);
        model.addObject("brands",dbProc.getBrands(category,subcategory,keywords,special));
        model.addObject("brand",brand);
        model.addObject("keywords",keywords);
        model.addObject("id",id);
        model.addObject("page",page);
        model.addObject("pages",(count/pageSize)+1);
        model.addObject("goods",goods);
        model.addObject("specialTags",specialTags);
        if(special==null&&subcategory!=null)
        {
            special = subcategory;
        }
        if(special!=null)
        {
            model.addObject("subtitle",category+" "+special);
        }
        ArrayList<DBRecord> articles = dbProc.getArticles("Каталог",category+"|"+subcategory);
        model.addObject("articlesTitle",articleTitle);
        model.addObject("articles",articles);
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
    @RequestMapping("/О нас")
    public ModelAndView about() throws Exception
    {
        ModelAndView model = new ModelAndView("/about");
        ArrayList<DBRecord> articles = dbProc.getArticles("О нас","о нас");
        model.addObject("articlesTitle","О нас");
        model.addObject("articles",articles);
        return model;
    }
    @RequestMapping("/Доставка")
    public ModelAndView delivery() throws Exception
    {
        ModelAndView model = new ModelAndView("/delivery");
        ArrayList<DBRecord> articles = dbProc.getArticles("Доставка","доставка");
        model.addObject("articlesTitle","Доставка");
        model.addObject("articles",articles);
        return model;
    }
    @RequestMapping("/Контакты")
    public ModelAndView contact() throws Exception
    {
        ModelAndView model = new ModelAndView("/contact");
        ArrayList<DBRecord> articles = dbProc.getArticles("Контакты","контакты");
        model.addObject("articlesTitle","Контакты");
        model.addObject("articles",articles);
        return model;
    }
    @RequestMapping("/Корзина")
    public ModelAndView basket()
    {
        ModelAndView model = new ModelAndView("/basket");
        return model;
    }
    @RequestMapping("/Скидки")
    public ModelAndView discount() throws Exception
    {
        ModelAndView model = new ModelAndView("/discount");
        ArrayList<DBRecord> articles = dbProc.getArticles("Скидки","скидки");
        model.addObject("articlesTitle","Скидки");
        model.addObject("articles",articles);
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
        ModelAndView model = new ModelAndView("/main");
        ArrayList<DBRecord> articles = dbProc.getArticles("Главная","Сантехника|Интернет магазин сантехники");
        model.addObject("articlesTitle","Купить сантехнику");
        model.addObject("articles",articles);
        model.addObject("title","Купить сантехнику");
        model.addObject("metaKeywords","сантехнику");
        return model;
    }

    //</editor-fold>
}
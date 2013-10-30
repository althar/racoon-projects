package racoonsoft.select.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.User;
import racoonsoft.library.access.UserProcessor;
import racoonsoft.select.database.PGSQLDataSource;
import racoonsoft.select.service.StoreService;
import racoonsoft.select.structure.Basket;
import racoonsoft.select.structure.BasketStorage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

@Controller
@RequestMapping("/Корзина")
public class BasketController
{
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

        DecimalFormat f = (DecimalFormat) NumberFormat.getNumberInstance();
        f.setGroupingSize(3);
        DecimalFormatSymbols s = f.getDecimalFormatSymbols();
        s.setDecimalSeparator(',');
        s.setGroupingSeparator(' ');
        f.setDecimalFormatSymbols(s);
        f.applyPattern("#,##0.00");

        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, f, true));
    }
    @Autowired
    private PGSQLDataSource dbProc;

    @Autowired
    private StoreService store;

    @RequestMapping("/Вкорзине")
    public ModelAndView addBasket(HttpServletRequest request) throws Exception
    {
        ModelAndView model = new ModelAndView("hbasket");
        String sessionId = UserProcessor.getSessionId(request);
        Basket b = store.getBasket(sessionId);
        model.addObject("basket",b);
        return model;
    }

    @RequestMapping("/")
    public ModelAndView basket(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("basket/basket");
        String sessionId = UserProcessor.getSessionId(request);
        Basket b = store.getBasket(sessionId);
        model.addObject("basket",b);
        return model;
    }

    @RequestMapping("/Добавить")
    public void addGood(HttpServletRequest request, HttpServletResponse response,Long good_id,Integer count) throws Exception
    {
        response.setCharacterEncoding("UTF-8");
        String sessionId = UserProcessor.getCookie(request,"session_id");
        if(count==null)
        {
            store.addGood(sessionId,good_id);
        }
        else
        {
            store.setGood(sessionId,good_id,count);
        }
        Basket b = store.getBasket(sessionId);
        response.getWriter().print(b.getXml().toXMLString());
    }

    //<editor-fold desc="Make order">
    @RequestMapping("/Авторизация")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response,String login,String password,Boolean logout) throws Exception
    {
        ActionResult res = UserProcessor.authorization(request, response, dbProc, false, false);
        if(res.success())
        {
            return new ModelAndView("redirect:/%D0%9A%D0%BE%D1%80%D0%B7%D0%B8%D0%BD%D0%B0/%D0%94%D0%BE%D1%81%D1%82%D0%B0%D0%B2%D0%BA%D0%B0");
        }
        return new ModelAndView("basket/login");
    }
    @RequestMapping("/РегистрацияАвторизация")
    public void createAuth(HttpServletRequest request, HttpServletResponse response,String login,String password) throws Exception
    {
        ActionResult res = UserProcessor.authorization(request, response, dbProc, false, false);
        if(res.success())
        {
            response.getWriter().print(true);
            response.getWriter().flush();
            return;
        }
        else
        {
            if(!dbProc.userExists(login))
            {
                res = UserProcessor.registration(dbProc,request,response,login,password,new HashMap<String, Object>(),new String[]{"CLIENT"});
                if(res.success())
                {
                    response.getWriter().print(true);
                    response.getWriter().flush();
                    return;
                }
            }
        }
        response.getWriter().print(false);
        response.getWriter().flush();
        return;
    }
    @RequestMapping("/Доставка")
    public ModelAndView shipping(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String sessionId = UserProcessor.getSessionId(request);
        Basket b = store.getBasketStorage().getBasket(sessionId);
        ActionResult res = UserProcessor.authorization(request, response, dbProc, false, false);
        ModelAndView model = new ModelAndView("basket/shipping");
        if(!res.success())
        {
            return new ModelAndView("redirect:/%D0%9A%D0%BE%D1%80%D0%B7%D0%B8%D0%BD%D0%B0/%D0%90%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F");
        }

        model.addObject("deliveryVariants",dbProc.getDeliveryVariants());
        model.addObject("basket",b);
        return model;
    }
    @RequestMapping("/Сертификаты")
    public ModelAndView certificate(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionResult res = UserProcessor.authorization(request, response, dbProc, false, false);
        String sessionId = UserProcessor.getSessionId(request);
        Basket b = store.getBasketStorage().getBasket(sessionId);
        if(!res.success())
        {
            return new ModelAndView("redirect:/%D0%9A%D0%BE%D1%80%D0%B7%D0%B8%D0%BD%D0%B0/%D0%90%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F");
        }
        if(b.isEmpty())
        {
            return new ModelAndView("redirect:/%D0%9A%D0%B0%D1%82%D0%B0%D0%BB%D0%BE%D0%B3");
        }
        return new ModelAndView("basket/certificates");
    }
    @RequestMapping("/Сохранить")
    public Object save(HttpServletRequest request, HttpServletResponse response,String comment,Date date,Long delivery_variant,String address) throws Exception
    {
        String sessionId = UserProcessor.getSessionId(request);
        Basket b = store.getBasketStorage().getBasket(sessionId);
        b.setAddress(address);
        b.setComment(comment);
        b.setDeliveryDate(date);
        b.setDeliveryVariant(delivery_variant);
        store.getBasketStorage().addBasket(sessionId,b);
        ActionResult res = UserProcessor.authorization(request, response, dbProc, false, false);
        if(!res.success())
        {
            return new ModelAndView("redirect:/%D0%9A%D0%BE%D1%80%D0%B7%D0%B8%D0%BD%D0%B0/%D0%90%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F");
        }
        if(b.isEmpty())
        {
            return new ModelAndView("redirect:/%D0%9A%D0%B0%D1%82%D0%B0%D0%BB%D0%BE%D0%B3");
        }
        response.getWriter().print(true);
        return null;
    }
    @RequestMapping("/Оформление")
    public ModelAndView order(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("/basket/order");
        String sessionId = UserProcessor.getSessionId(request);
        Basket b = store.getBasketStorage().getBasket(sessionId);
        ActionResult res = UserProcessor.authorization(request, response, dbProc, false, true);
        if(!res.success())
        {
            return new ModelAndView("redirect:/%D0%9A%D0%BE%D1%80%D0%B7%D0%B8%D0%BD%D0%B0/%D0%90%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F");
        }
        if(b.isEmpty())
        {
            return new ModelAndView("redirect:/%D0%9A%D0%B0%D1%82%D0%B0%D0%BB%D0%BE%D0%B3");
        }
        User u = res.getUser();
        Double firstOrderDiscount = 0.0;//store.getFirstOrderDiscount(b,u);
        b.setFirstOrderDiscount(firstOrderDiscount);
        store.getBasketStorage().addBasket(sessionId,b);
        model.addObject("basket",b);
        model.addObject("user",u);
        return model;
    }
    @RequestMapping("/Оформить")
    public ModelAndView makeOrder(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        boolean success = true;
        ModelAndView model = new ModelAndView("/basket/make_order_result");
        String sessionId = UserProcessor.getSessionId(request);
        Basket b = store.getBasketStorage().getBasket(sessionId);
        ActionResult res = UserProcessor.authorization(request, response, dbProc, false, true);
        if(!res.success())
        {
            model.addObject("failureReason","Вы не авторизированы. Пожалуйста вернитесь в каталог и выберите товары заново.");
            success = false;
        }
        if(b.isEmpty())
        {
            model.addObject("failureReason","Корзина пуста, пожалуйста выберите свои товары снова.");
            success = false;
        }
        Long id = store.makeOrder(b,res.getUser());
        b.clear();
        store.getBasketStorage().addBasket(sessionId,b);
        model.addObject("success",success);
        model.addObject("orderId",id);
        return model;
    }
    //</editor-fold>
}
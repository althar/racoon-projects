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
@RequestMapping("/cart")
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

    @RequestMapping("/")
    public ModelAndView basket(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("body/cart");
        String sessionId = UserProcessor.getSessionId(request);
        Basket b = store.getBasket(sessionId);
        model.addObject("basket",b);
        return model;
    }

    @RequestMapping("/add")
    public ModelAndView addGood(HttpServletRequest request, HttpServletResponse response,Long good_id,Integer count) throws Exception
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
        return new ModelAndView("section/empty");
    }

    //<editor-fold desc="Make order">
    @RequestMapping("/make_order")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response,String comment,String address,String city,String name,String phone) throws Exception
    {
        String sessionId = UserProcessor.getSessionId(request);
        Basket b = store.getBasketStorage().getBasket(sessionId);
        b.setAddress(city+", "+address);
        b.setComment(comment);
        b.setDeliveryVariant(4l);
        phone = phone.replace(" ","").replace("(","").replace(")","").replace("-","");
        if(phone.substring(0,1).equalsIgnoreCase("8"))
        {
            phone = "+7"+phone.substring(1);
        }
        b.setPhone(phone);
        b.setName(name);
        store.getBasketStorage().addBasket(sessionId,b);
        Long orderId = store.makeOrder(b);
        ModelAndView model = new ModelAndView("body/thank_you");
        model.addObject("order_id",orderId);
        return model;
    }
    //</editor-fold>
}
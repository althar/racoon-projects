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
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

@Controller
public class StoreController
{
    @Autowired
    private StoreService store;

    @Autowired
    private PGSQLDataSource dbProc;

    //<editor-fold desc="Catalogue">
    @RequestMapping("/good")
    public ModelAndView goodDetail(Long id) throws Exception
    {
        ModelAndView model = new ModelAndView("body/good");
        DBRecord good = dbProc.getGood(id);
        model.addObject("good",good);
        return model;
    }
    @RequestMapping("/order_call")
    public ModelAndView orderCall(String phone,HttpServletResponse resp) throws Exception
    {
        store.orderCall(phone);
        resp.getWriter().print("ok");
        resp.getWriter().close();
        return new ModelAndView("section/empty");
    }
    //</editor-fold>

    //<editor-fold desc="Pages">
    @RequestMapping(value = {"/about"})
    public ModelAndView about() throws Exception
    {
        ModelAndView model = new ModelAndView("body/about");
        return model;
    }
    @RequestMapping(value = {"/delivery","/"})
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
    //</editor-fold>
}
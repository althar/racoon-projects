package racoonsoft.select.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.access.ActionResult;
import racoonsoft.select.database.PGSQLDataSource;
import racoonsoft.select.service.AerseService;
import racoonsoft.select.service.GoodFindService;

import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController
{

    @Autowired
    private PGSQLDataSource dbProc;

    @RequestMapping("/order")
    public ModelAndView getOrder(String login,String password, Long id) throws Exception
    {
        if(login!=null&&login.equalsIgnoreCase("racoon")&&password!=null&&password.equalsIgnoreCase("racoonracoon2000"))
        {
            ModelAndView model = new ModelAndView("/admin/order");
            model.addObject("order_id",id);
            model.addObject("order",dbProc.getOrder(id));
            return model;
        }
        return null;
    }
}

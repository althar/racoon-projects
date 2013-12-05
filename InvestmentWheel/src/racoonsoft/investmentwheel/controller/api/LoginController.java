package racoonsoft.investmentwheel.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.investmentwheel.database.PostgresqlDataSource;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.User;
import racoonsoft.library.access.UserProcessor;
import racoonsoft.library.helper.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class LoginController
{
    @Autowired
    private PostgresqlDataSource dbProc;

    @RequestMapping("/registration")
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response,String school,String role,Boolean do_registration) throws Exception
    {
        HashMap<String,Object> params = new HashMap<String, Object>();

        ModelAndView model = new ModelAndView("page/access");
        return model;
    }
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response,Boolean do_login) throws Exception
    {
        ActionResult res = UserProcessor.authorization(request, response, dbProc);
        User user = res.getUser();

        ModelAndView model = new ModelAndView();
        return model;
    }
}

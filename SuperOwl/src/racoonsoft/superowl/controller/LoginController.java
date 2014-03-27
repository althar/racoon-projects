package racoonsoft.superowl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.superowl.database.PostgresqlDataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/login")
public class LoginController extends SuperOwlController
{
    @RequestMapping("/test")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return new ModelAndView("/structure/base");
    }
}

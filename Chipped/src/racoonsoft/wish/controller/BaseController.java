package racoonsoft.wish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.access.User;
import racoonsoft.wish.database.PGSQLDataSource;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController
{
    @RequestMapping("/")
    public ModelAndView base(HttpServletRequest request) throws Exception
    {
        return new ModelAndView("home");
    }
    @RequestMapping("/google7524a97e9df31f3e.html")
    public ModelAndView html()
    {
        ModelAndView model = new ModelAndView("google7524a97e9df31f3e");

        return model;
    }
    @RequestMapping("/7b2a43d32612.html")
    public ModelAndView html2()
    {
        ModelAndView model = new ModelAndView("7b2a43d32612");

        return model;
    }

}

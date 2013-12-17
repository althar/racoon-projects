package racoonsoft.businesswin.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.businesswin.database.PostgresqlDataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    private PostgresqlDataSource dbProc;

    @RequestMapping("/games")
    public ModelAndView games(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        ModelAndView model = new ModelAndView("json");
        return model;
    }
}

package racoonsoft.knauf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.annotations.RequiresRole;
import racoonsoft.library.web.controller.MainController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
@RequiresRole(role={"ADMIN"},redirectUrl = "/?need_login")
public class AdminController extends KnaufController
{
    @RequestMapping("")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return adminModel("main");
    }
}

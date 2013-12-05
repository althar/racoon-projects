package racoonsoft.investmentwheel.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.investmentwheel.database.PostgresqlDataSource;
import racoonsoft.library.access.ActionResult;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    private PostgresqlDataSource dbProc;

}

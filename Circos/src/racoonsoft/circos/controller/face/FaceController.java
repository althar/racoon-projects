package racoonsoft.circos.controller.face;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.circos.database.PostgresqlDataSource;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.UserProcessor;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.web.controller.MainController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class FaceController extends MainController
{
    @RequestMapping("")
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return new ModelAndView("welcome");
    }
}

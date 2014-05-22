package racoonsoft.circos.controller.face;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.racoonspring.data.structure.ActionResult;
import racoonsoft.racoonspring.mvc.controller.MainController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FaceController extends MainController
{
    @RequestMapping("")
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionResult res = accessProc.logout(request);
        return new ModelAndView("welcome");
    }
}

package racoonsoft.circos.controller.face;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import racoonsoft.circos.controller.CircosController;
import racoonsoft.racoonspring.data.structure.ActionResult;
import racoonsoft.racoonspring.mvc.controller.MainController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FaceController extends CircosController
{
    @RequestMapping("")
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return model("main");
    }
}

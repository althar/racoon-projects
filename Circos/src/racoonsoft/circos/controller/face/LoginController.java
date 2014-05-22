package racoonsoft.circos.controller.face;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.circos.database.PostgresqlDataSource;
import racoonsoft.racoonspring.data.json.JSONProcessor;
import racoonsoft.racoonspring.data.structure.ActionResult;
import racoonsoft.racoonspring.mvc.controller.MainController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class LoginController extends MainController
{
    @RequestMapping("/registration")
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response,String role) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        ActionResult result = accessProc.registration(request, response, new HashMap<String, Object>(), new String[]{role});
        jsonMap.put("success", result.success());
        jsonMap.put("result", result.getResult());
        JSONProcessor json = new JSONProcessor(jsonMap);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/confirm")
    public ModelAndView confirm(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionResult result = accessProc.confirm(request);
        ModelAndView model = new ModelAndView("json");
        return model;
    }
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        ActionResult result = accessProc.authorization(request, response,true);
        jsonMap.put("success", !result.anonymous());
        jsonMap.put("result", result.getResult());
        jsonMap.put("session_id", result.getData("session_id"));
        JSONProcessor json = new JSONProcessor(jsonMap);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
}

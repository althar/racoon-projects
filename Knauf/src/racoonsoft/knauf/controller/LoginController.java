package racoonsoft.knauf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.UserProcessor;
import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.json.JSONProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class LoginController
{
    @Autowired
    private DBProcessor dbProc;

    @RequestMapping("/registration")
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response,String role) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        ActionResult result = UserProcessor.registration(dbProc,request,response,new HashMap<String, Object>(),new String[]{role});
        jsonMap.put("success", result.success());
        jsonMap.put("result", result.getResult());
        JSONProcessor json = new JSONProcessor(jsonMap);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        ActionResult result = UserProcessor.authorization(request, response, dbProc);
        jsonMap.put("success", !result.anonymous());
        jsonMap.put("result", result.getResult());
        jsonMap.put("session_id", result.getData("session_id"));
        JSONProcessor json = new JSONProcessor(jsonMap);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
}

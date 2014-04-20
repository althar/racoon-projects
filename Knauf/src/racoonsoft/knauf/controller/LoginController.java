package racoonsoft.knauf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.UserProcessor;
import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.json.JSONProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/auth")
public class LoginController
{
    @Autowired
    private DBProcessor dbProc;
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
    @RequestMapping("/registration")
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response
//            ,@RequestParam(value = "status", required = false, defaultValue = "01.01.2000")Date birthday
                                    ) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("first_name",request.getParameter("first_name"));
        params.put("last_name",request.getParameter("last_name"));
        params.put("gender",request.getParameter("gender"));
//        params.put("birthday",birthday);
        params.put("email",request.getParameter("email"));
        params.put("phone",request.getParameter("phone"));
        params.put("company",request.getParameter("company"));
        params.put("address",request.getParameter("address"));
        params.put("post",request.getParameter("post"));

        ActionResult result = UserProcessor.registration(dbProc,request,response,params,new String[]{"CLIENT"});

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
        ActionResult result = UserProcessor.authorization(request, response, dbProc);
        ModelAndView model = new ModelAndView("structure/page");
        model.addObject("page","main");
        if(result.anonymous())
        {
            model.addObject("login_error","Отказано в доступе");
        }

        return model;
    }
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        ActionResult result = UserProcessor.logout(request);
        ModelAndView model = new ModelAndView("redirect:/");
        return model;
    }
}

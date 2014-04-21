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
import racoonsoft.library.access.ActionResultCode;
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
public class LoginController extends KnaufController
{
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
    @RequestMapping("/registration")
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response
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
        params.put("ozon_login","ozon."+request.getParameter("login"));

        ActionResult result = UserProcessor.registration(dbProc,request,response,params,new String[]{"CLIENT"});
        ModelAndView model = new ModelAndView("redirect:/registration");
        model = flushAllParameters(request,model);
        if(result.anonymous())
        {
            if(result.getResult()== ActionResultCode.REGISTRATION_FAILED_ALREADY_EXISTS)
            {
                model.addObject("registration_error","Такая почта уже существует");
            }
            else
            {
                model.addObject("registration_error","Введите все данные");
            }
            return model;
        }
        if(!result.anonymous())
        {
            JSONProcessor proc = ozon.ozonProc.registerUser("ozon."+request.getParameter("login"),request.getParameter("password"),result.getUser().getID().toString(),"Mozilla","127.0.0.1");
            Integer status = proc.getIntValue("Status");
            if(status!=2)
            {
                model.addObject("registration_error","Такая почта уже существует");
                return model;
            }
        }
        return new ModelAndView("redirect:/");
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
            return model;
        }
        else
        {
            return new ModelAndView("redirect:/");
        }
    }
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        ActionResult result = UserProcessor.logout(request);
        ModelAndView model = new ModelAndView("redirect:/");
        return model;
    }
}

package racoonsoft.languagebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.User;
import racoonsoft.library.access.UserProcessor;
import racoonsoft.library.helper.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class LoginController
{
    @Autowired
    private PostgresqlDataSource dbProc;

    @RequestMapping("/choose_registration")
    public ModelAndView chooseRegistration(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return new ModelAndView("page/public/choose_registration");
    }
    @RequestMapping("/registration")
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response,String school,String role,Boolean do_registration) throws Exception
    {
        HashMap<String,Object> params = new HashMap<String, Object>();
        if(school!=null)
        {
            params.put("school",school);
        }
        String[] roles = new String[]{role};
        ActionResult res = UserProcessor.registration(dbProc,request,response,params,roles);
        if(res.success())
        {
//            User u = res.getUser();
//            String domain = new URL(request.getRequestURL().toString()).getHost();
//            return new ModelAndView("redirect:/http://"+school+"."+domain+"/service/"+role.toLowerCase());
            return new ModelAndView("redirect:/login");
        }
        ModelAndView model = new ModelAndView("page/public/access");
        model.addObject("widget","registration");
        model.addObject("role",role);
        if(do_registration!=null)
        {
            model.addObject("error","Пользователь уже существует");
        }
        return model;
    }
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response,Boolean do_login) throws Exception
    {
        ActionResult res = UserProcessor.authorization(request, response, dbProc);
        User user = res.getUser();
        if(user.isAnonymous())
        {
            ModelAndView model = new ModelAndView("page/public/access");
            model.addObject("widget","login");
            if(do_login!=null)
            {
                model.addObject("error","Неверный логин и пароль");
            }
            return model;
        }
        String school = user.getStringValue("school");
        String domain = new URL(request.getRequestURL().toString()).getHost();
        if(user.hasRole("TUTOR"))
        {
            return new ModelAndView("redirect:http://"+school+"."+ StringHelper.getDomainByLevel(domain,2) +"/service/tutor");
        }
        else if(user.hasRole("SCHOOL"))
        {
            return new ModelAndView("redirect:http://"+school+"."+ StringHelper.getDomainByLevel(domain,2) +"/service/school");
        }
        else if(user.hasRole("STUDENT"))
        {
            return new ModelAndView("redirect:http://"+ StringHelper.getDomainByLevel(domain,2) +"/service/student");
        }
        UserProcessor.logout(request);
        ModelAndView model = new ModelAndView("page/public/access");
        model.addObject("widget","login");
        model.addObject("error","Сбой при входе. Попробуйте еще раз.");
        return model;
    }
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response,Boolean do_login) throws Exception
    {
        ActionResult res = UserProcessor.logout(request);
        ModelAndView model = new ModelAndView("page/public/main");
        model.addObject("widget","login");
        model.addObject("error","Сбой при входе. Попробуйте еще раз.");
        return model;
    }
}

package racoonsoft.racoonspring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.access.User;
import racoonsoft.library.database.DBProcessor;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

@Controller
public class MainController
{
    @Autowired
    public DBProcessor dbProc;

    public HashMap<String,Object> getParameters(HttpServletRequest request)
    {
        HashMap<String,Object> result = new HashMap<String, Object>();
        Enumeration<String> paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements())
        {
            String key = paramNames.nextElement();
            String value = request.getParameter(key);
            result.put(key,value);
        }
        return result;
    }
    public Long id(HttpServletRequest request)
    {
        return (Long)request.getAttribute("user_id");
    }
    public User user(HttpServletRequest request)
    {
        return (User)request.getAttribute("user");
    }
    public ArrayList<String> roles(HttpServletRequest request)
    {
        return (ArrayList<String>)request.getAttribute("roles");
    }
    public Boolean hasRole(HttpServletRequest request,String role)
    {
        return ((ArrayList<String>)request.getAttribute("roles")).contains(role);
    }
    public ModelAndView flushAllParameters(HttpServletRequest request,ModelAndView model)
    {
        Enumeration<String> parNames = request.getParameterNames();
        while(parNames.hasMoreElements())
        {
            String name = parNames.nextElement();
            String value = request.getParameter(name);
            model.addObject(name,value);
        }
        return model;
    }
}

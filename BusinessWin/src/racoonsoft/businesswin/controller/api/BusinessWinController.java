package racoonsoft.businesswin.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.businesswin.service.GameService;
import racoonsoft.businesswin.structure.enums.StatusCode;
import racoonsoft.library.access.User;
import racoonsoft.library.json.JSONProcessor;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

@Controller
public class BusinessWinController
{
    @Autowired
    protected GameService gameService;

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
    public ModelAndView generateError(StatusCode code) throws Exception
    {
        HashMap<String,Object> body = new HashMap<String, Object>();
        body.put("success",false);
        body.put("status_code",code);
        JSONProcessor proc = new JSONProcessor(body);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",proc.jsonString());
        return model;
    }
}

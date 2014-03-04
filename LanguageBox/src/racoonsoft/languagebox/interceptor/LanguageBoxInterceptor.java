package racoonsoft.languagebox.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
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

@Controller
public abstract class LanguageBoxInterceptor implements HandlerInterceptor
{
    @Autowired
    private PostgresqlDataSource dbProc;

    @Override
    public abstract boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception;

    @Override
    public abstract void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception;

    @Override
    public abstract void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception;

    public void setDbProc(PostgresqlDataSource dbProc)
    {
        this.dbProc = dbProc;
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
        if(request.getAttribute("roles")==null)
        {
            return new ArrayList<String>();
        }
        return (ArrayList<String>)request.getAttribute("roles");
    }
    public Boolean hasRole(HttpServletRequest request,String role)
    {
        return ((ArrayList<String>)request.getAttribute("roles")).contains(role);
    }
    public String school(HttpServletRequest request) throws Exception
    {
        String domain = new URL(request.getRequestURL().toString()).getHost();
        return StringHelper.getDomainByLevel(domain, 3);
    }
    public ModelAndView addRoles(ModelAndView model,HttpServletRequest request) throws Exception
    {
        model.addObject("roles",roles(request));
        return model;
    }
    public ModelAndView addUser(ModelAndView model,HttpServletRequest request) throws Exception
    {
        User u = user(request);
        model.addObject("user",u);
        return model;
    }
}

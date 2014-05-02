package racoonsoft.racoonspring.mvc.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.access.User;
import racoonsoft.library.database.DBProcessor;
import racoonsoft.racoonspring.data.database.DatabaseProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public abstract class MainInterceptor implements HandlerInterceptor
{
    @Autowired
    public DatabaseProcessor dbProc;

    @Override
    public abstract boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception;

    @Override
    public abstract void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception;

    @Override
    public abstract void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception;

    public void setDbProc(DBProcessor dbProc)
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
        return (ArrayList<String>)request.getAttribute("roles");
    }
    public Boolean hasRole(HttpServletRequest request,String role)
    {
        return ((ArrayList<String>)request.getAttribute("roles")).contains(role);
    }
}

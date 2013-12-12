package racoonsoft.languagebox.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.User;
import racoonsoft.library.access.UserProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.ArrayList;

@Controller
public class AccessInterceptor extends LanguageBoxInterceptor
{
    @Autowired
    public PostgresqlDataSource dbProc;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception
    {
        checkAccess(request,response);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception
    {

    }

    public void checkAccess(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String path = request.getServletPath();
        ActionResult res = UserProcessor.authorization(request,response,dbProc);
        User user = res.getUser();
        ArrayList<String> roles = res.getRoles();
        boolean anonymous = res.anonymous();
        request.setAttribute("user_id",user.getID());
        request.setAttribute("user",user);
        request.setAttribute("roles",roles);
        request.setAttribute("school",school(request));
        request.setAttribute("roles",roles);
        if(anonymous// Anonymous
                ||!user.getStringValue("school").equalsIgnoreCase(school(request)) // Wrong school
                ||(!res.hasRole("TUTOR")&&!res.hasRole("SCHOOL")&&path.contains("/service/teacher")) // Not tutor and not school tries to access teacher`s page
                ||(!res.hasRole("STUDENT")&&path.contains("/service/student"))) // Not school tries to access school`s page
        {
            response.sendRedirect("/page/no_access");
        }
    }
}

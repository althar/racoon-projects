package racoonsoft.circos.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.circos.database.PostgresqlDataSource;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.User;
import racoonsoft.library.access.UserProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class AccessInterceptor extends CircosInterceptor
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
        ActionResult res = UserProcessor.authorization(request,response,dbProc);
        User user = res.getUser();
        ArrayList<String> roles = res.getRoles();
        boolean anonymous = res.anonymous();
        request.setAttribute("anonymous",anonymous);
        request.setAttribute("user_id",user.getID());
        request.setAttribute("user",user);
        request.setAttribute("roles",roles);
    }
}

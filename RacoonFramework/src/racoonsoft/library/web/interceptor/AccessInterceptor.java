package racoonsoft.library.web.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.User;
import racoonsoft.library.access.UserProcessor;
import racoonsoft.library.annotations.RequiresRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class AccessInterceptor extends MainInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception
    {
        checkAccess(request,response);
        HandlerMethod method = (HandlerMethod)o;
        Object controller = method.getBean();
        if(controller.getClass().isAnnotationPresent(RequiresRole.class))
        {
            RequiresRole reqRole = controller.getClass().getAnnotation(RequiresRole.class);
            String[] rolesNeeded = reqRole.role();
            boolean canAccess = false;
            for(String role : rolesNeeded)
            {
                if(hasRole(request,role))
                {
                    canAccess = true;
                }
            }
            if(!canAccess)
            {
                response.sendRedirect(reqRole.redirectUrl());
                response.flushBuffer();
                return false;
            }
        }
        Class[] methodParamsClasses = new Class[method.getMethodParameters().length];
        for(int i=0; i<methodParamsClasses.length; i++)
        {
            methodParamsClasses[i] = method.getMethodParameters()[i].getParameterType();
        }
        Boolean check = method.getBean().getClass().getMethod(method.getMethod().getName(), methodParamsClasses).isAnnotationPresent(RequiresRole.class);
        if(check)
        {
            RequiresRole reqRole = method.getBean().getClass().getMethod(method.getMethod().getName(), methodParamsClasses).getAnnotation(RequiresRole.class);
            String[] rolesNeeded = reqRole.role();
            boolean canAccess = false;
            for(String role : rolesNeeded)
            {
                if(hasRole(request,role))
                {
                    canAccess = true;
                }
            }
            if(!canAccess)
            {
                response.sendRedirect(reqRole.redirectUrl());
                response.flushBuffer();
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception
    {
        modelAndView.addObject("user",request.getAttribute("user"));
        modelAndView.addObject("anonymous",request.getAttribute("anonymous"));
        modelAndView.addObject("user_id",request.getAttribute("user_id"));
        modelAndView.addObject("roles",request.getAttribute("roles"));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception
    {

    }

    public void checkAccess(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionResult res = UserProcessor.authorization(request, response, dbProc);
        User user = res.getUser();
        ArrayList<String> roles = res.getRoles();
        boolean anonymous = res.anonymous();
        request.setAttribute("anonymous",anonymous);
        request.setAttribute("user_id",user.getID());
        request.setAttribute("user",user);
        request.setAttribute("roles",roles);
    }
}

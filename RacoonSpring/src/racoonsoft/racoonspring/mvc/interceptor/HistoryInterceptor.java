package racoonsoft.racoonspring.mvc.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.database.DBProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HistoryInterceptor extends MainInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println("Post");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
//        System.out.println("after");
    }

    public void setDbProc(DBProcessor dbProc)
    {
        this.dbProc = dbProc;
    }
}

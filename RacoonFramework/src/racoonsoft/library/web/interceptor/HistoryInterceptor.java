package racoonsoft.library.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.UserProcessor;
import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.database.DBRecord;

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

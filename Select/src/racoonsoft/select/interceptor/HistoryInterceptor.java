package racoonsoft.select.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.UserProcessor;
import racoonsoft.library.web.WebHelper;
import racoonsoft.select.database.PGSQLDataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HistoryInterceptor implements HandlerInterceptor
{
    @Autowired
    private PGSQLDataSource dbProc;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        ActionResult res = UserProcessor.authorization(request, response, dbProc, true, true);
        Long userId = res.getUser().getID();
        String trackingId = WebHelper.getCookie(request,"tracking_id");
        if(trackingId==null)
        {
            trackingId = dbProc.generateTrackingId();
            WebHelper.setCookie(response,"tracking_id",trackingId,86400000);
        }
        String page = request.getRequestURL().toString();
        String action = "Посещение страницы";
        String description = "";
        if(page.contains("good_details"))
        {
            action = "Просмотр товара";
            description = request.getParameter("good_id");
        }
        if(page.contains("access/registration"))
        {
            action = "Регистрация";
        }
        if(page.contains("access/authorization"))
        {
            action = "Авторизация";
        }
        String referer = request.getHeader("referer");
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        dbProc.history(userId,page,action,description,referer,ip,userAgent,trackingId);
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

    public void setDbProc(PGSQLDataSource dbProc)
    {
        this.dbProc = dbProc;
    }
}

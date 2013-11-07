package racoonsoft.select.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.database.DBRecord;
import racoonsoft.select.database.PGSQLDataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataInterceptor implements HandlerInterceptor
{
    @Autowired
    private PGSQLDataSource dbProc;

    private DBRecord getStatic()
    {
        DBRecord stat = new DBRecord();
        stat.setValue("phone","8(499)390 73 51");
        stat.setValue("phone_free","8(499)390 73 51");
        return stat;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception
    {
        if(modelAndView!=null)
        {
            modelAndView.addObject("static_data",getStatic());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
    public void setDbProc(PGSQLDataSource dbProc)
    {
        this.dbProc = dbProc;
    }
}

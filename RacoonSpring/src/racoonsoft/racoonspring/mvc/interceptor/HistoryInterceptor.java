package racoonsoft.racoonspring.mvc.interceptor;

import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.racoonspring.data.annotation.DataStructureTable;
import racoonsoft.racoonspring.data.database.DatabaseProcessor;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Enumeration;

@Controller
public class HistoryInterceptor extends MainInterceptor
{
    protected Class<?> visitStructureClass;
    private String table_name = null;
    public HistoryInterceptor(Class<? extends DatabaseStructure> visitStructure)
    {
        visitStructureClass = visitStructure;
        DataStructureTable table_name_ann = visitStructureClass.getAnnotation(DataStructureTable.class);
        if(table_name_ann!=null)
        {
            table_name = table_name_ann.name();
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception
    {
        String ip = request.getRemoteAddr();
        DatabaseStructure str = new DatabaseStructure();
        boolean needTrackingId = true;
        String trackingId = null;
        trackingId = getCookie(request,"tracking_id");
        if(trackingId==null)
        {
            trackingId = dbProc.selectQuery("SELECT nextval('"+table_name+"_id_seq') AS tracking_id").selectOne().getStringValue("tracking_id");
            setCookie(response,"tracking_id",trackingId,860000000);
        }
        Field[] fs = visitStructureClass.getDeclaredFields();
        for(Field f:fs)
        {
            f.setAccessible(true);
            String name = f.getName();
            if(name.equalsIgnoreCase("ip"))
            {
                str.set(name,ip);
                continue;
            }
            if(name.equalsIgnoreCase("tracking_id"))
            {
                str.set(name,trackingId);
                continue;
            }
            String value = getHeader(name,request);

            if(value == null || value.equalsIgnoreCase(""))
            {
                value = request.getParameter(name);
            }
            if(value == null)
            {
                value = getCookie(request,name);
            }
            str.set(name,value);
        }

        dbProc.insertQuery(str,table_name).insert();
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

    public void setDbProc(DatabaseProcessor dbProc)
    {
        this.dbProc = dbProc;
    }
}

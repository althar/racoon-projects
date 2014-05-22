package racoonsoft.racoonspring.mvc.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.racoonspring.access.AccessProcessor;
import racoonsoft.racoonspring.data.annotation.DataStructureField;
import racoonsoft.racoonspring.data.annotation.DataStructureTable;
import racoonsoft.racoonspring.data.database.DatabaseProcessor;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@Controller
public class HistoryInterceptor extends MainInterceptor
{
    protected Class<?> visitStructureClass;
    private String table_name = null;
    public HistoryInterceptor(Class<? extends DatabaseStructure> visitStructure,DatabaseProcessor dbProc, AccessProcessor userProc)
    {
        super(dbProc,userProc);
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
        Constructor<DatabaseStructure> ctor = (Constructor<DatabaseStructure>)visitStructureClass.getConstructor();
        DatabaseStructure str = ctor.newInstance(new Object[] {});

        String ip = request.getRemoteAddr();
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
            String nameByAnnotation = "";
            if(f.isAnnotationPresent(DataStructureField.class))
            {
                nameByAnnotation = f.getAnnotation(DataStructureField.class).name();
            }

            if(name.equalsIgnoreCase("ip")||nameByAnnotation.equalsIgnoreCase("ip"))
            {
                str.set(name,ip);
                continue;
            }
            if(name.equalsIgnoreCase("url")||nameByAnnotation.equalsIgnoreCase("url"))
            {
                str.set(name,request.getRequestURL().toString());
                continue;
            }
            if(name.equalsIgnoreCase("page")||nameByAnnotation.equalsIgnoreCase("page"))
            {
                str.set(name,request.getServletPath());
                continue;
            }
            if(name.equalsIgnoreCase("tracking_id")||nameByAnnotation.equalsIgnoreCase("tracking_id"))
            {
                str.set(name,trackingId);
                continue;
            }

            // From headers
            String value = getHeader(name,request);
            if(value == null)
            {
                value = getHeader(nameByAnnotation,request);
            }

            // From parameters
            if(value == null || value.equalsIgnoreCase(""))
            {
                value = getParameter(name,request);
            }
            if(value == null)
            {
                value = getParameter(nameByAnnotation,request);
            }

            // From cookie
            if(value == null)
            {
                value = getCookie(request,name);
            }
            if(value == null)
            {
                value = getCookie(request,nameByAnnotation);
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

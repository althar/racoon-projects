package racoonsoft.languagebox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.database.Expression;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CourseService extends LanguageBoxService
{
    public ArrayList<DBRecord> getTeacherCourses(Long user_id) throws Exception
    {
        return dbProc.getRecords("SELECT * FROM course WHERE user_id="+user_id);
    }
    public ArrayList<DBRecord> getBoughtCourses(Long user_id) throws Exception
    {
        return dbProc.getRecords("SELECT c.*,cp.status AS purchase_status,cp.created AS purchase_created, cp.seller_fee_transaction_id FROM course_purchase cp, course c " +
                " WHERE (cp.status='APPROVED' OR cp.status='NEW') AND cp.course_id = c.id AND cp.user_id="+user_id);
    }

    public Long createCourse(Long userId, String name,String level, Boolean isPublic,Double price, String description,String target,String type) throws Exception
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("user_id",userId);
        pars.put("name",name);
        pars.put("level",level);
        pars.put("public",isPublic);
        pars.put("price",price);
        pars.put("description",description);
        pars.put("target",target);
        pars.put("type",type);
        Long id = dbProc.executeInsert("course",pars);
        addLesson(id,"MAIN_MATERIAL","","Основные материалы курса",true);
        return id;
    }
    public Integer updateCourse(Long id, Long userId, String name, String level, Boolean isPublic, Double price, String description, String target, String type) throws Exception
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("user_id",userId);
        pars.put("name",name);
        pars.put("level",level);
        pars.put("public",isPublic);
        pars.put("price",price);
        pars.put("description",description);
        pars.put("target",target);
        pars.put("type",type);
        return dbProc.executeUpdate("course", pars, "id=" + id.toString());
    }
    public Long addLesson(Long courseId,String type,String task,String description,Boolean trial) throws Exception
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("course_id",courseId);
        pars.put("type",type);
        pars.put("task",task);
        pars.put("index",new Expression("SELECT count(id)+1 FROM lesson WHERE course_id="+courseId));
        pars.put("description",description);
        pars.put("trial",trial);
        return dbProc.executeInsert("lesson",pars);
    }
}

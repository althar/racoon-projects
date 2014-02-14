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
    public DBRecord getCourse(Long user_id, Long course_id,Boolean with_lessons) throws Exception
    {
        DBRecord course = dbProc.getRecord("SELECT * FROM course WHERE user_id="+user_id+" AND id="+course_id);
        if(with_lessons)
        {
            ArrayList<DBRecord> lessons = dbProc.getRecords("SELECT ls.id,ls.name,ls.type,ls.index,ls.trial,count(lm.id) AS material_count FROM lesson ls,lesson_material lm WHERE ls.id=lm.lesson_id AND ls.type='LESSON' AND course_id="+course_id+" GROUP BY ls.id,ls.name,ls.type,ls.index,ls.trial");
            course.setValue("lessons",lessons);
            DBRecord mainMaterial = dbProc.getRecord("SELECT ls.id,ls.name,ls.type,ls.index,ls.trial,count(lm.id) AS material_count FROM lesson ls,lesson_material lm WHERE ls.id=lm.lesson_id AND ls.type='MAIN_MATERIAL' AND course_id="+course_id+" GROUP BY ls.id,ls.name,ls.type,ls.index,ls.trial LIMIT 1");
            course.setValue("main_material",mainMaterial);
        }
        return course;
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
        createLesson(userId,id,"Основные материалы","MAIN_MATERIAL","","Основные материалы курса",true);
        return id;
    }
    public Long updateCourse(Long id, Long userId, String name, String level, Boolean isPublic, Double price, String description, String target, String type) throws Exception
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
        return dbProc.executeUpdate("course", pars, "id=" + id.toString()+" AND user_id="+userId);
    }
    public Long createLesson(Long user_id, Long courseId,String name, String type,String task,String description,Boolean trial) throws Exception
    {
        DBRecord c = dbProc.getRecord("SELECT id FROM course WHERE user_id="+user_id+" AND id="+courseId);
        if(c!=null)
        {
            HashMap<String,Object> pars = new HashMap<String, Object>();
            pars.put("course_id",courseId);
            pars.put("name",name);
            pars.put("type",type);
            pars.put("task",task);
            pars.put("index",new Expression("SELECT count(id)+1 FROM lesson WHERE course_id="+courseId));
            pars.put("description",description);
            pars.put("trial",trial);
            return dbProc.executeInsert("lesson",pars);
        }
        return 0l;
    }
    public Long updateLesson(Long user_id, Long courseId, Long lesson_id, String name, String type,String task,String description,Boolean trial) throws Exception
    {
        DBRecord c = dbProc.getRecord("SELECT id FROM course WHERE user_id="+user_id+" AND id="+courseId);
        if(c!=null)
        {
            HashMap<String,Object> pars = new HashMap<String, Object>();
            pars.put("course_id",courseId);
            pars.put("name",name);
            pars.put("type",type);
            pars.put("task",task);
            pars.put("index",new Expression("SELECT count(id)+1 FROM lesson WHERE course_id="+courseId));
            pars.put("description",description);
            pars.put("trial",trial);
            if(lesson_id!=null)
            {
                return dbProc.executeUpdate("lesson",pars,"course_id="+courseId+" AND id="+lesson_id);
            }
            else
            {
                return dbProc.executeUpdate("lesson",pars,"course_id="+courseId+" AND type='MAIN_MATERIAL'");
            }
        }
        return 0l;
    }
    public DBRecord getLesson(Long user_id,Long course_id, Long lesson_id) throws Exception
    {
        if(lesson_id==null)
        {
            return null;
        }
        DBRecord lesson = dbProc.getRecord("SELECT * FROM lesson WHERE course_id IN (SELECT id FROM course WHERE user_id="+user_id+") AND lesson_id=");
        if(lesson!=null)
        {
            ArrayList<DBRecord> materials = dbProc.getRecords("SELECT m.* FROM lesson_material lm, material m WHERE lm.material_id=m.id AND lesson_id="+lesson_id);
            lesson.setValue("materials",materials);
        }
        if(course_id!=null)
        {
            DBRecord course = dbProc.getRecord("SELECT * FROM course WHERE id="+course_id+" AND user_id="+user_id);
            lesson.setValue("course",course);
        }
        return lesson;
    }

}

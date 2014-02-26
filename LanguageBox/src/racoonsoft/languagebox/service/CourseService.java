package racoonsoft.languagebox.service;

import org.springframework.stereotype.Service;
import racoonsoft.languagebox.structure.Lesson;
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
        DBRecord course = dbProc.getRecord("SELECT *,(SELECT id FROM lesson WHERE course_id="+course_id+" AND type='MAIN_MATERIAL' LIMIT 1) AS main_material_id FROM course WHERE user_id="+user_id+" AND id="+course_id);
        if(with_lessons)
        {
            ArrayList<DBRecord> lessons = dbProc.getRecords("SELECT ls.id,ls.name,ls.type,ls.index,ls.trial,count(lm.id) AS material_count FROM lesson ls LEFT JOIN lesson_material lm ON ls.id=lm.lesson_id WHERE ls.type='LESSON' AND course_id="+course_id+" GROUP BY ls.id,ls.name,ls.type,ls.index,ls.trial ORDER BY ls.id");
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
        saveLesson(userId, id,null, "Основные материалы", "MAIN_MATERIAL", "", "Основные материалы курса", true,new Long[0]);
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
        dbProc.executeUpdate("course", pars, "id=" + id.toString()+" AND user_id="+userId);
        return id;
    }
    public Long saveLesson(Long userId, Long courseId, Long lessonId, String name, String type, String task, String description, Boolean trial,Long[] material) throws Exception
    {
        DBRecord c = dbProc.getRecord("SELECT id FROM course WHERE user_id="+userId+" AND id="+courseId);
        if(c!=null)
        {
            HashMap<String,Object> pars = new HashMap<String, Object>();
            pars.put("course_id",courseId);
            pars.put("name",name);
            pars.put("type",type);
            pars.put("task",task);
            pars.put("index",new Expression("(SELECT COALESCE(count(id)+1,1) FROM lesson WHERE course_id="+courseId+")"));
            pars.put("description",description);
            pars.put("trial",trial);
            if(lessonId==null)
            {
                return dbProc.executeInsert("lesson",pars);
            }
            else
            {
                Long return_id = dbProc.executeUpdate("lesson", pars, "id=" + lessonId);
                dbProc.executeDelete("lesson_material","lesson_id="+lessonId);
                for(int i=0; i<material.length; i++)
                {
                    HashMap<String,Object> ps = new HashMap<String, Object>();
                    ps.put("lesson_id",lessonId);
                    ps.put("material_id",material[i]);
                    dbProc.executeInsert("lesson_material",ps);
                }
                return return_id;
            }
        }
        return 0l;
    }
    public Long saveLesson(Long userId,Lesson lesson) throws Exception
    {
        return saveLesson(userId,lesson.getCourseId(),lesson.getId(),lesson.getName(),lesson.getType(),lesson.getTask(),lesson.getDescription(),lesson.getTrial(),lesson.getMaterial());
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
        DBRecord lesson = dbProc.getRecord("SELECT * FROM lesson WHERE course_id IN (SELECT id FROM course WHERE user_id="+user_id+") AND id="+lesson_id);
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

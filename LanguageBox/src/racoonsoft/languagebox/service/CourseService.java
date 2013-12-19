package racoonsoft.languagebox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.library.database.DBRecord;

import java.util.ArrayList;

@Service
public class CourseService
{
    @Autowired
    private PostgresqlDataSource dbProc;

    public ArrayList<DBRecord> getTeacherCourses(Long user_id) throws Exception
    {
        return dbProc.getRecords("SELECT * FROM course WHERE user_id="+user_id);
    }
    public ArrayList<DBRecord> getBoughtCourses(Long user_id) throws Exception
    {
        return dbProc.getRecords("SELECT c.*,cp.status AS purchase_status,cp.created AS purchase_created,cp.payment_transaction FROM course_purchase cp, course c " +
                " WHERE c.id=cp.course_id AND cp.user_id="+user_id);
    }
}

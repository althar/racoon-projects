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
}

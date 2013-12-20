package racoonsoft.languagebox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.library.database.DBRecord;

import java.util.ArrayList;

@Service
public class StudentService
{
    @Autowired
    private PostgresqlDataSource dbProc;

    public ArrayList<DBRecord> getTeacherStudents(Long user_id) throws Exception
    {
        return dbProc.getRecords("SELECT * FROM \"user\" WHERE user_id="+user_id);
    }
    public ArrayList<DBRecord> getStudentAchievements(Long user_id) throws Exception
    {
        return dbProc.getRecords("SELECT a.* FROM achievement a, user_achievement ua WHERE a.id=ua.achievement_id AND ua.user_id="+user_id);
    }
}

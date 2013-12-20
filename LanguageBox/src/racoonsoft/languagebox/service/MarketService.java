package racoonsoft.languagebox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.library.database.DBRecord;

import java.util.ArrayList;

@Service
public class MarketService
{
    @Autowired
    private PostgresqlDataSource dbProc;

    public ArrayList<DBRecord> getSells(Long user_id) throws Exception
    {
        return dbProc.getRecords("SELECT * FROM transaction WHERE type='TEACHER_FEE' AND status='APPROVED' AND user_id="+user_id);
    }
}

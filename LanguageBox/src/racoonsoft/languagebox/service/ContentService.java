package racoonsoft.languagebox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.library.database.DBRecord;

import java.util.ArrayList;

@Service
public class ContentService
{
    @Autowired
    private PostgresqlDataSource dbProc;

    public ArrayList<DBRecord> getNews() throws Exception
    {
        return dbProc.getRecords("SELECT * FROM content WHERE type='NEWS'");
    }
}

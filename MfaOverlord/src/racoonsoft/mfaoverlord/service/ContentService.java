package racoonsoft.mfaoverlord.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.racoonspring.data.database.DatabaseProcessor;

@Service
public class ContentService
{
    private ContentProcessor proc = new ContentProcessor("content");
    @Autowired
    private DatabaseProcessor dbProc;

    public void start() throws Exception
    {
        proc.dbProc = dbProc;
        proc.start();
    }
}
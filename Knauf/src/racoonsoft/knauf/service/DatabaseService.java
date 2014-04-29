package racoonsoft.knauf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.library.database.DBProcessor;

@Service
public class DatabaseService
{
    @Autowired
    private DBProcessor dbProc;

    public void transaction(String type,String status,Long user_id, Double amount)
    {

    }
}

package racoonsoft.knauf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.library.database.DBProcessor;

import java.util.HashMap;

@Service
public class DatabaseService
{
    @Autowired
    private DBProcessor dbProc;

    public void transaction(String type,String status,Long user_id, Double amount) throws Exception
    {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("type",type);
        map.put("status",status);
        map.put("user_id",user_id);
        map.put("amount",amount);
        dbProc.executeInsert("transaction",map);
    }
}

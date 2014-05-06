package racoonsoft.mfaoverlord.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.racoonspring.data.database.DatabaseProcessor;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;
import racoonsoft.racoonspring.helper.StringHelper;
import racoonsoft.racoonspring.http.HTTPClient;
import racoonsoft.racoonspring.service.SeparateThreadService;

import java.util.ArrayList;

@Service
public class SearchService
{
    private SearchProcessor proc = new SearchProcessor("search");
    @Autowired
    private DatabaseProcessor dbProc;

    public void start() throws Exception
    {
        proc.dbProc = dbProc;
        proc.start();
    }
}
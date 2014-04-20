package racoonsoft.knauf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.ozon.OzonProcessor;

@Service
public class OzonService
{
    @Autowired
    private OzonProcessor ozonProc;

    public JSONProcessor catalogueStructure() throws Exception
    {
        JSONProcessor json = ozonProc.getCatalogueStructure();
        return json;
    }
}

package racoonsoft.knauf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.ozon.OzonProcessor;

@Service
public class OzonService
{
    @Autowired
    public OzonProcessor ozonProc;

    public JSONProcessor catalogue() throws Exception
    {
        JSONProcessor json = ozonProc.contextInfoGet();
        return json;
    }
    public JSONProcessor subCatalogue(String catalogue) throws Exception
    {
        JSONProcessor json = ozonProc.getSubCatalogue(catalogue);
        return json;
    }
    public JSONProcessor getItems(String catalogue_id,String catalogue_name) throws Exception
    {
        if(catalogue_id==null)
        {
            JSONProcessor json = ozonProc.getCatalogueItemsByName(catalogue_name,"","200","1");
            return json;
        }
        JSONProcessor json = ozonProc.getCatalogueItems(catalogue_id,"","200","1");
        return json;
    }

}

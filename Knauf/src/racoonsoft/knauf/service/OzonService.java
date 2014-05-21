package racoonsoft.knauf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.library.cache.Cache;
import racoonsoft.library.cache.CacheProcessor;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.ozon.OzonProcessor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class OzonService
{
    @Autowired
    public OzonProcessor ozonProc;

    private CacheProcessor<String,JSONProcessor> cache = new CacheProcessor<String, JSONProcessor>();

    public JSONProcessor catalogue() throws Exception
    {
        JSONProcessor json = cache.get("catalogue");
        if(json==null)
        {
            json = ozonProc.contextInfoGet();
            cache.put("catalogue",json,3600000*24);
        }
        return json;
    }
    public JSONProcessor catalogueFacet() throws Exception
    {
        JSONProcessor json = cache.get("catalogue");
        if(json==null)
        {
            json = ozonProc.getStieSections();
            cache.put("catalogue",json,3600000*24);
        }
        return json;
    }
    public JSONProcessor subCatalogue(String catalogue) throws Exception
    {
        JSONProcessor json = cache.get("subCatalogue."+catalogue);
        if(json==null)
        {
            json = ozonProc.getSubCatalogue(catalogue);
            cache.put("subCatalogue."+catalogue,json,3600000*24);
        }
        return json;
    }
    public JSONProcessor getItems(String catalogue_id,String catalogue_name,Integer page,String search) throws Exception
    {

        JSONProcessor json = cache.get("getItems."+catalogue_id+"."+catalogue_name+"."+page.toString()+"."+search);
        if(json==null)
        {
            if(search!=null&&!search.equalsIgnoreCase(""))
            {
                json = ozonProc.searchItemsGet(search, "istPrice", "40", page.toString());
            }
            else if(catalogue_id==null||catalogue_id.equalsIgnoreCase(""))
            {
                json = ozonProc.getSectionSearchResult(catalogue_name,"40",page.toString(),"0-100000","");
                //json = ozonProc.getCatalogueItemsByName(catalogue_name, "istPrice", "40", page.toString());
            }
            else
            {
                json = ozonProc.getSectionSearchResult(catalogue_name,"40",page.toString(),"0-100000",catalogue_id);
//                json = ozonProc.getCatalogueItems(catalogue_id, "istPrice", "40", page.toString());
            }
            cache.put("getItems."+catalogue_id+"."+catalogue_name+"."+page.toString()+"."+search,json,3600000*24);
        }
        return json;
    }
    public void removeGood(String good_id,String user_id) throws Exception
    {
        ozonProc.cartRemove(user_id,good_id);
    }
    public JSONProcessor addGood(String good_id,String user_id,Integer count) throws Exception
    {
        JSONProcessor json = ozonProc.cartAdd(user_id,good_id+":"+count.toString());
        return json;
    }

    public static String formatPrice(String rub)
    {
        try
        {
        Double result = Double.valueOf(rub)/5.0;
        String str = new DecimalFormat("#.#").format(result);
        return str;
        }
        catch(Exception ex)
        {
            return "0.0";
        }
    }
}

package racoonsoft.mfaoverlord.service;
import racoonsoft.mfaoverlord.structure.StolenArticle;
import racoonsoft.racoonspring.data.database.DatabaseProcessor;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;
import racoonsoft.racoonspring.helper.Helper;
import racoonsoft.racoonspring.http.HTTPClient;
import racoonsoft.racoonspring.service.SeparateThreadService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ContentProcessor extends SeparateThreadService
{
    public DatabaseProcessor dbProc;

    public ContentProcessor(String name)
    {
        super(name);
    }

    @Override
    public void process() throws Exception
    {
        try
        {
            // 1. Select search_items
            ArrayList<DatabaseStructure> search_items = dbProc.selectQuery("SELECT * FROM search_item WHERE extracted=FALSE").select();

            // 2. Create articles
            for(DatabaseStructure ds:search_items)
            {
                String url = ds.getStringValue("url");
                String domain = ds.getStringValue("domain");
                Long search_item_id = ds.getLongValue("id");
                Long search_id = ds.getLongValue("search_id");
                Long semantics_id = ds.getLongValue("semantics_id");
                String text = null;
                try
                {
                    text = HTTPClient.sendHTTPRequest("",url);
                    if(text.toLowerCase().contains("windows-1251"))
                    {
                        HTTPClient.default_encoding = "windows-1251";
                        text = HTTPClient.sendHTTPRequest("",url);
                    }
                }
                catch (Exception exx)
                {

                }

                HTTPClient.default_encoding = "UTF-8";
//                System.out.println(" ========================================================= ");
//                System.out.println(text);
//                System.out.println(" ========================================================= ");
                searchForArticles(url, search_item_id, domain,semantics_id,text);
            }
            Thread.sleep(20000);
        }
        catch (Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
            Thread.sleep(20000);
        }
    }

    private void searchForArticles(String url, Long search_item_id,String domain,Long semantics_id,String text) throws Exception
    {
        try
        {
            String word = dbProc.selectQuery("SELECT word FROM semantics WHERE id="+semantics_id).selectOne().getStringValue("word");
            HashMap<String,Object> pars = new HashMap<String, Object>();
            pars.put("extracted",true);
            dbProc.executeUpdate("search_item",pars,"id="+search_item_id);
            ArrayList<StolenArticle> articles = TextService.getArticles(text,domain,word);
            for(StolenArticle article:articles)
            {
                article.search_item_id = search_item_id;
                article.domain = domain;
                article.url = url;
                article.semantics_id = semantics_id;
                dbProc.insertQuery(article).insert();
            }

        }
        catch (Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
        }
    }
}
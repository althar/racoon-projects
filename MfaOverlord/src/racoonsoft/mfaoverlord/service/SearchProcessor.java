package racoonsoft.mfaoverlord.service;
import org.springframework.beans.factory.annotation.Autowired;
import racoonsoft.racoonspring.data.database.DatabaseProcessor;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;
import racoonsoft.racoonspring.helper.Helper;
import racoonsoft.racoonspring.helper.StringHelper;
import racoonsoft.racoonspring.http.HTTPClient;
import racoonsoft.racoonspring.service.SeparateThreadService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SearchProcessor extends SeparateThreadService
{
    public DatabaseProcessor dbProc;

    public SearchProcessor(String name)
    {
        super(name);
    }

    @Override
    public void process() throws Exception
    {
        try
        {
            // 1. Select semantics
            ArrayList<DatabaseStructure> semantics = dbProc.selectQuery("SELECT * FROM semantics WHERE done = FALSE LIMIT 1").select();
            if(semantics==null||semantics.size()==0)
            {
                dbProc.executeNonQuery("UPDATE semantics SET done = FALSE");
            }
            // 2. Create search for each semantics
            for(DatabaseStructure ds:semantics)
            {
                String word = ds.getStringValue("word");
                String domain = ds.getStringValue("domain");
                Long id = ds.getLongValue("id");
                try
                {
                    searchForWord(word,id);
                }
                catch (Exception exx)
                {
                    System.out.println(Helper.getStackTraceString(exx));
                }
                dbProc.executeNonQuery("UPDATE semantics SET done=TRUE WHERE id="+id);
            }
            // 20 times a day...
            Thread.sleep(4320000);
        }
        catch (Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
            Thread.sleep(20000);
        }
        finally
        {
            // 1 day of waiting
        }
    }

    private void searchForWord(String word, Long semantics_id) throws Exception
    {

        // 1. Google
        Long id = dbProc.insertQuery(new DatabaseStructure().set("semantics_id",semantics_id).set("search_engine","google"),"search").insert();
        googleResultUrls(word,10,id,semantics_id);

        // 2. Yandex
        //dbProc.insertQuery(new DatabaseStructure().set("semantics_id",semantics_id).set("search_engine","yandex"),"search").insert();

    }

    private void googleResultUrls(String word, int depth,long search_id,Long semantics_id) throws Exception
    {
        for(int i=0; i<depth; i++)
        {
            HashMap<String,String> pars = new HashMap<String, String>();
            //pars.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36;");
            pars.put("User-Agent",Tricker.nextUserAgent());

            String result = HTTPClient.sendHTTPSRequestWithHeaders("https://www.google.ru/search?q=" + word.replace(" ","") + "&start=" + 10 * i, "GET",pars);
            ArrayList<String> hrefs = TextService.getGoogleResult(result);
            int pos=0;
            for(String hr:hrefs)
            {
                pos++;
                try
                {
                    DatabaseStructure href = dbProc.selectQuery("SELECT id FROM search_item WHERE url='"+hr+"'").selectOne();
                    if(href==null)
                    {
                        String domain = TextService.getDomain(hr);
                        String hrText = hr.split("\\|")[0];
                        String titleText = word;
                        if(hr.split("\\|").length>1)
                        {
                            titleText = hr.split("\\|")[1];
                        }
                        dbProc.insertQuery("INSERT INTO search_item (search_id,url,position,extracted,semantics_id,domain,title) VALUES (?,?,?,?,?,?,?) RETURNING id")
                            .setParameter(1,search_id)
                            .setParameter(2,hrText)
                            .setParameter(3,pos+i*10)
                            .setParameter(4,false)
                            .setParameter(5,semantics_id)
                            .setParameter(6,domain)
                            .setParameter(7,titleText).insert();
                    }
                }
                catch (Exception ex)
                {
                    System.out.println(Helper.getStackTraceString(ex));
                }
            }
            Thread.sleep(1000+new Random().nextInt(1000));
        }
    }
}

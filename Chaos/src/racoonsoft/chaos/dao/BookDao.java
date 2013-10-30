package racoonsoft.chaos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import racoonsoft.chaos.entity.Chapter;
import racoonsoft.chaos.entity.Fragment;
import racoonsoft.chaos.magic.TextWizard;
import racoonsoft.chaos.settings.PGSQLDataSource;
import racoonsoft.library.database.DBRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Repository
public class BookDao
{
    public static String RELATION_TARGET_FRAGMENT = "FRAGMENT";
    public static String RELATION_TARGET_CHAPTER = "CHAPTER";


    @Autowired
    private PGSQLDataSource dataSource;

    public Long saveBook(Long id,String name,String type,String genre,String author) throws SQLException
    {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("type", type);
        params.put("genre", genre);
        params.put("author", author);
        if(id == null||id==0)
        {
            return dataSource.executeInsert("book", params);

        }
        else
        {
            dataSource.executeUpdate("book", params, "id=" + id);
            return id;
        }
    }
    public ArrayList<DBRecord> getBooks() throws SQLException
    {
        return dataSource.getRecords("SELECT b.* FROM book b");
    }
    public Long saveChapter(Long book_id,String chapter_name,String keywords,String text,String book_name) throws SQLException
    {
        //1. Save chapter with relational keywords -----------------------------------------
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("book_id",book_id);
        params.put("name",chapter_name);
        params.put("book_name",book_name);
        Long chapter_id = dataSource.executeInsert("chapter",params);

        //2. Split chapter for fragments and analyze them. Save Chapter --------------------
        Chapter chapter = new Chapter(text,chapter_name,book_id,chapter_id,book_name);
        chapter.process();
        HashMap<String,Integer> rels = chapter.getTopRelations();
        Iterator<String> keys = rels.keySet().iterator();
        while(keys.hasNext())
        {
            String name = keys.next();
            Integer value = rels.get(name);
            saveRelation(book_id,chapter_id,null,RELATION_TARGET_CHAPTER,name,value);
        }

        //4. Insert fragments and relations -------------------------------------------------
        for(int i=0; i<chapter.getFragments().size(); i++)
        {
            Fragment f = chapter.getFragments().get(i);
            Long fragment_id = saveFragment(f);
            HashMap<String,Integer> relss = f.getTopRelations();
            Iterator<String> keyss = relss.keySet().iterator();
            while(keyss.hasNext())
            {
                String name = keyss.next();
                Integer value = relss.get(name);
                saveRelation(book_id,chapter_id,fragment_id,RELATION_TARGET_FRAGMENT,name,value);
            }
        }

        //5. Update chapter with keywords ---------------------------------------------------
        int keyword_relation_value = TextWizard.ANALYZER_FRAGMENT_SIZE_SENTENCES/6;
        String[] keywords_arr = keywords.trim().toLowerCase().split("\\,");
        for(int i=0; i<keywords_arr.length; i++)
        {
            saveRelation(book_id,chapter_id,null,RELATION_TARGET_CHAPTER,keywords_arr[i].trim(),keyword_relation_value);
        }

        return chapter_id;
    }
    public String[] getKeywordsByChapterId(Long chapter_id) throws SQLException
    {
        ArrayList<DBRecord> recs = dataSource.getRecords("SELECT keyword,value FROM relation WHERE relation_target='CHAPTER' AND chapter_id="+chapter_id+" ORDER BY VALUE LIMIT 4");
        String[] result = new String[recs.size()];
        for(int i=0; i<result.length; i++)
        {
            result[i] = recs.get(i).getStringValue("keyword");
        }
        return result;
    }
    public Long saveImage(String unescaped_url
            ,String content_no_formatting
            ,String tb_url
            ,String keyword
            ,Integer height
            ,Integer width
            ,String title_no_formatting) throws SQLException
    {
        HashMap<String,Object> pars = new HashMap<String, Object>();
        pars.put("unescaped_url",unescaped_url);
        pars.put("tb_url",tb_url);
        pars.put("keyword",keyword);
        pars.put("height",height);
        pars.put("width",width);
        pars.put("title_no_formatting",title_no_formatting);
        pars.put("content_no_formatting",content_no_formatting);
        return dataSource.executeInsert("image",pars);
    }

    private Long saveFragment(Fragment f) throws SQLException
    {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("chapter_id",f.getChapterId());
        params.put("text",f.getText());
        Long id = dataSource.executeInsert("fragment",params);
        System.out.println("Saved fragment: "+id);
        return id;
    }
    private Long saveRelation(Long book_id,Long chapter_id,Long fragment_id,String relation_target,String keyword,int value) throws SQLException
    {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("book_id",book_id);
        params.put("chapter_id",chapter_id);
        params.put("fragment_id",fragment_id);
        params.put("relation_target",relation_target);
        params.put("keyword",keyword);
        params.put("value",value);
        Long id = dataSource.executeInsert("relation",params);
        System.out.println("Saved relation: "+id);
        return id;
    }
}

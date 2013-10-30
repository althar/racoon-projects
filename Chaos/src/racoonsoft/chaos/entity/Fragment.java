package racoonsoft.chaos.entity;

import racoonsoft.chaos.magic.TextWizard;

import javax.persistence.Entity;
import java.util.HashMap;

@Entity
public class Fragment
{
    private String source;
    private Long chapter_id;
    private Long book_id;
    private String author;
    private String book_name;
    private String chapter_name;
    private HashMap<String,Integer> relations = new HashMap<String, Integer>();
    public Fragment(String source,Long chapter_id)
    {
        this.source = source.trim();
        this.chapter_id = chapter_id;
    }
    public Fragment(String source,Long chapter_id,Long book_id,String author,String book_name,String chapter_name)
    {
        this.source = source.trim();
        this.chapter_id = chapter_id;
        this.book_id = book_id;
        this.author = author;
        this.book_name = book_name;
        this.chapter_name = chapter_name;
    }
    public void analyze()
    {
        String[] words = source.split("\\s");
        for(int i=0; i<words.length; i++)
        {
            words[i] = TextWizard.purifyWord(words[i]);
            if(words[i].length()>1&&!TextWizard.isWordPreposition(words[i]))
            {
                if(!relations.containsKey(words[i]))
                {
                    relations.put(words[i],0);
                }
                relations.put(words[i],relations.get(words[i])+1);
            }
        }
    }
    public HashMap<String,Integer> getRelations()
    {
        return relations;
    }

    public Long getChapterId()
    {
        return chapter_id;
    }
    public HashMap<String,Integer> getTopRelations()
    {
        return TextWizard.getBestRelations(relations,TextWizard.ANALYZER_FRAGMENT_TOP_RELATION_COUNT);
    }
    public String getText()
    {
        return source;
    }

}

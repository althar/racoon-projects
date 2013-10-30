package racoonsoft.chaos.entity;
import racoonsoft.chaos.magic.TextWizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Chapter
{
    private String source;
    private Long id;
    private ArrayList<String> keywords = new ArrayList<String>();
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private HashMap<String,Integer> relations = new HashMap<String, Integer>();

    private String chapter_name;

    private Long book_id;
    private String book_name;

    public Chapter(String source,String name,Long book_id,Long id,String book_name)
    {
        this.source = source;
        chapter_name = name;
        this.book_id = book_id;
        this.id = id;
        this.book_name = book_name;
    }
    public void process()
    {
        String[] sentences = source.split("\\.");
        int j = 0;
        StringBuilder fragment_builder = new StringBuilder();
        for(int i=0; i<sentences.length; i++)
        {
            fragment_builder.append(sentences[i]);
            fragment_builder.append(".");
            j++;
            if(j>TextWizard.ANALYZER_FRAGMENT_SIZE_SENTENCES)
            {
                Fragment f = new Fragment(fragment_builder.toString(),id);
                f.analyze();
                fragments.add(f);
                j = 0;
                fragment_builder = new StringBuilder();
            }
        }
        Fragment f = new Fragment(fragment_builder.toString(),id);
        f.analyze();
        fragments.add(f);

        for(Fragment fr : fragments)
        {
            HashMap<String,Integer> rels = fr.getRelations();
            Iterator<String> fr_key = rels.keySet().iterator();
            while(fr_key.hasNext())
            {
                String name = fr_key.next();
                Integer value = rels.get(name);
                ArrayList<String> keys = TextWizard.getSimilarWords(relations,name);
                if(keys.isEmpty())
                {
                    relations.put(name,1);
                }
                for(String st : keys)
                {
                    int val_of_sinonim = relations.get(st);
                    int value_for_sinonim = value+val_of_sinonim;
                    relations.put(st,value_for_sinonim);
                }

            }
        }
    }
    public ArrayList<Fragment> getFragments()
    {
        return fragments;
    }
    public String getName()
    {
        return chapter_name;
    }
    public HashMap<String,Integer> getTopRelations()
    {
        return TextWizard.getBestRelations(relations, TextWizard.ANALYZER_CHAPTER_TOP_RELATION_COUNT);
    }
}

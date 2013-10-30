package racoonsoft.chaos.magic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TextWizard
{
    public static int ANALYZER_FRAGMENT_SIZE_SENTENCES = 30;
    public static int ANALYZER_FRAGMENT_TOP_RELATION_COUNT = 5;
    public static int ANALYZER_CHAPTER_TOP_RELATION_COUNT = 8;

    public static boolean isWordPreposition(String word)
    {
        return word.equalsIgnoreCase("без")
                ||word.equalsIgnoreCase("до")
                ||word.equalsIgnoreCase("из")
                ||word.equalsIgnoreCase("нем")
                ||word.equalsIgnoreCase("на")
                ||word.equalsIgnoreCase("по")
                ||word.equalsIgnoreCase("от")
                ||word.equalsIgnoreCase("перед")
                ||word.equalsIgnoreCase("при")
                ||word.equalsIgnoreCase("через")
                ||word.equalsIgnoreCase("за")
                ||word.equalsIgnoreCase("над")
                ||word.equalsIgnoreCase("об")
                ||word.equalsIgnoreCase("под")
                ||word.equalsIgnoreCase("про")
                ||word.equalsIgnoreCase("тд")
                ||word.equalsIgnoreCase("тп")
                ||word.equalsIgnoreCase("не")
                ||word.equalsIgnoreCase("же")
                ||word.equalsIgnoreCase("для")
                ||word.equalsIgnoreCase("ага")
                ||word.equalsIgnoreCase("аж")
                ||word.equalsIgnoreCase("бы")
                ||word.equalsIgnoreCase("было")
                ||word.equalsIgnoreCase("быдто")
                ||word.equalsIgnoreCase("ведь")
                ||word.equalsIgnoreCase("во")
                ||word.equalsIgnoreCase("вон")
                ||word.equalsIgnoreCase("вот")
                ||word.equalsIgnoreCase("вроде")
                ||word.equalsIgnoreCase("все")
                ||word.equalsIgnoreCase("все-таки")
                ||word.equalsIgnoreCase("всего")
                ||word.equalsIgnoreCase("даже")
                ||word.equalsIgnoreCase("если")
                ||word.equalsIgnoreCase("еще")
                ||word.equalsIgnoreCase("же")
                ||word.equalsIgnoreCase("как")
                ||word.equalsIgnoreCase("раз")
                ||word.equalsIgnoreCase("бы")
                ||word.equalsIgnoreCase("ли")
                ||word.equalsIgnoreCase("лишь")
                ||word.equalsIgnoreCase("ну")
                ||word.equalsIgnoreCase("что")
                ||word.equalsIgnoreCase("но")
                ||word.equalsIgnoreCase("это")
                ||word.equalsIgnoreCase("те")
                ||word.equalsIgnoreCase("тем")
                ||word.equalsIgnoreCase("та")
                ||word.equalsIgnoreCase("тот")
                ||word.equalsIgnoreCase("этот")
                ||word.equalsIgnoreCase("эта")
                ||word.equalsIgnoreCase("эти")
                ||word.equalsIgnoreCase("ни")
                ||word.equalsIgnoreCase("никак")
                ||word.equalsIgnoreCase("ну")
                ||word.equalsIgnoreCase("откуда")
                ||word.equalsIgnoreCase("пока")
                ||word.equalsIgnoreCase("тоже")
                ||word.equalsIgnoreCase("те")
                ||word.equalsIgnoreCase("уж")
                ||word.equalsIgnoreCase("уже")
                ||word.equalsIgnoreCase("хоть")
                ||word.equalsIgnoreCase("что-то")
                ||word.equalsIgnoreCase("что-либо")
                ||word.equalsIgnoreCase("чтобы")
                ||word.equalsIgnoreCase("чтоб")
                ||word.equalsIgnoreCase("чуть")

                ||word.equalsIgnoreCase("я")
                ||word.equalsIgnoreCase("ты")
                ||word.equalsIgnoreCase("он")
                ||word.equalsIgnoreCase("она")
                ||word.equalsIgnoreCase("оно")
                ||word.equalsIgnoreCase("они")
                ||word.equalsIgnoreCase("мой")
                ||word.equalsIgnoreCase("их")
                ||word.equalsIgnoreCase("ее")
                ||word.equalsIgnoreCase("её")
                ||word.equalsIgnoreCase("или")
                ||word.equalsIgnoreCase("мне")
                ||word.equalsIgnoreCase("ей")
                ||word.equalsIgnoreCase("ему")
                ||word.equalsIgnoreCase("им")

                ||word.equalsIgnoreCase("тут")
                ||word.equalsIgnoreCase("этом")
                ||word.equalsIgnoreCase("так")
                ||word.equalsIgnoreCase("быть")
                ||word.equalsIgnoreCase("некое")
                ||word.equalsIgnoreCase("того")
                ||word.equalsIgnoreCase("есть")
                ||word.equalsIgnoreCase("то")
                ||word.equalsIgnoreCase("нет")
                ||word.equalsIgnoreCase("чем")
                ||word.equalsIgnoreCase("был")
                ||word.equalsIgnoreCase("была")
                ||word.equalsIgnoreCase("были")
                ||word.equalsIgnoreCase("было")
                ||word.equalsIgnoreCase("будет")
                ||word.equalsIgnoreCase("бывал")
                ||word.equalsIgnoreCase("ты")
                ||word.equalsIgnoreCase("только")
                ||word.equalsIgnoreCase("итак")
                ||word.equalsIgnoreCase("пусть")
                ||word.equalsIgnoreCase("его")

                ||word.equalsIgnoreCase("которые")
                ||word.equalsIgnoreCase("этого")
                ||word.equalsIgnoreCase("который")
                ||word.equalsIgnoreCase("которое")
                ||word.equalsIgnoreCase("которая")
                ||word.equalsIgnoreCase("того")
                ||word.equalsIgnoreCase("всех")
                ||word.equalsIgnoreCase("тех")
                ||word.equalsIgnoreCase("когда")
                ||word.equalsIgnoreCase("тебя")
                ||word.equalsIgnoreCase("твой")
                ||word.equalsIgnoreCase("мы")
                ||word.equalsIgnoreCase("также")
                ||word.equalsIgnoreCase("да")
                ||word.equalsIgnoreCase("вас")
                ||word.equalsIgnoreCase("них")
                ||word.equalsIgnoreCase("где")
                ||word.equalsIgnoreCase("среди")
                ||word.equalsIgnoreCase("между")
                ||word.equalsIgnoreCase("собой")
                ||word.equalsIgnoreCase("этого")


                ;
    }
    public static String purifyWord(String word)
    {
        return word.replace(".", "")
                .replace(",", "")
                .replace("?", "")
                .replace("'", "")
                .replace("\"", "")
                .replace(":", "")
                .replace("«", "")
                .replace("»", "")
                .replace("(", "")
                .replace(")", "")
                .replace("_", "")
                .toLowerCase();
    }
    static class Relation
    {
        public String word;
        public int value;
    }
    public static HashMap<String,Integer> getBestRelations(HashMap<String,Integer> relations,int count)
    {
        ArrayList<Relation> result = new ArrayList<Relation>();

        Iterator<String> keys = relations.keySet().iterator();
        while(keys.hasNext())
        {
            String name = keys.next();
            Integer value = relations.get(name);
            if(result.size()<count)
            {
                for(int i=0; i<result.size(); i++)
                {
                    Relation in_arr = result.get(i);

                    if(value>in_arr.value)
                    {
                        Relation r = new Relation();
                        r.value = value;
                        r.word = name;
                        result.add(i,r);
                        break;
                    }
                    if(i==result.size()-1)
                    {
                        Relation r = new Relation();
                        r.value = value;
                        r.word = name;
                        result.add(i,r);
                        break;
                    }
                }
                if(result.size()==0)
                {
                    Relation r = new Relation();
                    r.value = value;
                    r.word = name;
                    result.add(r);
                }
            }
            else
            {
                for(int i=0; i<result.size(); i++)
                {
                    Relation in_arr = result.get(i);

                    if(value>in_arr.value)
                    {
                        Relation r = new Relation();
                        r.value = value;
                        r.word = name;
                        result.add(i,r);
                        result.remove(result.size()-1);
                        break;
                    }
                }
            }
        }
        HashMap<String,Integer> final_res = new HashMap<String, Integer>();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for(int i=0; i<result.size(); i++)
        {
            System.out.println(result.get(i).word+" : "+result.get(i).value);
            final_res.put(result.get(i).word,result.get(i).value);
        }

        return final_res;
    }

    // It`ll help us to find entry, which is similar to the key given...
    public static ArrayList<String> getSimilarWords(HashMap<String,Integer> source,String key)
    {
        ArrayList<String> result = new ArrayList<String>();

        if(source.containsKey(key))
        {
            result.add(key);
        }

        return result;
    }
}

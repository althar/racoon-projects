package racoonsoft.racoonspring.data.xml;

import java.util.ArrayList;

public class HtmlParser
{
    /**
     * Method allows to find html parts matching pattern given
     * @param pattern Kind of mask to match. It must look like "<span*param_1="value_1"*param_2=*>some</span>"
     * @return
     */
    public static ArrayList<String> find(String pattern,String html)
    {
        ArrayList<String> res = new ArrayList<String>();
        String[] separators = pattern.split("\\*");
        int current_part = 0;
        int current_index = 0;
        while(true)
        {
            int start_index = -1;
            for(int i=0; i<separators.length; i++)
            {
                int index_of_part = html.indexOf(separators[i],current_index);
                if(index_of_part == -1)// Not found...
                {
                    return res;
                }
                else
                {
                    if(start_index == -1)
                    {
                        start_index = current_index;
                    }
                    current_index = index_of_part+separators[i].length();
                }
            }
            String matched_seq = html.substring(start_index,current_index);
            res.add(matched_seq);
        }
    }
}

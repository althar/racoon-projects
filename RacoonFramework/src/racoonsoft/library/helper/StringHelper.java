package racoonsoft.library.helper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper
{
    public static String getCharEncodedString(String charString)
    {
        String result = charString;
        Matcher match = Pattern.compile("\\\\u([0-9a-fA-F]{4})| ").matcher(charString);
        while (match.find()) {
            String character = match.group(1);
            if (character == null)
            {
                character = match.group();
            }
            if(character.length()==4)
            {
                char ch = (char)Integer.parseInt(character.replace("\\u",""),16);
                result = result.replace("\\u"+character,new String(new char[]{ch}));
            }
        }
//        return decodedInput.toString();
        return result;
    }
    public static String getCharEncodedString2(String str)
    {
        str = str.replace("\\","");
        String[] arr = str.split("u");
        String text = "";
        for(int i = 1; i < arr.length; i++){
            int hexVal = Integer.parseInt(arr[i].trim(), 16);
            text += (char)hexVal;
        }
        return text;
    }
    public static ArrayList<String> findSubstring(String text,String regexp,boolean distinct)
    {
        ArrayList<String> result = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        while(matcher.find())
        {
            String res = matcher.group(0);
            if(!distinct||!result.contains(res))
            {
                result.add(matcher.group(0));
            }
        }
        return result;
    }
}

package racoonsoft.library.helper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper
{
    public static String getCharEncodedString(String charString)
    {
        StringBuffer decodedInput = new StringBuffer();
        Matcher match = Pattern.compile("\\\\u([0-9a-fA-F]{4})| ").matcher(charString);
        while (match.find()) {
            String character = match.group(1);
            if (character == null)
                decodedInput.append(match.group());
            else
                decodedInput.append((char)Integer.parseInt(character, 16));
        }
        return decodedInput.toString();
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

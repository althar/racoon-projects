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
    public static String normalizeOzonString(Object str)
    {
        if(str == null)
        {
            return "";
        }
        String result = getCharEncodedString(str.toString());
        result = result.replaceAll("\\\\","");
        result = result.replaceAll("<iframe[\\s\\S]+?iframe>","");
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

    public static String getDomainByLevel(String domain, int level)
    {
        String[] domainParts = domain.split("\\.");
        if(level<=domainParts.length)
        {
            StringBuilder builder = new StringBuilder();
            for(int i=domainParts.length-level; i<domainParts.length; i++)
            {
                builder.append(domainParts[i]);
                if(i<domainParts.length-1)
                {
                    builder.append(".");
                }
            }
            return builder.toString();
        }
        return null;
    }

    public static String getContentDespositionFilename(String agent, String fileName) throws Exception
    {
        if(agent==null)
        {
            agent="";
        }
        boolean isInternetExplorer = (agent.toLowerCase().indexOf("msie") > -1);
        byte[] fileNameBytes = fileName.getBytes((isInternetExplorer) ? ("windows-1251") : ("utf-8"));
        String dispositionFileName = "";
        for (byte b : fileNameBytes)
        {
            dispositionFileName += (char) (b & 0xff);
        }
        return dispositionFileName;
    }
}

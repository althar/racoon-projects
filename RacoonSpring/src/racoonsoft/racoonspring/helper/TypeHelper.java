package racoonsoft.racoonspring.helper;


import java.util.HashMap;
import java.util.Iterator;

public class TypeHelper
{
    public static boolean isDouble(String val)
    {
		try
		{
			Double.parseDouble(val);
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
    }
	public static boolean isInt(String val)
    {
		try
		{
			Integer.parseInt(val);
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
    }
    public static boolean isBoolean(String val)
    {
		return val!=null&&(val.equalsIgnoreCase("true")||val.equalsIgnoreCase("false"));
    }
    public static boolean isNull(String val)
    {
		return val==null||val.equalsIgnoreCase("null");
    }
    public static HashMap addHashMap(HashMap source, HashMap dest,boolean replace)
    {
        Iterator iter = source.keySet().iterator();
        while(iter.hasNext())
        {
            Object key = iter.next();
            Object value = source.get(key);
            if(replace||!dest.containsKey(key))
            {
                dest.put(key,dest);
            }
        }
        return dest;
    }
}

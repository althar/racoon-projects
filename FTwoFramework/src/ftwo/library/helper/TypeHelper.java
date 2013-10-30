package ftwo.library.helper;


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
}

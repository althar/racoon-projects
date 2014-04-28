package racoonsoft.businesswin.service;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: AlThar
 * Date: 18.03.14
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class Math
{
    public static Double random(Double min,Double max, int digits)
    {
        int minInt = (int)(min*(java.lang.Math.pow(10.0,digits)));
        int maxInt = (int)(max*(java.lang.Math.pow(10.0,digits)));
        int r = new Random().nextInt(maxInt-minInt) + minInt;
        Double value = r*1.0/java.lang.Math.pow(10.0,digits);
        return value;
    }
    public static Double random(Double min,Double max)
    {
        return random(min,max,3);
    }
    public static Boolean randomBool(int chance_divider)
    {
        return new Random().nextInt(chance_divider)>=chance_divider;
    }
}

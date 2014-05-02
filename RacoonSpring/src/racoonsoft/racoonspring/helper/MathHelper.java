package racoonsoft.racoonspring.helper;

public class MathHelper
{
    public static double round(double value, int accuracy)
    {
        if (accuracy < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, accuracy);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

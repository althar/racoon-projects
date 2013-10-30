package racoonsoft.library.yandex.direct.enums;

import java.util.ArrayList;

public class TimeTarget
{
    public static final ArrayList<Integer> AllDays = new ArrayList<Integer>();
    public static final ArrayList<Integer> WeekDays = new ArrayList<Integer>();
    public static final ArrayList<Integer> WeekEnd = new ArrayList<Integer>();

    public static final ArrayList<Integer> AllHours = new ArrayList<Integer>();
    public static final ArrayList<Integer> WorkHours = new ArrayList<Integer>();
    public static final ArrayList<Integer> NightHours = new ArrayList<Integer>();

    static
    {
        for(int i=1; i<8; i++)
        {
            AllDays.add(i);
            if(i<6)// weekdays
            {
                WeekDays.add(i);
            }
            if(i>5)// weekend
            {
                WeekEnd.add(i);
            }
        }
        for(int i=0; i<24; i++)
        {
            AllHours.add(i);
            if(i>7&&i<21)// work hours
            {
                WorkHours.add(i);
            }
            else// night hours
            {
                NightHours.add(i);
            }
        }
    }
    public static ArrayList<Integer> days(int from, int to)
    {
        if(from<0)
        {
            from=0;
        }
        if(from>7)
        {
            from=7;
        }
        if(to<0)
        {
            to=0;
        }
        if(to>7)
        {
            to=7;
        }
        ArrayList<Integer> days = new ArrayList<Integer>();
        for(int i=from; i<=to; i++)
        {
            days.add(i);
        }
        return days;
    }
    public static ArrayList<Integer> hours(int from, int to)
    {
        if(from<0)
        {
            from=0;
        }
        if(from>23)
        {
            from=7;
        }
        if(to<0)
        {
            to=0;
        }
        if(to>23)
        {
            to=7;
        }
        ArrayList<Integer> hours = new ArrayList<Integer>();
        for(int i=from; i<=to; i++)
        {
            hours.add(i);
        }
        return hours;
    }
}

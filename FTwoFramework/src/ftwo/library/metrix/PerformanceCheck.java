package ftwo.library.metrix;
import java.util.Date;
import java.util.HashMap;

public class PerformanceCheck
{
    private static HashMap<String,Long> StartMap = new HashMap<String,Long>();
    private static HashMap<String,Long> StopMap = new HashMap<String,Long>();

    public static long start(String process_name)
    {
        long time = System.nanoTime();
        synchronized (StartMap)
        {
            StartMap.put(process_name,time);
        }
        return time;
    }
    public static long stop(String process_name)
    {
        long time = System.nanoTime();
        synchronized (StopMap)
        {
            StopMap.put(process_name,time);
        }
        return time;
    }
    public static long getProcessDuration(String process_name)
    {
        Long start = StartMap.get(process_name);
        Long stop = StopMap.get(process_name);
        if(start == null)
        {
            return 0;
        }
        if(stop == null)
        {
            return System.nanoTime()-start;
        }
        return stop-start;
    }
}

package ftwo.library.processors;

import ftwo.library.helper.Helper;
import ftwo.library.logging.Logger;

import java.util.HashMap;
import java.util.Iterator;

public abstract class MultiThreadProcessor implements Runnable
{
    private HashMap<String,Thread> ThreadMap;
    private HashMap<String,Boolean> IsRunningMap;
    private String Name;

    public MultiThreadProcessor(String processor_name)
    {
        Name = processor_name;
        ThreadMap = new HashMap<String, Thread>();
        IsRunningMap = new HashMap<String, Boolean>();
    }
    public void start(String process_name) throws InterruptedException
    {
        Boolean IsRunning = IsRunningMap.get(process_name);
        if(IsRunning==null||!IsRunning)
        {
            Thread t = ThreadMap.get(process_name);
            if(t!=null)
            {
                t.join();
            }
            t = new Thread(this,process_name);
            IsRunningMap.put(process_name,true);
            ThreadMap.put(process_name,t);
            t.start();
            System.out.println("Process '"+process_name+"' started");
        }
    }
    public void start() throws InterruptedException
    {
        Iterator<String> iter = ThreadMap.keySet().iterator();
        while(iter.hasNext())
        {
            String key = iter.next();
            start(key);
        }
    }
    public void stop() throws InterruptedException
    {
        Iterator<String> iter = ThreadMap.keySet().iterator();
        while(iter.hasNext())
        {
            String key = iter.next();
            stop(key);
        }
    }
    public void stop(String process_name) throws InterruptedException
    {
        Boolean IsRunning = IsRunningMap.get(process_name);
        if(IsRunning!=null&&IsRunning)
        {
            Thread t = ThreadMap.get(process_name);
            if(t!=null)
            {
                IsRunningMap.put(process_name,false);
                t.join();
                ThreadMap.remove(process_name);
            }
        }
        System.out.println("Process '"+process_name+"' stoped");
    }

    public void run()
    {
        Boolean IsRunning = true;
        while(IsRunning)
        {
            try
            {
                String process_name = Thread.currentThread().getName();
                process(process_name);
                IsRunning = IsRunningMap.get(process_name);
                if(IsRunning==null)
                {
                    IsRunning = false;
                }
            }
            catch(Exception ex)
            {
                Logger.error("Processor: " + Name + ". " + Helper.getStackTraceString(ex));
            }
        }
    }

    public abstract void process(String process_name);
}
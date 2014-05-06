package racoonsoft.racoonspring.service;

import racoonsoft.racoonspring.helper.Helper;

import java.util.logging.Logger;

public abstract class SeparateThreadService implements Runnable
{
    protected Thread t;
    protected boolean IsRunning;
    protected String Name;

    public Thread getT() {
        return t;
    }

    public boolean isRunning() {
        return IsRunning;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public SeparateThreadService(String processor_name)
    {
        Name = processor_name;
        t = new Thread(this,Name);
    }
    public void start() throws InterruptedException
    {
        if(!IsRunning)
        {
            if(t!=null)
            {
                t.join();
            }
            t = new Thread(this,Name);
            IsRunning = true;
            t.start();
        }
    }
    public void stop() throws InterruptedException
    {
        if(IsRunning)
        {
            IsRunning = false;
            t.join();
        }
    }

    public void run()
    {
        while(IsRunning)
        {
            try
            {
                process();
            }
            catch(Exception ex)
            {
                System.out.println(Helper.getStackTraceString(ex));
            }
        }
    }

    public abstract void process() throws Exception;
}

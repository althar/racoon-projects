package ftwo.library.processors;

import ftwo.library.helper.Helper;
import ftwo.library.logging.Logger;


public abstract class SeparateThreadProcessor implements Runnable
{
    private Thread t;
    private boolean IsRunning;
    private String Name;

    public SeparateThreadProcessor(String processor_name)
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
        System.out.println("stoped");
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
                Logger.error("Processor: "+Name+". "+Helper.getStackTraceString(ex));
            }
        }
    }

    public abstract void process();
}

package ftwo.library.engines.convey.processor;

import ftwo.library.engines.convey.gatekeeper.CommandTransceiver;
import ftwo.library.exceptions.ItemAlreadyExistsException;

import java.util.HashMap;
import java.util.Iterator;

public class Mastermind
{
    private HashMap<String,CommandProcessor> Processors = new HashMap<String, CommandProcessor>();
    public Mastermind()
    {

    }
    public CommandProcessor get(String transceiver_name)
    {
        return Processors.get(transceiver_name);
    }
    public void add(String processor_name,CommandProcessor item) throws ItemAlreadyExistsException
    {
        if(Processors.containsKey(processor_name))
        {
            throw new ItemAlreadyExistsException("Processor '"+processor_name+"' already exists.");
        }
        Processors.put(processor_name,item);
    }
    public void start() throws InterruptedException
    {
        Iterator<CommandProcessor> iter = Processors.values().iterator();
        while(iter.hasNext())
        {
            CommandProcessor cp = iter.next();
            cp.start();
        }
    }
    public void start(String processor_name) throws InterruptedException
    {
        CommandProcessor cp = Processors.get(processor_name);
        if(cp!=null)
        {
            cp.start();
        }
    }
    public void stop() throws InterruptedException
    {
        Iterator<CommandProcessor> iter = Processors.values().iterator();
        while(iter.hasNext())
        {
            CommandProcessor cp = iter.next();
            cp.stop();
        }
    }
    public void stop(String transceiver_name) throws InterruptedException
    {
        CommandProcessor cp = Processors.get(transceiver_name);
        if(cp!=null)
        {
            cp.stop();
        }
    }
}

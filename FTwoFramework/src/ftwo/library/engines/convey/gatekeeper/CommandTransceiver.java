package ftwo.library.engines.convey.gatekeeper;

import ftwo.library.engines.convey.commandstorage.Command;
import ftwo.library.engines.convey.commandstorage.CommandStorage;
import ftwo.library.exceptions.ItemAlreadyExistsException;
import ftwo.library.exceptions.QueueOverflowException;
import ftwo.library.logging.Logger;
import ftwo.library.processors.MultiThreadProcessor;
import ftwo.library.processors.SeparateThreadProcessor;

import java.util.HashMap;
import java.util.Iterator;

public abstract class CommandTransceiver extends MultiThreadProcessor
{
    public HashMap<String, CommandTransceiverFilter> PutFilters = new HashMap<String, CommandTransceiverFilter>();
    public HashMap<String, CommandTransceiverFilter> GetFilters = new HashMap<String, CommandTransceiverFilter>();

    public CommandTransceiver(String name)
    {
        super("Gate keeper processor: "+name);
    }
    public synchronized void addPutFilter(String name,CommandTransceiverFilter filter) throws ItemAlreadyExistsException
    {
        if(PutFilters.containsKey(name))
        {
            throw new ItemAlreadyExistsException("Put filter '"+name+"' already exists");
        }
        PutFilters.put(name,filter);
    }
    public synchronized void addGetFilter(String name,CommandTransceiverFilter filter) throws ItemAlreadyExistsException
    {
        if(GetFilters.containsKey(name))
        {
            throw new ItemAlreadyExistsException("Get filter '"+name+"' already exists");
        }
        GetFilters.put(name,filter);
    }
    public synchronized void clearPutFilters()
    {
        PutFilters = new HashMap<String, CommandTransceiverFilter>();
    }
    public synchronized void clearGetFilters()
    {
        GetFilters = new HashMap<String, CommandTransceiverFilter>();
    }
    public synchronized void putCommand(String queue_name,Command command,boolean clear_queue) throws QueueOverflowException
    {
        Command c = command;
        Iterator<CommandTransceiverFilter> iter = PutFilters.values().iterator();
        while(iter.hasNext())
        {
            CommandTransceiverFilter filter = iter.next();
            Logger.info("Put filter: "+filter.toString()+" is going to process...");
            c = filter.filter(c);
        }
        if(c!=null)
        {
            CommandStorage.put(queue_name,command,clear_queue);
        }
    }
    public synchronized Command getCommand(String queue_name) throws InterruptedException
    {
        Command c = CommandStorage.get(queue_name);
        Iterator<CommandTransceiverFilter> iter = GetFilters.values().iterator();
        while(iter.hasNext())
        {
            CommandTransceiverFilter filter = iter.next();
            Logger.info("Get filter: "+filter.toString()+" is going to process...");
            c = filter.filter(c);
        }
        return c;
    }
    public abstract void processTransfer(String transfer_process_name);
    public void process(String transfer_process_name)
    {
        processTransfer(transfer_process_name);
    }
    @Override
    public void start() throws InterruptedException
    {
        start("accept");
        start("receive");
        start("transmit");
    }
    @Override
    public void stop() throws InterruptedException
    {
        stop("accept");
        stop("receive");
        stop("transmit");
    }
}

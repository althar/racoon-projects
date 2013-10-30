package ftwo.library.engines.convey;

import ftwo.library.engines.convey.gatekeeper.CommandTransceiver;
import ftwo.library.engines.convey.gatekeeper.CommandTransceiverFilter;
import ftwo.library.engines.convey.gatekeeper.GateKeeper;
import ftwo.library.engines.convey.processor.CommandProcessor;
import ftwo.library.engines.convey.processor.Mastermind;
import ftwo.library.exceptions.ItemAlreadyExistsException;

import java.util.HashMap;

public class ConveyProcessor
{
    private GateKeeper GKeeper;
    private Mastermind MMind;

    public ConveyProcessor()
    {
        GKeeper = new GateKeeper();
        MMind = new Mastermind();
    }

    // Transceivers...
    public void addCommandTransceiver(String transceiver_name,CommandTransceiver transceiver) throws ItemAlreadyExistsException
    {
        GKeeper.add(transceiver_name,transceiver);
    }
    public void startTransceiver(String transceiver_name) throws InterruptedException
    {
        GKeeper.start(transceiver_name);
    }
    public void startTransceivers() throws InterruptedException
    {
        GKeeper.start();
    }
    public void stopTransceiver(String transceiver_name) throws InterruptedException
    {
        GKeeper.stop(transceiver_name);
    }
    public void stopTransceivers() throws InterruptedException
    {
        GKeeper.stop();
    }

    // Processors...
    public void addCommandProcessor(String processor_name,CommandProcessor processor) throws ItemAlreadyExistsException
    {
        MMind.add(processor_name,processor);
    }
    public void startCommandProcessor(String transceiver_name) throws InterruptedException
    {
        MMind.start(transceiver_name);
    }
    public void startCommandProcessors() throws InterruptedException
    {
        MMind.start();
    }
    public void stopCommandProcessor(String transceiver_name) throws InterruptedException
    {
        MMind.stop(transceiver_name);
    }
    public void stopCommandProcessors() throws InterruptedException
    {
        MMind.stop();
    }

    // Filters...
    public synchronized void addPutFilter(String transceiver_name, String name,CommandTransceiverFilter filter) throws ItemAlreadyExistsException
    {
        CommandTransceiver ct = GKeeper.get(transceiver_name);
        if(ct==null)
        {
            System.out.println("Transceiver "+transceiver_name+" doesn`t exist");
            return;
        }
        if(ct.PutFilters.containsKey(name))
        {
            throw new ItemAlreadyExistsException("Put filter '"+name+"' already exists");
        }
        ct.PutFilters.put(name,filter);
    }
    public synchronized void addGetFilter(String transceiver_name, String name,CommandTransceiverFilter filter) throws ItemAlreadyExistsException
    {
        CommandTransceiver ct = GKeeper.get(transceiver_name);
        if(ct==null)
        {
            return;
        }
        if(ct.GetFilters.containsKey(name))
        {
            throw new ItemAlreadyExistsException("Get filter '"+name+"' already exists");
        }
        ct.GetFilters.put(name,filter);
    }
    public synchronized void clearPutFilters(String transceiver_name)
    {
        CommandTransceiver ct = GKeeper.get(transceiver_name);
        if(ct==null)
        {
            return;
        }
        ct.PutFilters = new HashMap<String, CommandTransceiverFilter>();
    }
    public synchronized void clearGetFilters(String transceiver_name)
    {
        CommandTransceiver ct = GKeeper.get(transceiver_name);
        if(ct==null)
        {
            return;
        }
        ct.GetFilters = new HashMap<String, CommandTransceiverFilter>();
    }
}

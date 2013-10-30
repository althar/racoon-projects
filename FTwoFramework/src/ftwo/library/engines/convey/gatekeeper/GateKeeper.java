package ftwo.library.engines.convey.gatekeeper;

import ftwo.library.exceptions.ItemAlreadyExistsException;

import java.util.HashMap;
import java.util.Iterator;

public class GateKeeper
{
    private HashMap<String,CommandTransceiver> Transceivers = new HashMap<String, CommandTransceiver>();

    public GateKeeper()
    {

    }
    public CommandTransceiver get(String transceiver_name)
    {
        return Transceivers.get(transceiver_name);
    }
    public void add(String transceiver_name,CommandTransceiver item) throws ItemAlreadyExistsException
    {
        if(Transceivers.containsKey(transceiver_name))
        {
            throw new ItemAlreadyExistsException("Processor '"+transceiver_name+"' already exists.");
        }
        Transceivers.put(transceiver_name,item);
    }
    public void start() throws InterruptedException
    {
        Iterator<CommandTransceiver> iter = Transceivers.values().iterator();
        while(iter.hasNext())
        {
            CommandTransceiver gkp = iter.next();
            gkp.start();
        }
    }
    public void start(String transceiver_name) throws InterruptedException
    {
        CommandTransceiver gkp = Transceivers.get(transceiver_name);
        if(gkp!=null)
        {
            gkp.start();
        }
    }
    public void stop() throws InterruptedException
    {
        Iterator<CommandTransceiver> iter = Transceivers.values().iterator();
        while(iter.hasNext())
        {
            CommandTransceiver gkp = iter.next();
            gkp.stop();
        }
    }
    public void stop(String transceiver_name) throws InterruptedException
    {
        CommandTransceiver gkp = Transceivers.get(transceiver_name);
        if(gkp!=null)
        {
            gkp.stop();
        }
    }
}

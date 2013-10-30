package ftwo.library.engines.convey.processor;

import ftwo.library.engines.convey.commandstorage.Command;
import ftwo.library.engines.convey.commandstorage.CommandStorage;
import ftwo.library.engines.convey.gatekeeper.CommandTransceiverFilter;
import ftwo.library.exceptions.QueueOverflowException;
import ftwo.library.processors.MultiThreadProcessor;
import ftwo.library.processors.SeparateThreadProcessor;

import java.util.Iterator;

public abstract class CommandProcessor extends SeparateThreadProcessor
{
    public CommandProcessor(String name)
    {
        super(name);
    }
    public synchronized void putCommand(String queue_name,Command command,boolean clear_queue) throws QueueOverflowException
    {
        Command c = command;
        if(c!=null)
        {
            CommandStorage.put(queue_name, command, clear_queue);
        }
    }
    public synchronized Command getCommand(String queue_name) throws InterruptedException
    {
        Command c = CommandStorage.get(queue_name);
        return c;
    }
}

package ftwo.library.engines.convey.commandstorage;

import ftwo.library.collections.Queue;
import ftwo.library.exceptions.QueueOverflowException;

import java.util.HashMap;

public class CommandStorage
{
    private static HashMap<String,Queue> CommandQs = new HashMap<String, Queue>();
    private static HashMap<String,Integer> MaxSizeConditions = new HashMap<String, Integer>();

    public CommandStorage()
    {

    }

    public static synchronized void setMaxSize(String queue_name, int max_size)
    {
        MaxSizeConditions.put(queue_name,max_size);
    }
    public static synchronized Command get(String queue_name) throws InterruptedException
    {
        Queue q = CommandQs.get(queue_name);
        if(q!=null&&!q.isEmpty())
        {
            return (Command)q.dequeue();
        }
        return null;
    }
    public static synchronized void put(String queue_name,Command command,boolean clear_queue) throws QueueOverflowException
    {
        if(!CommandQs.containsKey(queue_name))
        {
            CommandQs.put(queue_name,new Queue());
        }
        if(clear_queue)
        {
            CommandQs.put(queue_name,new Queue());
        }
        Queue q = CommandQs.get(queue_name);
        int max_size = Integer.MAX_VALUE;
        if(MaxSizeConditions.containsKey(queue_name))
        {
            max_size = MaxSizeConditions.get(queue_name);
        }
        if(q.getLength()>max_size)
        {
            throw new QueueOverflowException("Queue overflow. Maximum size is: "+max_size);
        }
        q.enqueue(command);
    }
    public static synchronized void put(String queue_name,Command command) throws QueueOverflowException
    {
        put(queue_name,command,false);
    }
    public static synchronized Queue getQueue(String queue_name)
    {
        return CommandQs.get(queue_name);
    }
}

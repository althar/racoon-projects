package racoonsoft.library.collections;

import java.util.ArrayList;
import java.util.Date;

public class QueueExtended extends sun.misc.Queue
{
    private long Size = 0;
    private String Name;
    private long Created;
    private long Dequeued;
    private long Enqueued;
    private ArrayList<Object> Q;
    private int TailElementIndex = -1;
    private int HeadElementIndex = -1;

    public QueueExtended(String name)
    {
        Name = name;
        Created = new Date().getTime();
        Dequeued = Created;
        Q = new ArrayList<Object>();
    }
    public String name()
    {
        return Name;
    }
    @Override
    public synchronized void enqueue(Object value)
    {
        if(value!=null)
        {
            Size++;
            Q.add(value);
            TailElementIndex++;
            if(Q.isEmpty())
            {
                HeadElementIndex = 0;
            }
            Enqueued = new Date().getTime();
        }
    }
    public synchronized void enqueue(ArrayList<Object> values)
    {
        for(int i=0; i<values.size(); i++)
        {
            enqueue(values.get(i));
        }
        Enqueued = new Date().getTime();
    }
    @Override
    public synchronized Object dequeue() throws InterruptedException
    {
        if(HeadElementIndex<=TailElementIndex&&HeadElementIndex>-1)
        {
            Dequeued = new Date().getTime();
            Size--;
            Object res = Q.get(HeadElementIndex);
            Q.set(HeadElementIndex,null);
            HeadElementIndex++;
            Dequeued = new Date().getTime();
            return res;
        }
        Dequeued = new Date().getTime();
        return null;
    }
    public synchronized ArrayList<Object> dequeueAll() throws InterruptedException
    {
        if(HeadElementIndex<=TailElementIndex&&HeadElementIndex>-1)
        {
            Size = 0;
            HeadElementIndex = -1;
            TailElementIndex = -1;
            Dequeued = new Date().getTime();
            return (ArrayList<Object>)Q.subList(HeadElementIndex,TailElementIndex);
        }
        return new ArrayList<Object>();
    }
    public synchronized ArrayList<Object> getRange(int start)
    {
        ArrayList<Object> res = new ArrayList<Object>();
        if(start<Size)
        {
            for(int i=start+HeadElementIndex; i<start+TailElementIndex; i++)
            {
                res.add(Q.get(i));
            }
            Dequeued = new Date().getTime();
        }
        return res;
    }
    public synchronized ArrayList<Object> getRange(int start,int count)
    {
        ArrayList<Object> res = new ArrayList<Object>();
        if(start<Size)
        {
            for(int i=start+HeadElementIndex; i<(start+TailElementIndex)&&res.size()<count; i++)
            {
                res.add(Q.get(i));
            }
            Dequeued = new Date().getTime();
        }
        return res;
    }
    public synchronized long size()
    {
        return Size;
    }
    public long getPeriodFromLastDequeue()
    {
        return (long)(new Date().getTime()-Dequeued);
    }
    public long getPeriodFromLastEnqueue()
    {
        return (long)(new Date().getTime()-Enqueued);
    }
    public long getLivePeriod()
    {
        return (long)(new Date().getTime()-Created);
    }
}

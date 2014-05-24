package racoonsoft.racoonspring.mail;

public class Queue extends sun.misc.Queue
{
    private long size = 0;

    @Override
    public synchronized void enqueue(Object obj)
    {
        super.enqueue(obj);
        size++;
    }
    @Override
    public synchronized Object dequeue() throws InterruptedException
    {
        Object res = super.dequeue();
        size--;
        return res;
    }
    public synchronized long getLength()
    {
        return size;
    }
}

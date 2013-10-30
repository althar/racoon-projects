package ftwo.library.collections;

public class Queue<T> extends sun.misc.Queue
{
    private long size = 0;

    @Override
    public synchronized void enqueue(Object obj)
    {
        super.enqueue(obj);
        size++;
    }
    @Override
    public synchronized T dequeue() throws InterruptedException
    {
        Object res = super.dequeue();
        size--;
        return (T)res;
    }
    public synchronized long getLength()
    {
        return size;
    }
}

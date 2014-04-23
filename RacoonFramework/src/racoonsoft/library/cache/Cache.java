package racoonsoft.library.cache;

import racoonsoft.library.json.JSONProcessor;

import java.util.Date;

public class Cache<T>
{
    private long created;
    private long lifetime;
    private T data;

    public Cache(T data, long lifetime)
    {
        this.data = data;
        this.lifetime = lifetime;
        created = new Date().getTime();
    }

    public T get()
    {
        if(new Date().getTime()-created<lifetime)
        {
            return data;
        }
        return null;
    }
}

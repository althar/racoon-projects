package racoonsoft.library.cache;

import java.util.HashMap;

public class CacheProcessor<K,V>
{
    private HashMap<K,Cache<V>> items = new HashMap<K, Cache<V>>();

    public void put(K key,V data, long lifetime)
    {
        Cache<V> item = new Cache<V>(data,lifetime);
        items.put(key,item);
    }
    public V get(K key)
    {
        Cache<V> cache = items.get(key);
        if(cache==null)
        {
            return null;
        }
        V data = cache.get();
        return data;
    }
    public boolean hasCache(K key)
    {
        return get(key)!=null;
    }
}

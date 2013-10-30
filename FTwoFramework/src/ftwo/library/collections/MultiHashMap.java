package ftwo.library.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class MultiHashMap
{
    private ArrayList<Object> Objects = new ArrayList<Object>();
    private HashMap<Object,HashSet<Integer>> Map = new HashMap<Object, HashSet<Integer>>();
    public synchronized void add(Object key,Object value)
    {
        if(key==null||value==null)
        {
            return;
        }
        int index = indexOf(value);
        if(index == -1)
        {
            Objects.add(value);
            index = Objects.size()-1;
        }

        HashSet map_set = Map.get(key);
        if(map_set==null)
        {
            map_set = new HashSet();
            Map.put(key,map_set);
        }
        map_set.add(index);
    }
    public synchronized void add(ArrayList<Object> key,Object value)
    {
        for(int i=0; i<key.size(); i++)
        {
            add(key.get(i),value);
        }
    }
    public synchronized void add(Object key,ArrayList<Object> value)
    {
        for(int i=0; i<value.size(); i++)
        {
            add(key,value.get(i));
        }
    }
    public synchronized void add(ArrayList<Object> key,ArrayList<Object> value)
    {
        for(int i=0; i<value.size(); i++)
        {
            add(key,value.get(i));
        }
    }
    public synchronized ArrayList<Object> get(Object key)
    {
        ArrayList<Object> res = new ArrayList<Object>();
        HashSet<Integer> keys = Map.get(key);
        if(keys!=null)
        {
            Iterator<Integer> key_iter = keys.iterator();
            while(key_iter.hasNext())
            {
                Object o = Objects.get(key_iter.next());
                if(o!=null)
                {
                    res.add(o);
                }
            }
        }
        return res;
    }
    public synchronized ArrayList<Object> getPartialMatch(ArrayList<Object> keys)
    {
        ArrayList<Object> res = new ArrayList<Object>();
        for(int i=0; i<keys.size(); i++)
        {
            ArrayList<Object> arr = get(keys.get(i));
            for(int j=0; j<arr.size(); j++)
            {
                if(!res.contains(arr.get(j)))
                {
                    res.add(arr.get(j));
                }
            }
        }
        return res;
    }
    public synchronized ArrayList<Object> getFullMatch(ArrayList<Object> keys)
    {
        ArrayList<Object> res = new ArrayList<Object>();
        ArrayList<Object> final_res = new ArrayList<Object>();

        for(int i=0; i<keys.size(); i++)
        {
            if(final_res.size()==0)
            {

                final_res = get(keys.get(i));
            }
            else
            {
                res = (ArrayList<Object>)final_res.clone();
                final_res = new ArrayList<Object>();
                ArrayList<Object> arr = get(keys.get(i));
                if(arr.size()==0)
                {
                    return final_res;
                }
                for(int j=0; j<arr.size(); j++)
                {
                    if(res.contains(arr.get(j)))
                    {
                        final_res.add(arr.get(j));
                    }
                }
                if(final_res.size()==0)
                {
                    return final_res;
                }
            }
        }
        return final_res;
    }
    public synchronized void removeByValue(Object value)
    {
        if(value!=null)
        {
            int last_index = 0;
            while((last_index = Objects.lastIndexOf(value))!=-1)
            {
                Objects.set(last_index,null);
            }
        }
        
    }
    public synchronized void removeByValue(ArrayList<Object> values)
    {
        for(int i=0; i<values.size(); i++)
        {
            removeByValue(values.get(i));
        }
    }
    public synchronized void removeByKey(Object key)
    {
        HashSet<Integer> indeces_to_remove = Map.get(key);
        if(indeces_to_remove!=null)
        {
            Iterator<Integer> iter = indeces_to_remove.iterator();
            while(iter.hasNext())
            {
                Integer obj_index = iter.next();
                if(obj_index!=null)
                {
                    Objects.set(obj_index,null);
                }
            }
        }
    }
    public synchronized boolean contains(Object value)
    {
        if(value!=null)
        {
            return Objects.contains(value);
        }
        return false;
    }
    public synchronized boolean hasAnyOf(ArrayList<Object> values)
    {
        for(int i=0; i<values.size(); i++)
        {
            if(contains(values.get(i)))
            {
                return true;
            }
        }
        return false;
    }
    public synchronized int indexOf(Object value)
    {
        return Objects.indexOf(value);
    }
    public synchronized ArrayList<Object> getKeys(Object value) throws Exception
    {
        throw new Exception("Not yet implemented");
    }
}

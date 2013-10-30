package racoonsoft.select.structure;

import racoonsoft.select.database.PGSQLDataSource;

import java.util.Date;
import java.util.HashMap;

public class BasketStorage
{
    public static PGSQLDataSource dbProc;

    private HashMap<String,Basket> baskets = new HashMap<String, Basket>();

    public void addBasket(String sessionId,Basket b)
    {
        baskets.put(sessionId,b);
    }
    public void addOne(Long goodId,String sessionId) throws Exception
    {
        Basket b = baskets.get(sessionId);
        if(b == null)
        {
            b = new Basket();
            baskets.put(sessionId,b);
        }
        b.addOne(goodId);
    }
    public void setGood(Long goodId,String sessionId,Integer count) throws Exception
    {
        Basket b = baskets.get(sessionId);
        if(b == null)
        {
            b = new Basket();
            baskets.put(sessionId,b);
        }
        b.setGood(goodId,count);
    }
    public void removeOne(Long goodId,String sessionId) throws Exception
    {
        Basket b = baskets.get(sessionId);
        if(b == null)
        {
            b = new Basket();
            baskets.put(sessionId,b);
        }
        b.addOne(goodId);
    }
    public void removeBasket(String sessionId)
    {
        baskets.remove(sessionId);
    }
    public Basket getBasket(String sessionId)
    {
        Basket b = baskets.get(sessionId);
        if(b==null)
        {
            return new Basket();
        }
        return b;
    }
}

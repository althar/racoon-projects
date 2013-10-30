package racoonsoft.select.structure;

import racoonsoft.library.database.DBRecord;

public class Good extends DBRecord
{
    private Integer count = 1;

    public Good(DBRecord rec)
    {
        super(rec);
    }

    public Integer getCount()
    {
        return count;
    }
    public void setCount(Integer count)
    {
        this.count = count;
    }
    public void addOne()
    {
        this.count++;
    }
    public boolean removeOne()
    {
        this.count--;
        if(count<=0)
        {
            return false;
        }
        return true;
    }
    public Double getPrice()
    {
        if(getDoubleValue("sell_price")==null)
        {
            return 0.0;
        }
        return getDoubleValue("sell_price");
    }
}

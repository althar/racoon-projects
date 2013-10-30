package racoonsoft.select.structure;
import racoonsoft.library.database.DBRecord;

import java.util.ArrayList;

public class Order extends DBRecord
{
    public Order(DBRecord rec)
    {
        super(rec);
    }
    private ArrayList<DBRecord> goods = new ArrayList<DBRecord>();

    public ArrayList<DBRecord> getGoods()
    {
        return goods;
    }
    public void setGoods(ArrayList<DBRecord> goods)
    {
        this.goods = goods;
    }

    public void addGood(DBRecord good)
    {
        goods.add(good);
    }
}

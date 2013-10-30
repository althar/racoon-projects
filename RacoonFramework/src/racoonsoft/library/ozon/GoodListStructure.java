package racoonsoft.library.ozon;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class GoodListStructure
{
    public ArrayList<Good> goods = new ArrayList<Good>();
    private Integer status = 0;
    private long lastUpdateTime;
    public long getLastUpdateTime()
    {
        return lastUpdateTime;
    }
    public GoodListStructure(HashMap<String,Object> struct)
    {
        lastUpdateTime = new Date().getTime();
        status = (Integer)struct.get("Status");
        if(!isOk())
        {
            return;
        }
        ArrayList<Object> goods = (ArrayList<Object>)(struct.get("GoodsItems"));
        for(Object good :goods)
        {
            HashMap<String,Object> categoryMap = (HashMap<String,Object>) good;
            String path = (String)categoryMap.get("Path");
            String itemType = (String)categoryMap.get("ItemType");
            String name = (String)categoryMap.get("Name");
            String annotation = (String)categoryMap.get("Annotation");
            Double price = Double.valueOf(categoryMap.get("Price").toString());
            Integer id = (Integer)categoryMap.get("Id");
            if(path==null||path.equalsIgnoreCase(""))
            {
                path = "../../img/noimage.png";
            }
            else
            {
                path = "http://static.ozone.ru/multimedia/"+path;
            }
            Good g = new Good(path,itemType,name,annotation,price,id);
            addGood(g);
        }
    }
    public boolean isOk()
    {
        return status==2;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }
    public void addGood(Good g)
    {
        goods.add(g);
    }
    public class Good
    {
        public String imageUrl;
        public String itemType;
        public String name;
        public String annotation;
        public Double price;
        public Integer id;

        public String getImageUrl() {
            return imageUrl;
        }

        public String getItemType() {
            return itemType;
        }

        public String getName() {
            return name;
        }

        public String getAnnotation() {
            return annotation;
        }

        public Double getPrice() {
            return price;
        }

        public Integer getId() {
            return id;
        }

        public Good(String imageUrl,String itemType,String name,String annotation,Double price,Integer id)
        {
            this.annotation = annotation;
            this.id = id;
            this.imageUrl = imageUrl;
            this.itemType = itemType;
            this.name = name;
            this.price = price;
        }
    }
}

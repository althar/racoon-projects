package racoonsoft.library.ozon;

import java.util.Date;
import java.util.HashMap;

public class Catalogue
{
    private long catchDuration;
    private static Catalogue instance;
    private CatalogueStructure catalogueStructure;
    private HashMap<String,GoodListStructure> goodListStructure = new HashMap<String, GoodListStructure>();

    private Catalogue(long catchDuration)
    {
        this.catchDuration = catchDuration;
    }
    public static Catalogue getInstance(long catchDuration)
    {
        if(instance==null)
        {
            instance = new Catalogue(catchDuration);
        }
        return instance;
    }
    public static Catalogue getInstance()
    {
        return getInstance(86400000);// One day.
    }
    public CatalogueStructure getCatalogueStructure(OzonProcessor proc) throws Exception
    {
        if(catalogueStructure==null||isExpired(catalogueStructure))
        {
            HashMap<String,Object> catalogueStruct = proc.getCatalogueStructure().getStructure();
            catalogueStructure = new CatalogueStructure(catalogueStruct);
        }
        return catalogueStructure;
    }
    public GoodListStructure getGoodListStructure(OzonProcessor proc,Integer categoryId,Integer pageSize,Integer page) throws Exception
    {
        GoodListStructure gls = goodListStructure.get(categoryId.toString()+"_"+pageSize.toString()+"_"+page);
        if(gls==null||isExpired(gls))
        {
            GoodListStructure result = null;
            int trys = 5;
            while((result==null||!result.isOk())&&trys>0)
            {
                HashMap<String,Object> goodsStruct = proc.getCatalogueItems(categoryId.toString(),"",pageSize.toString(),page.toString()).getStructure();
                result = new GoodListStructure(goodsStruct);
                trys--;
            }
            if(!categoryId.toString().equalsIgnoreCase("0"))
            {
                goodListStructure.put(categoryId.toString()+"_"+pageSize.toString()+"_"+page,result);
            }
            return result;
        }
        return gls;
    }
    private boolean isExpired(CatalogueStructure cat)
    {
        return (new Date().getTime()-cat.getLastUpdateTime())>catchDuration;
    }
    private boolean isExpired(GoodListStructure goodList)
    {
        return (new Date().getTime()-goodList.getLastUpdateTime())>catchDuration;
    }
}

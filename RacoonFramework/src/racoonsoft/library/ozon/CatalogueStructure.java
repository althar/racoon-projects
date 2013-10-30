package racoonsoft.library.ozon;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CatalogueStructure
{
    public ArrayList<CatalogueCategory> categories = new ArrayList<CatalogueCategory>();
    private Integer status = 0;
    private long lastUpdateTime;
    public long getLastUpdateTime()
    {
        return lastUpdateTime;
    }
    public CatalogueStructure(HashMap<String,Object> struct)
    {
        lastUpdateTime = new Date().getTime();
        status = (Integer)struct.get("Status");
        if(!isOk())
        {
            return;
        }
        ArrayList<Object> childs = (ArrayList<Object>)((HashMap<String,Object>)struct.get("WebSection")).get("Childs");
        for(Object cat:childs)
        {
            HashMap<String,Object> categoryMap = (HashMap<String,Object>)cat;
            String categoryName = categoryMap.get("DisplayName").toString();
            ArrayList<Object> subcategories = (ArrayList<Object>)categoryMap.get("Childs");
            CatalogueCategory category = new CatalogueCategory(categoryName);
            if(subcategories!=null)
            {
                for(Object scat: subcategories)
                {
                    HashMap<String,Object> subcategoryMap = (HashMap<String,Object>)scat;
                    String subcategoryName = subcategoryMap.get("DisplayName").toString();
                    Integer subcategoryId = (Integer)subcategoryMap.get("CatalogId");
                    if(subcategoryId!=0)
                    {
                        category.addSubcategory(subcategoryName,subcategoryId);
                    }
                }
            }
            addCategory(category);
        }
    }
    public boolean isOk()
    {
        return status==2;
    }
    public void addCategory(CatalogueCategory category)
    {
        categories.add(category);
    }
    public ArrayList<CatalogueCategory> getCategories()
    {
        return categories;
    }
    public class CatalogueSubcategory
    {
        public String name;
        public Integer id;

        public Integer getId()
        {
            return id;
        }
        public String getName()
        {
            return name;
        }
        public CatalogueSubcategory(String name,Integer id)
        {
            this.name = name;
            this.id = id;
        }
    }
    public class CatalogueCategory
    {
        public ArrayList<CatalogueSubcategory> subcategories = new ArrayList<CatalogueSubcategory>();
        public String name;

        public CatalogueCategory(String name)
        {
            this.name = name;
        }
        public String getName()
        {
            return name;
        }
        public void addSubcategory(String name,Integer id)
        {
            subcategories.add(new CatalogueSubcategory(name,id));
        }
        public ArrayList<CatalogueSubcategory> getSubcategories()
        {
            return subcategories;
        }
    }

}

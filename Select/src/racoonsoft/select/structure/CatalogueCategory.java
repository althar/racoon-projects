package racoonsoft.select.structure;

import java.util.ArrayList;
import java.util.HashMap;

public class CatalogueCategory
{
    private String name;
    private Long id;
    private HashMap<String,CatalogueCategory> categories = new HashMap<String, CatalogueCategory>();

    public CatalogueCategory(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public CatalogueCategory getCategory(String name)
    {
        return categories.get(name);
    }
    public void addCategory(String fullName, Long id)
    {
        String[] cats = fullName.split("\\|");
        CatalogueCategory currentCategory = this;
        for(int i=0; i<cats.length; i++)
        {
            CatalogueCategory leaf = currentCategory.getCategory(cats[i]);
            if(leaf==null)
            {
                leaf = new CatalogueCategory(cats[i],id);
            }
            currentCategory.categories.put(cats[i],leaf);
            currentCategory = leaf;
        }
    }
    public ArrayList<CatalogueCategory> getCategories()
    {
        ArrayList<CatalogueCategory> result = new ArrayList<CatalogueCategory>();
        result.addAll(categories.values());
        return result;
    }
}

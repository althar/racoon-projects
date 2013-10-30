package racoonsoft.library.ozon;

import java.util.HashMap;

public class Good
{
    private Long id;
    private String name;
    private String description;
    private Double price;
    private HashMap<String,Object> params;
    private HashMap<String,String> characteristics;

    public HashMap<String, String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(HashMap<String, String> characteristics) {
        this.characteristics = characteristics;
    }

    public Good(HashMap<String,Object> params)
    {
        HashMap<String,Object> item = (HashMap<String,Object>)params.get("Item");
        this.id = Long.valueOf(item.get("Id").toString());
        this.name = item.get("Name").toString();
        if(item.get("Annotation")!=null)
        {
            this.description = item.get("Annotation").toString();
        }
        this.price = Double.valueOf(item.get("Price").toString());
        this.params = item;
    }
    public String getImagePath()
    {
        if(params.get("Path")!=null)
        {
            return "http://static.ozone.ru/multimedia/"+params.get("Path");
        }
        return null;
    }
    public String getImageBigPath()
    {
        if(params.get("Path")!=null)
        {
            return "http://static.ozone.ru/multimedia/"+params.get("Path").toString().replace("small","c240").replace("gif","jpg");
        }
        return null;
    }
    public String getImageMediumPath()
    {
        if(params.get("Path")!=null)
        {
            return "http://static.ozone.ru/multimedia/"+params.get("Path").toString().replace("small","c120").replace("gif","jpg");
        }
        return null;
    }
    public String getParameter(String name)
    {
        if(params.get(name)!=null)
        {
            return params.get(name).toString();
        }
        return null;
    }
    public Double getPrice()
    {
        if(price==null)
        {
            return 0.0;
        }
        return price;
    }
    public void setPrice(Double price)
    {
        this.price = price;
    }
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public HashMap<String, Object> getParams()
    {
        return params;
    }
    public void setParams(HashMap<String, Object> params)
    {
        this.params = params;
    }
}

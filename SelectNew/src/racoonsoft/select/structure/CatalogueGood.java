package racoonsoft.select.structure;

import racoonsoft.library.database.DBRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class CatalogueGood extends DBRecord
{
    private HashMap<String,String> characteristics = new HashMap<String, String>();
    private String mainImageUrl;
    private ArrayList<String> images = new ArrayList<String>();

    public HashMap<String, String> getCharacteristics() {
        return characteristics;
    }

    public String getBigMainImageUrl()
    {
        return mainImageUrl.replace("128x128","256x256");
    }
    public void setCharacteristics(HashMap<String, String> characteristics) {
        this.characteristics = characteristics;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }
    public void addCharacteristic(String name,String value)
    {
        characteristics.put(name,value);
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
    public void addImage(String image)
    {
        images.add(image);
    }
}

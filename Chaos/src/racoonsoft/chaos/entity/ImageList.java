package racoonsoft.chaos.entity;

import java.util.ArrayList;

public class ImageList
{
    private ArrayList<Image> images = new ArrayList<Image>();
    public Image getImage(int i)
    {
        return images.get(i);
    }
    public void addImage(Image im)
    {
        images.add(im);
    }
    public ArrayList<Image> getImages()
    {
        return images;
    }
}

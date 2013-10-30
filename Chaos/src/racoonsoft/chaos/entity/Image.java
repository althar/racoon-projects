package racoonsoft.chaos.entity;

import javax.persistence.Entity;

@Entity
public class Image
{
    public String contentNoFormatting;
    public String unescapedUrl;
    public String titleNoFormatting;
    public String tbUrl;
    public Integer width;
    public Integer height;
    public String keyword;

    public String getKeyword()
    {
        return keyword;
    }
    public String getUnescapedUrl()
    {
        return unescapedUrl;
    }
    public String getContentNoFormatting()
    {
        return contentNoFormatting;
    }
    public String getTitleNoFormatting()
    {
        return titleNoFormatting;
    }
    public String getTbUrl()
    {
        return tbUrl;
    }
    public Integer getWidth()
    {
        return width;
    }
    public Integer getHeight()
    {
        return height;
    }
}

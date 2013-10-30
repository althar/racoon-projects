package racoonsoft.select.structure;

public class Test
{
    public static void main(String[] args)
    {
        try
        {
            CatalogueCategory cat = new CatalogueCategory("root",0l);
            cat.addCategory("Смесители|Для ванны",2l);
            cat.addCategory("Смесители",1l);
            cat.addCategory("Смесители|Для кухни",3l);
            cat.addCategory("Мойки",4l);
            cat.addCategory("Мойки|Для ванны",5l);
            cat.toString();
        }
        catch(Exception ex)
        {

        }
    }
}

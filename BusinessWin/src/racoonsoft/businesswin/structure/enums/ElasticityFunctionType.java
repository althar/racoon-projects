package racoonsoft.businesswin.structure.enums;

public enum ElasticityFunctionType
{
    CONSTANT("Постаянная")
    , LINEAR("Линейная");

    private String caption;

    private ElasticityFunctionType(String caption)
    {
        this.caption = caption;
    }

    public String getCaption()
    {
        return caption;
    }
}

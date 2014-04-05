package racoonsoft.businesswin.structure.enums;

public enum AquisitionMethod
{
    NONE("Никакого")
    , BUY_BANKRUPT("Покупка банкрота");

    private String caption;

    private AquisitionMethod(String caption)
    {
        this.caption = caption;
    }

    public String getCaption()
    {
        return caption;
    }
}

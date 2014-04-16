package racoonsoft.businesswin.structure.enums;

public enum AquisitionMethod
{
    START("Было изначально")
    , BUY_BANKRUPT("Покупка банкрота")
    , EFFICIENCY_PROJECT("ППЭ");

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

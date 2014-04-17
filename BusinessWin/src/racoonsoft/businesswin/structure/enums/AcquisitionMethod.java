package racoonsoft.businesswin.structure.enums;

public enum AcquisitionMethod
{
    START("Было изначально")
    , BUY_BANKRUPT("Покупка банкрота")
    , EFFICIENCY_PROJECT("ППЭ");

    private String caption;

    private AcquisitionMethod(String caption)
    {
        this.caption = caption;
    }

    public String getCaption()
    {
        return caption;
    }
}

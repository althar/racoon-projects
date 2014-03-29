package racoonsoft.businesswin.structure.enums;

public enum CreditRating
{
    UNKNOWN("Not specified"),
    DDD("DDD"),
    CCC("CCC"),
    BBB("BBB"),
    AAA("AAA");

    private String caption;

    private CreditRating(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}

package racoonsoft.businesswin.structure.enums;

public enum CompanySellReason
{
    FREE_WILL("По собственно воле"), BANKRUPT("Банкротство"), EVENT_CARD("Карточка - продажа предприятия");

    private String caption;

    private CompanySellReason(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}

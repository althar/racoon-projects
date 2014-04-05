package racoonsoft.businesswin.structure.enums;

public enum EventCardType
{
    CONTRACT_ENTIRE_VOLUME("Законтрактовать весь объем")
    , SELL_COMPANY("Продажа предприятия")
    , EFFICIENCY_PROJECT_SMALL("Проект повышения эффективности 1")
    , EFFICIENCY_PROJECT_MEDIUM("Проект повышения эффективности 2")
    , EFFICIENCY_PROJECT_LARGE("Проект повышения эффективности 3")
    , POCKING("Поккинг");

    private String caption;

    private EventCardType(String caption)
    {
        this.caption = caption;
    }

    public String getCaption()
    {
        return caption;
    }
}

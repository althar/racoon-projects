package racoonsoft.businesswin.game.structure.enums;

public enum EventCardType
{
    CONTRACT_ENTIRE_VOLUME("Законтрактовать весь объем")
    , INVESTMENT_PROJECT("Инвестиционный проект")
    , COST_CUTTING_PROJECT("Проект по сокращению затрат")
    , BUY_COMPANY("Купить другого игрока")
    , PRIZE("Приз");

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

package racoonsoft.businesswin.structure.enums;

public enum StatusCode
{
    SUCCESS("Успех"),
    GAME_NOT_READY("Игра не готова"),
    GAME_NOT_IN_PROGRESS("Игра не в процессе"),
    GAME_ALREADY_STARTED("Игра уже в процессе"),
    NO_SUCH_USER("Нет такого пользователя"),
    NO_SUCH_GAME("Нет такой игры"),
    NO_PERMISSIONS("Нет доступа"),
    FAILURE_PARAMETERS_WRONG("Провал. Не верные данные."),
    NO_SUCH_PLAYER("Нет такого игрока"),
    NO_SUCH_COMPANY("Нет такого компании"),
    WRONG_PHASE("Не та фаза игры"),
    TOO_MANY_PLAYERS("Слишком много игроков"),
    CONSOLIDATION_DECLINED_BANKRUPT_OR_ON_SALE("Отказ в консолидации. Продается или банкрот"),
    CONSOLIDATION_DECLINED_NO_MONEY("Отказ в консолидации. Недостаточно средств"),
    CANT_GET_CREDIT("Отказ по кредиту"),
    CANT_ACCEPT_CARD("Нельзя принять карточку"),

    WRONG_DATA_FORMAT("Неверный формат даннных");


    private String caption;

    private StatusCode(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}

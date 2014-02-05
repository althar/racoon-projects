package racoonsoft.businesswin.game.structure.enums;

public enum StatusCode
{
    SUCCESS("Успех"),
    GAME_NOT_READY("Игра не готова"),
    GAME_NOT_IN_PROGRESS("Игра не в процессе"),
    GAME_ALREADY_STARTED("Игра уже в процессе"),
    NO_SUCH_USER("Нет такого игрока"),
    NO_SUCH_GAME("Нет такой игры"),
    NO_PERMISSIONS("Нет доступа"),
    FAILURE_PARAMETERS_WRONG("Провал. Не верные данные.");

    private String caption;

    private StatusCode(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}
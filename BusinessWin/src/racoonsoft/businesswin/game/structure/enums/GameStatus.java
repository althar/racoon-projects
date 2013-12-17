package racoonsoft.businesswin.game.structure.enums;

public enum GameStatus
{
    NEW("Игра создана"), READY("Игра настроена"), IN_PROGRESS("В процессе"), FINISHED("Завершена");

    private String caption;

    private GameStatus(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}

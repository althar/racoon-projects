package racoonsoft.investmentwheel.game.structure;

public enum GameMode
{
    DEFAULT("Обычная игра"), SPECIAL("Специальная");

    private String caption;

    private GameMode(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}

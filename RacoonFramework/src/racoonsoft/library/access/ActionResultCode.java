package racoonsoft.library.access;

public enum ActionResultCode
{

    ACTION_SUCCESSFUL("Успех"),
    REGISTRATION_SUCCESSFUL("Регистрация успешна"),
    REGISTRATION_FAILED_ALREADY_EXISTS("Ошибка регистрации. Уже существует."),
    REGISTRATION_FAILED_LACK_OF_DATA("Ошибка регистрации. Недостаточно данных"),
    REGISTRATION_FAILED_ROLE_NOT_SET("Ошибка регистрации. Роль не указана (параметр role=null)"),
    REGISTRATION_FAILED_UNKNOWN("Ошибка регистрации. Причина неизвестна."),
    AUTHORIZATION_SUCCESSFUL("Авторизация успешна"),
    AUTHORIZATION_FAILED_NO_AUTHORIZATION_DATA("Ошибка авторизации. Не верные данные."),
    AUTHORIZATION_FAILED_WRONG_SESSION_ID("Ошибка авторизации. Не верный session_id."),
    AUTHORIZATION_FAILED_WRONG_LOGIN_PASSWORD("Ошибка авторизации. Не верный пароль."),
    AUTHORIZATION_FAILED_NO_SUCH_USER("Ошибка авторизации. Пользователь не существует."),
    AUTHORIZATION_FAILED_UNKNOWN("Ошибка авторизации. Причина неизвестна.");

    private String caption;

    private ActionResultCode(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}

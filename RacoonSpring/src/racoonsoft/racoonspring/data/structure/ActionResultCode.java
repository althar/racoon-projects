package racoonsoft.racoonspring.data.structure;

public enum ActionResultCode
{

    ACTION_SUCCESSFUL("Успех"),
    ACTION_FAILED("Неудача"),

    ACTION_FAILED_ALREADY_EXISTS("Неудача. Уже существует."),
    ACTION_FAILED_DOES_NOT_EXIST("Неудача. Не существует."),

    REGISTRATION_SUCCESSFUL("Регистрация успешна"),
    AUTHORIZATION_SUCCESSFUL("Авторизация успешна"),
    CONFIRMATION_SUCCESSFUL("Подтверждение успешно"),

    REGISTRATION_FAILED_ALREADY_EXISTS("Ошибка регистрации. Уже существует."),
    REGISTRATION_FAILED_LACK_OF_DATA("Ошибка регистрации. Недостаточно данных"),
    REGISTRATION_FAILED_NO_LOGIN("Ошибка регистрации. Нет логина"),
    REGISTRATION_FAILED_ROLE_NOT_SET("Ошибка регистрации. Роль не указана (параметр role=null)"),
    REGISTRATION_FAILED_UNKNOWN("Ошибка регистрации. Причина неизвестна."),
    AUTHORIZATION_FAILED_NO_AUTHORIZATION_DATA("Ошибка авторизации. Не верные данные."),
    AUTHORIZATION_FAILED_NEED_CONFIRMATION("Ошибка авторизации. Необходимо подтверждение регистрации."),
    AUTHORIZATION_FAILED_WRONG_SESSION_ID("Ошибка авторизации. Не верный session_id."),
    AUTHORIZATION_FAILED_WRONG_LOGIN_PASSWORD("Ошибка авторизации. Не верный пароль."),
    AUTHORIZATION_FAILED_NO_SUCH_USER("Ошибка авторизации. Пользователь не существует."),
    AUTHORIZATION_FAILED_UNKNOWN("Ошибка авторизации. Причина неизвестна."),
    CONFIRMATION_FAILED_UNKNOWN("Ошибка авторизации. Причина неизвестна."),
    CONFIRMATION_FAILED_WRONG_CONFIRMATION_LINK("Ошибка авторизации. Причина неизвестна.");


    private String caption;

    private ActionResultCode(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}

package racoonsoft.library.yandex.direct;

import racoonsoft.library.json.JSONProcessor;

import java.util.HashMap;

public abstract class YandexDirectPackage
{
    public abstract JSONProcessor getJsonPackage(String application_id,String login,String access_token) throws Exception;
    public JSONProcessor pack = null;
    public String login = null;
    public String application_id = null;
    public String access_token = null;
}

package racoonsoft.library.yandex.direct;

import racoonsoft.library.http.HTTPClient;
import racoonsoft.library.json.JSONProcessor;

import java.util.HashMap;

public class YandexDirectProcessor
{
    private String login;
    private String access_token;
    private String application_id;
    private Boolean sandbox;

    private String apiUrl = "https://api.direct.yandex.ru/json-api/v4/";
    private String sandboxApiUrl = "https://sandbox-api.direct.yandex.ru/json-api/v4/";
    private String currentApiUrl;

    public YandexDirectProcessor(String applicationId, String login,String accessToken,Boolean sandbox)
    {
        this.login = login;
        this.application_id = applicationId;
        this.access_token = accessToken;
        this.sandbox = sandbox;
        if(sandbox)
        {
            currentApiUrl = sandboxApiUrl;
            return;
        }
        currentApiUrl = apiUrl;
    }

    public JSONProcessor executeMethod(YandexDirectPackage request) throws Exception
    {
        JSONProcessor req = request.getJsonPackage(application_id,login,access_token);
        byte[] pack = req.toJsonString().getBytes("UTF-8");
        String result = HTTPClient.sendHTTPSRequestWithHeaders(currentApiUrl, "POST", new HashMap<String, String>(), pack);
        System.out.println(result);
        return new JSONProcessor(result);
    }

}

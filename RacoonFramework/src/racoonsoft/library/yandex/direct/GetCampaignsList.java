package racoonsoft.library.yandex.direct;

import racoonsoft.library.json.JSONProcessor;

import java.util.HashMap;

public class GetCampaignsList extends YandexDirectPackage
{

    public JSONProcessor getJsonPackage(String application_id, String login,String access_token) throws Exception
    {
        pack = new JSONProcessor(new HashMap<String, Object>());
        HashMap<String,Object> struct = pack.getStructure();
        struct.put("method","GetCampaignsList");
        struct.put("login",login);
        struct.put("token",access_token);
        struct.put("application_id", application_id);
        pack = new JSONProcessor(struct);
        return pack;
    }

}

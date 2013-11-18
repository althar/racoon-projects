package racoonsoft.library.mailru.torg;

import racoonsoft.library.http.HTTPClient;
import racoonsoft.library.json.JSONProcessor;

import java.util.HashMap;

public class ContentAPIProcessor
{
    private String authKey;
    private String apiUrl;

    public ContentAPIProcessor(String authKey,String apiUrl)
    {
        this.authKey = authKey;
        this.apiUrl = apiUrl;
    }

    public JSONProcessor request(String method,String paramString) throws Exception
    {
        HashMap<String,String> headers = new HashMap<String, String>();
        headers.put("Authorization","eY2OlZ7tCC42upkyvShmq6jj2XKQ5H");
        //String json = HTTPClient.sendHTTPRequest("","http://content.api.torg.mail.ru/1.0/regions.json");
        String json = HTTPClient.sendHTTPRequestWithHeaders("", "http://content.api.torg.mail.ru/1.0/"+method+"?"+paramString, headers);
        if(json!=null)
        {
            return new JSONProcessor(json);
        }
        return null;
    }
}

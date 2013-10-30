package ftwo.library.ozon;

import ftwo.library.http.HTTPClient;
import ftwo.library.json.JSONProcessor;
import ftwo.library.settings.Settings;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

public class APIProcessor
{
    private String OzonLogin;
    private String OzonPassword;
    private String OzoneAPIUrl;
    
    public APIProcessor(String login,String password, String url)
    {
		OzonLogin = login;
		OzonPassword = password;
		OzoneAPIUrl = url;
    }
    public APIProcessor()
    {
		if(Settings.getStringSetting("ozone_login")!=null
		&&Settings.getStringSetting("ozone_password")!=null
		&&Settings.getStringSetting("ozone_api_url")!=null)
		{
			OzonLogin = Settings.getStringSetting("ozone_login");
			OzonPassword = Settings.getStringSetting("ozone_password");
			OzoneAPIUrl = Settings.getStringSetting("ozone_api_url");
		}
    }
    public static JSONProcessor executeAPIMethod(String login,String password,String url,String method_group_name,String method_name,HashMap<String,String> parameters,boolean post) throws Exception
    {
		StringBuilder builder = new StringBuilder();
		builder.append(url);
		builder.append("/");
		builder.append(method_group_name);
		builder.append("/");
		builder.append(method_name);
		builder.append("?login=");
		builder.append(login);
		builder.append("&password=");
		builder.append(password);
		Iterator<String> paramIter = parameters.keySet().iterator();
		while(paramIter.hasNext())
		{
			String key = paramIter.next();
			String value = URLEncoder.encode(parameters.get(key),"UTF-8");
			builder.append("&");
			builder.append(key);
			builder.append("=");
			builder.append(value);
		}
		HTTPClient cl = new HTTPClient();
		String result = null;
        System.out.println("Request -> "+builder.toString());
		if(post)
		{
			result = cl.sendHTTPSRequest(builder.toString(),"POST");
		}
		else
		{
			result = cl.sendHTTPSRequest(builder.toString(),"GET");
		}
        System.out.println("Response <- "+result.toString());
		JSONProcessor proc = new JSONProcessor(result);
		return proc;
    }
    public static HashMap<String,Object> extractAPIMethod(String login,String password,String url,String method_group_name,String method_name,HashMap<String,String> parameters,boolean post) throws Exception
    {
		return executeAPIMethod(login, password, url, method_group_name,method_name, parameters,post).getStructure();
    }
    public JSONProcessor executeAPIMethod(String method_group_name,String method_name,HashMap<String,String> parameters,boolean post) throws Exception
    {
		StringBuilder builder = new StringBuilder();
		builder.append(OzoneAPIUrl);
		builder.append("/");
		builder.append(method_group_name);
		builder.append("/");
		builder.append(method_name);
		builder.append("?login=");
		builder.append(OzonLogin);
		builder.append("&password=");
		builder.append(OzonPassword);
		Iterator<String> paramIter = parameters.keySet().iterator();
		while(paramIter.hasNext())
		{
			String key = paramIter.next();
			String value = URLEncoder.encode(parameters.get(key),"UTF-8");
			builder.append("&");
			builder.append(key);
			builder.append("=");
			builder.append(value);
		}
		System.out.println("Request -> "+builder.toString());
		HTTPClient cl = new HTTPClient();
		String result = null;
		if(post)
		{
			result = cl.sendHTTPSRequestWithMethod(builder.toString(),"POST");
		}
		else
		{
			result = cl.sendHTTPSRequestWithMethod(builder.toString(),"GET");
		}
        System.out.println("Response <- "+result.toString());
		JSONProcessor proc = new JSONProcessor(result);
		return proc;
    }
    public HashMap<String,Object> extractAPIMethod(String method_group_name,String method_name,HashMap<String,String> parameters,boolean post) throws Exception
    {
		return executeAPIMethod(method_group_name,method_name, parameters,post).getStructure();
    }
    public APIRequest createRequest(String method_group_name,String method_name)
    {
		APIRequest req = new APIRequest(OzonLogin, OzonPassword, OzoneAPIUrl, method_group_name, method_name);
		return req;
    }
}


package ftwo.library.ozon;

import ftwo.library.json.JSONProcessor;
import java.util.HashMap;


public class APIRequest
{
    private String MethodName;
    private String MethodGroupName;
    private HashMap<String,String> Parameters;
    private String Login,Password,Url;

    public APIRequest(String login,String password,String url,String method_group_name,String method_name)
    {
		Login = login;
		Password = password;
		Url = url;
		MethodName = method_name;
		MethodGroupName = method_group_name;
		Parameters = new HashMap<String, String>();
    }
    public void addParameter(String name,String value)
    {
		Parameters.put(name, value);
    }
    public void removeParameter(String name)
    {
		Parameters.remove(name);
    }
    public JSONProcessor execute(boolean post) throws Exception
    {
		return APIProcessor.executeAPIMethod(Login, Password, Url, MethodGroupName,MethodName, Parameters,post);
    }
    public HashMap<String,Object> extract(boolean post) throws Exception
    {
		return APIProcessor.extractAPIMethod(Login, Password, Url, MethodGroupName,MethodName, Parameters,post);
    }
    
}

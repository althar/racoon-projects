package racoonsoft.circos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.circos.structure.RegistrationStructure;
import racoonsoft.racoonspring.access.AccessProcessor;
import racoonsoft.racoonspring.data.database.DatabaseProcessor;
import racoonsoft.racoonspring.data.json.JSONProcessor;
import racoonsoft.racoonspring.data.structure.ActionResult;
import racoonsoft.racoonspring.data.structure.ActionResultCode;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;
import racoonsoft.racoonspring.helper.StringHelper;
import racoonsoft.racoonspring.http.HTTPClient;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class VkontakteService
{
    @Autowired
    DatabaseProcessor dbProc;

    @Autowired
    AccessProcessor access;

    public ActionResult authOrRegister(String vk_id,String access_token) throws Exception
    {
        try
        {
            ActionResult result = new ActionResult(ActionResultCode.ACTION_SUCCESSFUL);

            // Check existence
            DatabaseStructure user = dbProc.selectQuery("SELECT id,login,password FROM \"user\" WHERE vk_id=?").setParameter(1,vk_id).selectOne();
            if(user==null)
            {
                JSONProcessor vk_user_json = getUser(vk_id,access_token);
                ArrayList<HashMap<String,Object>> userInfo = (ArrayList<HashMap<String,Object>>)vk_user_json.getStructure().get("response");
                String full_name = userInfo.get(0).get("first_name").toString()+userInfo.get(0).get("last_name").toString();

                RegistrationStructure userStruct = new RegistrationStructure();
                userStruct.login = "vk_"+vk_id;
                userStruct.vk_id = vk_id;
                userStruct.name = full_name;
                userStruct.confirmed = true;
                userStruct.password = StringHelper.randomString(8);
                Long id = dbProc.insertQuery(userStruct,"\"user\"").insert();
                result.setData("login", userStruct.login);
                result.setData("password",userStruct.password);
            }
            else
            {
                result.setData("login", user.getStringValue("login"));
                result.setData("password",user.getStringValue("password"));
            }

            return result;
        }
        catch(Exception ex)
        {
            return new ActionResult(ActionResultCode.ACTION_FAILED,"exception","exception",ex);
        }
    }

    public JSONProcessor getUser(String vk_id,String access_token) throws Exception
    {
        String vk_user = HTTPClient.sendHTTPSRequest("https://api.vk.com/method/users.get?user_id="+vk_id+"&v=5.21&access_token="+access_token,"GET");
        JSONProcessor vk_user_json = new JSONProcessor(vk_user);

        return vk_user_json;
    }
}

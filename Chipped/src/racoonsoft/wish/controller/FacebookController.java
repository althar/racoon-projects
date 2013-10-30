package racoonsoft.wish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import racoonsoft.library.access.User;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.helper.Helper;
import racoonsoft.library.helper.StringHelper;
import racoonsoft.library.http.HTTPClient;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.settings.Settings;
import racoonsoft.wish.database.PGSQLDataSource;
import racoonsoft.wish.struct.FacebookFriendList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

@Controller
@RequestMapping("/facebook")
public class FacebookController
{
    @Value("${main.domain}")
    public String domain;

    @Autowired
    public PGSQLDataSource dbProc;

    @RequestMapping("/login")
    public void facebookLogin(HttpServletRequest request, HttpServletResponse response,String code) throws Exception
    {
        // ModelAndView model = new ModelAndView();
        // Facebook did not accept this user yet...
        if(code==null||code.equalsIgnoreCase(""))
        {
            String facebook_url = "https://www.facebook.com/dialog/oauth?";
            //                facebook_url+="client_id="+ Settings.getStringSetting("app_id");
            facebook_url+="client_id="+ "128491293976344";
            facebook_url+="&scope=publish_actions,friends_hometown,friends_status,friends_birthday";
            facebook_url+="&redirect_uri="+domain+ "/facebook/login";
            facebook_url+="&state="+ "DDFFRREEiiFF";
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", facebook_url);
        }
        // Facebook accepted our user
        else
        {
            // Getting token to use facebook API
            String getTokenURL  = "https://graph.facebook.com/oauth/access_token?"
                    +"client_id="+ "128491293976344"
                    +"&redirect_uri="+domain+ "/facebook/login"
                    +"&code="+code
                    +"&scope=publish_actions,friends_hometown,friends_status,friends_birthday"
                    +"&client_secret="+"fc160e2ab3496921e35fbb235254240c";
            //friends_hometown
            String result = HTTPClient.sendHTTPRequestWithMethod(getTokenURL, "GET");
            String access_token = extractParams(result).get("access_token");
            // Get user...
            String get_user_url = "https://graph.facebook.com/me?access_token="+access_token;
            String user_str = HTTPClient.sendHTTPSRequestWithMethod(get_user_url,"GET");
            JSONProcessor proc = new JSONProcessor(user_str);
            System.out.println(proc.getStructure());
            HashMap<String,Object> user = proc.getStructure();
            String facebook_id = proc.getStructure().get("id").toString();
            DBRecord rec = dbProc.getUserByFacebookId(facebook_id);
            User u = new User();
            u.setValue("facebook_access_token",access_token);
            u.setValue("facebook_id",facebook_id);
            u.setValue("first_name",user.get("first_name"));
            u.setValue("last_name",user.get("last_name"));
            u.setValue("facebook_name",user.get("name"));
            u.setValue("birthday",user.get("birthday"));
            u.setValue("facebook_code",code);
            u.setValue("facebook_link",user.get("link"));
            u.setValue("gender",user.get("gender"));
            u.setValue("facebook_photo_url","https://graph.facebook.com/"+user.get("id")+"/picture?type=small");
            if(rec==null)
            {

                Long user_id = u.createInDB(dbProc);
                u.setValue("id",user_id);
            }
            else
            {
                u.setValue("id",rec.getID());
                u.saveToDB(dbProc);
            }
            setCookie(response,"facebook_code",code,86400000);
//            return new ModelAndView("/user/main_choose");
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            //https://apps.facebook.com/f-two-apps/
            response.setHeader("Location", "http://apps.facebook.com/f-two-apps/");
            //response.setHeader("Location", "/user/main_choose");
        }
    }
    public static String getAccessToken(HttpServletRequest request,PGSQLDataSource dbProc) throws SQLException
    {
        String facebook_code = getCookie(request,"facebook_code");
        DBRecord rec = dbProc.getUserByFacebookCode(facebook_code);
        if(rec!=null)
        {
            return rec.getStringValue("facebook_access_token");
        }
        return null;
    }

    private HashMap<String,String> extractParams(String params)
    {
        String[] pairs = params.split("\\&");
        HashMap<String,String> res= new HashMap<String, String>();
        for(String pair: pairs)
        {
            String[] key_val = pair.split("\\=");
            res.put(key_val[0],key_val[1]);
        }
        return res;
    }
    public static FacebookFriendList getFriendList(String access_token) throws Exception
    {
        String friends_str = HTTPClient.sendHTTPSRequestWithMethod("https://graph.facebook.com/me/friends?locale=ru_RU&access_token="+access_token,"GET");
        JSONProcessor proc = new JSONProcessor(friends_str);
        HashMap<String,Object> struct = proc.getStructure();
        ArrayList friends_struct = (ArrayList)struct.get("data");
        FacebookFriendList list = new FacebookFriendList();
        for(Object o : friends_struct)
        {
            HashMap<String,Object> friend = (HashMap<String,Object>)o;
            String id = friend.get("id").toString();
            String name = friend.get("name").toString();
            if(name.contains("\\u"))
            {
                name = StringHelper.getCharEncodedString(friend.get("name").toString());
            }
            list.addFriend(id,name);
        }
        return list;
        //CAAB03LXvJxgBAFZCwCnZBIgkRZCJF5PpiYBvJ3ZBkEcbLYm2EjFn1UUdlkywtM005EZBicE9vkvZCNJ5J10fJXKaPeSZAitJ5rL4ynVtRQsIqbj7wWSRf6kCZC9MsZBplHAFHocRj8ZAKYEl0V8dDJ3nueTxZAAMmXiMMAZD
    }
    public static String getAvatarUrl(boolean large,String id)
    {
        String res = "https://graph.facebook.com/"+id+"/picture?type=";
        if(large)
        {
            return res+"large";
        }
        return res+"small";
    }
    public static String getCookie(HttpServletRequest request,String name)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies==null)
        {
            return null;
        }
        String sid = null;
        for(int i=0; i<cookies.length; i++)
        {
            if(cookies[i].getName().equalsIgnoreCase(name))
            {
                sid = cookies[i].getValue();
                break;
            }
        }
        return sid;
    }
    public static void setCookie(HttpServletResponse response,String name,String value,Integer duration)
    {
        String cookie = name+"="+value+"; path=/; Expires="+getCookieExpires(duration);
        response.setHeader("Set-Cookie", cookie);
    }
    public static String getCookieExpires(Integer duration)
    {
        if(Settings.getIntegerSetting("session_lifetime")!=null)
        {
            duration = Settings.getIntegerSetting("session_lifetime");
        }
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.add(Calendar.MILLISECOND, duration);
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMMM yyyy HH:mm:ss",Locale.ENGLISH);
        String exp = format.format(c.getTime())+" GMT";
        System.out.println(exp);
        return exp;
    }
    public static User getUser(HttpServletRequest request,PGSQLDataSource dbProc) throws SQLException
    {
        String code = getCookie(request,"facebook_code");
        DBRecord rec = dbProc.getUserByFacebookCode(code);
        if(rec==null)
        {
            return null;
        }
        return new User(rec);
    }
    public static void publishOnWall(String access_token,String facebook_id,String message,String link,String picture,String name,String caption) throws Exception
    {
        try
        {
            String url_str = "https://graph.facebook.com/"+facebook_id+"/feed?message=test&access_token="+access_token;
            url_str+="&message="+ URLEncoder.encode(message,"UTF-8");
            url_str+="&link="+URLEncoder.encode(link,"UTF-8");
            url_str+="&picture="+URLEncoder.encode(picture,"UTF-8");
            url_str+="&name="+URLEncoder.encode(name,"UTF-8");
            url_str+="&caption="+URLEncoder.encode(caption,"UTF-8");
            String result = HTTPClient.sendHTTPSRequestWithMethod(url_str,"POST");
        }
        catch(Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
        }
    }
}
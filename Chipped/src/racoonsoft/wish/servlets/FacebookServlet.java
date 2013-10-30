package racoonsoft.wish.servlets;

import racoonsoft.library.access.User;
import racoonsoft.library.http.HTTPClient;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.settings.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class FacebookServlet extends BaseServlet
{
    private String domain = "http://109.173.122.47:8080";
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            PrintWriter out = response.getWriter();
            String cmd = request.getParameter("cmd");
            if(cmd.equalsIgnoreCase("facebook-login"))
            {
                String code = request.getParameter("code");
                if(code==null||code.equalsIgnoreCase(""))
                {
                    String facebook_url = "https://www.facebook.com/dialog/oauth?";
    //                facebook_url+="client_id="+ Settings.getStringSetting("app_id");
                    facebook_url+="client_id="+ "128491293976344";

                    facebook_url+="&redirect_uri="+domain+ "/Facebook?cmd=facebook-login";
                    facebook_url+="&state="+ "DDFFRREEiiFF";
                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", facebook_url);
                }
                else
                {
                    String getTokenURL  = "https://graph.facebook.com/oauth/access_token?"
                            +"client_id="+ "128491293976344"
                            +"&redirect_uri="+domain+ "/Facebook?cmd=facebook-login"
                            +"&code="+code
                            +"&client_secret="+"fc160e2ab3496921e35fbb235254240c";
                    String result = HTTPClient.sendHTTPRequestWithMethod(getTokenURL,"GET");
                    String access_token = extractParams(result).get("access_token");
                    // Get user...
                    String get_user_url = "https://graph.facebook.com/me?access_token="+access_token;
                    String user_str = HTTPClient.sendHTTPSRequestWithMethod(get_user_url,"GET");
                    JSONProcessor proc = new JSONProcessor(user_str);
                    System.out.println(proc.getStructure());
                    HashMap<String,Object> user = proc.getStructure();
                    long id = Long.valueOf(proc.getStructure().get("id").toString());

//                    User u = dbProc().getUser(id);
                    User u = new User(new HashMap<String,Object>());
                    u.setValue("id",0);
                    if(u==null)
                    {
                        u.setValue("first_name",user.get("first_name"));
                        u.setValue("last_name",user.get("last_name"));
                        u.setValue("id",user.get("id"));
                        u.saveToDB(dbProc());
                        // Add user to db
                    }

                    Long session_lifetime = 864000l;
                    if(Settings.getIntegerSetting("session_lifetime")!=null)
                    {
                        session_lifetime = Settings.getIntegerSetting("session_lifetime").longValue();
                    }
                    String session_id = sessProc().createSession(u, session_lifetime).getKey();
                    String expires = getSessionExpires();
                    String cookie = "session_id="+session_id+"; path=/; Expires="+expires;
                    response.setHeader("Set-Cookie", cookie);
                    out.close();

                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", "/home.html");
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }
    public static String getSessionExpires()
    {
        Integer duration = 864000;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doRequest(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doRequest(request,response);
    }
}

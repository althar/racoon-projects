package racoonsoft.circos.controller.face;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import racoonsoft.circos.controller.CircosController;
import racoonsoft.circos.database.PostgresqlDataSource;
import racoonsoft.circos.settings.MainConfig;
import racoonsoft.circos.structure.RegistrationStructure;
import racoonsoft.racoonspring.data.json.JSONProcessor;
import racoonsoft.racoonspring.data.structure.ActionResult;
import racoonsoft.racoonspring.data.structure.ActionResultCode;
import racoonsoft.racoonspring.http.HTTPClient;
import racoonsoft.racoonspring.mail.MailMessage;
import racoonsoft.racoonspring.mvc.controller.MainController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class LoginController extends CircosController
{
    @RequestMapping("/registration")
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response,String role,RegistrationStructure reg) throws Exception
    {
        String[] roles = new String[]{};
        if(role!=null)
        {
            roles = role.split(",");
        }
        ActionResult result = accessProc.registration(request, response, reg, roles,true);
        ModelAndView model = model("main");
        if(result.getResult() != ActionResultCode.REGISTRATION_SUCCESSFUL)
        {
            model = model("access");
            if(role!=null)
            {
                model.addObject("error",result.getResult().getCaption());
            }
            model.addObject("action","registration");
            return model;
        }
        MailMessage mess = new MailMessage("dfedorovich85@gmail.com",request.getParameter("login"),"Подтвреждение регистрации","Для подтверждения регистрации пройдите по ссылке http://localhost:8080/confirm?confirmation_link="+result.getData("confirmation_link"));
        mail.send(mess);
        model = addHint(model,"Вам на почту отправлено письмо с подтверждением регистрации");
        model.addObject("action","registration");
        return model;
    }
    @RequestMapping("/confirm")
    public ModelAndView confirm(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionResult result = accessProc.confirm(request,response);
        ModelAndView model = model("main");
        if(result.getResult()!=ActionResultCode.CONFIRMATION_SUCCESSFUL)
        {
            model = addHint(model,result.getResult().getCaption());
        }
        return model;
    }
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response,String login) throws Exception
    {
        if(login!=null)
        {
            ActionResult result = accessProc.authorization(request, response,true);
            if(result.anonymous())
            {
                ModelAndView model = model("access");
                model.addObject("error",result.getResult().getCaption());
                model.addObject("action","login");
                return model;
            }
            return new ModelAndView("redirect:/");
        }
        ModelAndView model = model("access");
        model.addObject("action","login");
        return model;
    }
    @RequestMapping("/logout")
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        ActionResult result = accessProc.logout(request);
        return new ModelAndView("forward:/");
    }

    @RequestMapping("/vklogin")
    public ModelAndView vkLogin(HttpServletRequest request,HttpServletResponse response,String code) throws Exception
    {
        //ModelAndView model = model("main");
        ModelAndView model = new ModelAndView("redirect:/");

        if(code!=null)
        {
            String token_url ="https://oauth.vk.com/access_token?client_id=4378615&client_secret=cWy5iuR6UpzRmtDbKhkR&redirect_uri=http://"+ MainConfig.host+"/vklogin&code="+code;
            String token_result =  HTTPClient.sendHTTPSRequest(token_url,"GET");
            JSONProcessor json = new JSONProcessor(token_result);
            String user_id = json.getValue("user_id").toString();
            String access_token = json.getValue("access_token").toString();

            if(user_id==null)
            {
                model = addHint(model,"Сбой авторизации 'В контакте'");
                return model;
            }
            //Trying to authorize or even register
            ActionResult result = vk.authOrRegister(user_id,access_token);
            if(result.getResult() == ActionResultCode.ACTION_SUCCESSFUL)
            {
                String login = result.getData("login").toString();
                String password = result.getData("password").toString();
                accessProc.authorization(request,response,login,password,false);
            }
        }

        return model;
    }
}

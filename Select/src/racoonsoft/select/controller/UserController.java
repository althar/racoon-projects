package racoonsoft.select.controller;

import liquibase.dbdoc.AuthorWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import racoonsoft.library.annotation.Interceptors;
import racoonsoft.library.sms.SMSMessage;
import racoonsoft.library.sms.SMSProcessor;
import racoonsoft.select.database.PGSQLDataSource;
import racoonsoft.select.interceptor.AccessInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller
@RequestMapping("/user")
@Interceptors({AccessInterceptor.class})
public class UserController
{
    @Autowired
    private PGSQLDataSource dbProc;
    @Autowired
    private SMSProcessor smsProc;

    @RequestMapping("/check")
    public void registration(HttpServletResponse resp, String login) throws Exception
    {
        boolean exists = dbProc.userExists(login);
        if(exists)
        {
            resp.getWriter().print(true);
        }
        else
        {
            resp.getWriter().print(false);
        }
    }
    @RequestMapping("/authorization")
    public void authorization(HttpServletRequest req, String login, String password)
    {
        System.out.println("auth");
    }
    @RequestMapping("/password_recover")
    public void passwordRecover(HttpServletRequest req,HttpServletResponse resp, String login) throws Exception
    {
        String password = dbProc.getUserPassword(login);
        if(password!=null)
        {
            String phone = "8"+login.replace("(","").replace("-","").replace(")","");
            SMSMessage mess = new SMSMessage(phone,"Ваш пароль: "+password,"select-st");
            smsProc.sendSMS(mess);
        }
        resp.getWriter().print(true);
    }
}
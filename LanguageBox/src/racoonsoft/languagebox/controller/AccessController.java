package racoonsoft.languagebox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import racoonsoft.languagebox.interceptor.AccessInterceptor;
import racoonsoft.library.annotation.Interceptors;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/access")
@Interceptors({AccessInterceptor.class})
public class AccessController
{
    @RequestMapping("/registration")
    public void registration(HttpServletRequest req, String login, String password)
    {
        System.out.println("register");
    }
    @RequestMapping("/authorization")
    public void authorization(HttpServletRequest req, String login, String password)
    {
        System.out.println("auth");
    }
}
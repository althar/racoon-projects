package racoonsoft.languagebox.controller;

import org.springframework.stereotype.Controller;
import racoonsoft.library.access.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public abstract class LanguageBoxController
{
    public Long id(HttpServletRequest request)
    {
        return (Long)request.getAttribute("user_id");
    }
    public User user(HttpServletRequest request)
    {
        return (User)request.getAttribute("user");
    }
    public ArrayList<String> roles(HttpServletRequest request)
    {
        return (ArrayList<String>)request.getAttribute("roles");
    }
    public Boolean hasRole(HttpServletRequest request,String role)
    {
        return ((ArrayList<String>)request.getAttribute("roles")).contains(role);
    }
}

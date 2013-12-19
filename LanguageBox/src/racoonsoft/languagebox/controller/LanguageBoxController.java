package racoonsoft.languagebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.languagebox.service.*;
import racoonsoft.library.access.User;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.helper.StringHelper;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.ArrayList;

@Controller
public abstract class LanguageBoxController
{
    @Autowired
    private CourseService course;

    @Autowired
    private LibraryService library;

    @Autowired
    private MarketService market;

    @Autowired
    private StudentService student;

    @Autowired
    private ContentService content;

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
    public String school(HttpServletRequest request) throws Exception
    {
        String domain = new URL(request.getRequestURL().toString()).getHost();
        return StringHelper.getDomainByLevel(domain, 3);
    }

    public ModelAndView addNews(ModelAndView model) throws Exception
    {
        ArrayList<DBRecord> news = content.getNews();
        model.addObject("news",news);
        return model;
    }
    public ModelAndView addBoughtCourses(ModelAndView model,HttpServletRequest request) throws Exception
    {
        ArrayList<DBRecord> bought_courses = course.getBoughtCourses(id(request));
        model.addObject("bought_courses",bought_courses);
        return model;
    }

}

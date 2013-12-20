package racoonsoft.languagebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.languagebox.database.PostgresqlDataSource;
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
    protected PostgresqlDataSource dbProc;
    @Autowired
    protected CourseService course;
    @Autowired
    protected LibraryService library;
    @Autowired
    protected MarketService market;
    @Autowired
    protected ContentService content;
    @Autowired
    protected StudentService student;

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
    public ModelAndView addTeacherStudents(ModelAndView model,HttpServletRequest request) throws Exception
    {
        ArrayList<DBRecord> bought_courses = student.getTeacherStudents(id(request));
        model.addObject("teacher_students",bought_courses);
        return model;
    }
    public ModelAndView addSells(ModelAndView model,HttpServletRequest request) throws Exception
    {
        ArrayList<DBRecord> bought_courses = market.getSells(id(request));
        model.addObject("sells",bought_courses);
        return model;
    }
    public ModelAndView addAchievement(ModelAndView model,HttpServletRequest request) throws Exception
    {
        ArrayList<DBRecord> bought_courses = student.getStudentAchievements(id(request));
        model.addObject("achievements",bought_courses);
        return model;
    }
}

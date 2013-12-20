package racoonsoft.languagebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.languagebox.service.CourseService;
import racoonsoft.languagebox.service.LibraryService;
import racoonsoft.languagebox.service.MarketService;
import racoonsoft.languagebox.service.StudentService;
import racoonsoft.library.database.DBRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
@RequestMapping("/service/teacher")
public class TeacherController extends LanguageBoxController
{
    //<editor-fold desc="Page">
    @RequestMapping("")
    public ModelAndView mainPage(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("page/teacher/main");
        Long user_id = id(request);
        ArrayList<DBRecord> courses = course.getTeacherCourses(user_id);
        model = addSells(model,request);
        model.addObject("courses",courses);
        return model;
    }
    @RequestMapping("/courses")
    public ModelAndView courses(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("page/teacher/courses");
        Long user_id = id(request);

        return model;
    }
    @RequestMapping("/students")
    public ModelAndView students(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("page/teacher/students");
        Long user_id = id(request);

        return model;
    }
    @RequestMapping("/profile")
    public ModelAndView profile(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("page/teacher/profile");
        Long user_id = id(request);

        return model;
    }
    //</editor-fold>

}

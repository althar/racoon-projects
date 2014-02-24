package racoonsoft.languagebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.languagebox.service.*;
import racoonsoft.library.database.DBRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
@RequestMapping("/service/student")
public class StudentController extends LanguageBoxController
{
    @RequestMapping("")
    public ModelAndView mainPage(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("page/student/main");
        model = addUser(model,request);
        model = addBoughtCourses(model,request);
        model = addNews(model);
        model = addSells(model,request);
        model = addBoughtCourses(model,request);
        return model;
    }
    @RequestMapping("/courses")
    public ModelAndView courses(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("page/student/courses");
        Long user_id = id(request);

        return model;
    }
    @RequestMapping("/course_lesson")
    public ModelAndView courseLesson(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("page/student/course_lesson");
        Long user_id = id(request);

        return model;
    }
    @RequestMapping("/course_main_material")
    public ModelAndView courseMainMaterial(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("page/student/course_main_material");
        Long user_id = id(request);

        return model;
    }
    @RequestMapping("/course_mates")
    public ModelAndView courseMates(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("page/student/course_mates");
        Long user_id = id(request);

        return model;
    }
    @RequestMapping("/profile")
    public ModelAndView profile(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("page/student/profile");
        Long user_id = id(request);

        return model;
    }

}

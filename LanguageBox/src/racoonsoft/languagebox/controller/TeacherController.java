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
import racoonsoft.library.database.DBRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
@RequestMapping("/service/teacher")
public class TeacherController extends LanguageBoxController
{
    @Autowired
    private PostgresqlDataSource dbProc;

    @Autowired
    private CourseService course;

    @Autowired
    private LibraryService library;

    @Autowired
    private MarketService market;

    //<editor-fold desc="Main page">
    @RequestMapping("/main")
    public ModelAndView mainPage(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("page/teacher/main");
        Long user_id = id(request);
        ArrayList<DBRecord> courses = course.getTeacherCourses(user_id);
        ArrayList<DBRecord> sells = market.getTeacherSells(user_id);
        model.addObject("courses",courses);
        model.addObject("sells",sells);
        return model;
    }
    //</editor-fold>

}

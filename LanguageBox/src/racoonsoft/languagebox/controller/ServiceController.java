package racoonsoft.languagebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.languagebox.service.*;
import racoonsoft.library.database.DBRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
@RequestMapping("/service")
public class ServiceController extends LanguageBoxController
{
    @Autowired
    private ContentService content;
    @Autowired
    private CourseService course;
    @Autowired
    private LibraryService library;
    @Autowired
    private MarketService market;
    @Autowired
    private StudentService student;

    @RequestMapping("/news")
    public ModelAndView news(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("section/news");
        ArrayList<DBRecord> news = content.getNews();
        model.addObject("news",news);
        return model;
    }
    @RequestMapping("/sells")
    public ModelAndView sells(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("section/sells");
        ArrayList<DBRecord> sells = market.getSells(id(request));
        model.addObject("sells",sells);
        return model;
    }
    @RequestMapping("/tutorial")
    public ModelAndView tutorial(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("section/tutorial");
        return model;
    }
    @RequestMapping("/students")
    public ModelAndView students(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("section/students");

        return model;
    }
}

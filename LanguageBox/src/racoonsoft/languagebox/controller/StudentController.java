package racoonsoft.languagebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.languagebox.service.CourseService;
import racoonsoft.languagebox.service.LibraryService;
import racoonsoft.languagebox.service.MarketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/service/student")
public class StudentController
{
    @Autowired
    private PostgresqlDataSource dbProc;
    @Autowired
    private CourseService course;
    @Autowired
    private LibraryService library;
    @Autowired
    private MarketService market;

    @RequestMapping("/student")
    public ModelAndView info(HttpServletRequest request, HttpServletResponse response, String login)
    {
        return new ModelAndView("page/info");
    }

}

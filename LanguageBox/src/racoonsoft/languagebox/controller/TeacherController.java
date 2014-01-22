package racoonsoft.languagebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.languagebox.service.CourseService;
import racoonsoft.languagebox.service.LibraryService;
import racoonsoft.languagebox.service.MarketService;
import racoonsoft.languagebox.service.StudentService;
import racoonsoft.languagebox.structure.MultipartFilesStructure;
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
    @RequestMapping("/get_folder")
    public ModelAndView getFolder(HttpServletRequest request, HttpServletResponse response, Long folder_id) throws Exception
    {
        //Thread.sleep(2000);
        ModelAndView model = new ModelAndView("section/courses/folder_body");
        DBRecord folder = library.getFolder(folder_id,id(request));
        model.addObject("folder",folder);
        return model;
    }
    @RequestMapping("/create_folder")
    public ModelAndView createFolder(HttpServletRequest request, HttpServletResponse response, Long folder_id,String folder_name) throws Exception
    {
        //Thread.sleep(2000);
        library.addFolder(folder_id,folder_name,id(request));
        ModelAndView model = new ModelAndView("section/courses/folder_body");
        return model;
    }
    @RequestMapping("/upload_files")
    public ModelAndView uploadFiles(@ModelAttribute("uploadForm")MultipartFilesStructure uploadForm,
                                    Model map) throws Exception
    {
        String str = "";
        return new ModelAndView("");
    }
    @RequestMapping("/delete")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, Long id,String type) throws Exception
    {
        ModelAndView model = new ModelAndView("document/plain");
        model.addObject("value","ok");
        return model;
    }
    @RequestMapping("/rename")
    public ModelAndView rename(HttpServletRequest request, HttpServletResponse response, Long id,String type,String name) throws Exception
    {
        ModelAndView model = new ModelAndView("document/plain");
        model.addObject("value","ok");
        return model;
    }

    //</editor-fold>

}

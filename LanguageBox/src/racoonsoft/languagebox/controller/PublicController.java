package racoonsoft.languagebox.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

@Controller
@RequestMapping("")
public class PublicController extends LanguageBoxController
{
    @RequestMapping("")
    public ModelAndView main()
    {
        return new ModelAndView("page/public/main");
    }
    @RequestMapping("/profile")
    public ModelAndView userProfile(HttpServletRequest request) throws Exception
    {
        ModelAndView model = new ModelAndView("page/public/profile");
        model = addRoles(model,request);
        return model;
    }
    @RequestMapping("/teacher_review")
    public ModelAndView teacherReview(HttpServletRequest request) throws Exception
    {
        ModelAndView model = new ModelAndView("page/public/teacher_review");
        model = addRoles(model,request);
        return model;
    }
    @RequestMapping("/course_review")
    public ModelAndView courseReview(HttpServletRequest request) throws Exception
    {
        ModelAndView model = new ModelAndView("page/public/course_review");
        model = addRoles(model,request);
        return model;
    }
    @RequestMapping("/market")
    public ModelAndView market(HttpServletRequest request) throws Exception
    {
        ModelAndView model = new ModelAndView("page/public/market");
        model = addRoles(model,request);
        return model;
    }

//    @RequestMapping("/course_image/{path}")
//    @ResponseBody
//    public byte[] downloadCourseImage(HttpServletRequest request,HttpServletResponse response, String path) throws Exception
//    {
//        return new FileSystemResource("C:/temp/courses/"+path).getFile();
//    }
}

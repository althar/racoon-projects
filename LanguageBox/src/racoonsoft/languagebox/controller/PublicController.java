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
public class PublicController
{
    @RequestMapping("")
    public ModelAndView main()
    {
        return new ModelAndView("page/public/main");
    }
    @RequestMapping("/functions")
    public ModelAndView functions()
    {
        return new ModelAndView("page/public/functions");
    }
    @RequestMapping("/price")
    public ModelAndView price()
    {
        return new ModelAndView("page/public/price");
    }
    @RequestMapping("/about")
    public ModelAndView about()
    {
        return new ModelAndView("page/public/about");
    }
    @RequestMapping("/faq")
    public ModelAndView faq()
    {
        return new ModelAndView("page/public/faq");
    }
    @RequestMapping("/partners")
    public ModelAndView partners()
    {
        return new ModelAndView("page/public/partners");
    }
    @RequestMapping("/user_profile")
    public ModelAndView userProfile()
    {
        return new ModelAndView("page/public/user_profile");
    }
    @RequestMapping("/teacher_review")
    public ModelAndView teacherReview()
    {
        return new ModelAndView("page/public/teacher_review");
    }
    @RequestMapping("/market")
    public ModelAndView market()
    {
        return new ModelAndView("page/public/market");
    }

//    @RequestMapping("/course_image/{path}")
//    @ResponseBody
//    public byte[] downloadCourseImage(HttpServletRequest request,HttpServletResponse response, String path) throws Exception
//    {
//        return new FileSystemResource("C:/temp/courses/"+path).getFile();
//    }
}

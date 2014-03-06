package racoonsoft.languagebox.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

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
    @RequestMapping("/teacher_profile")
    public ModelAndView teacherProfile(HttpServletRequest request) throws Exception
    {
        ModelAndView model = new ModelAndView("page/public/domain3");
        model = addRoles(model,request);
        return model;
    }
    @RequestMapping(value = "/get_image/{path}",produces = "image/jpeg")
    public @ResponseBody byte[] downloadImage(WebRequest request, @PathVariable("path")Long path,HttpServletResponse response) throws Exception
    {
        if (request.checkNotModified(0))
        {
            return null;
        }
        return uploader.getImage(path);
    }
}

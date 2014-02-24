package racoonsoft.languagebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.languagebox.service.*;
import racoonsoft.languagebox.structure.MultipartFilesStructure;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.helper.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

@Controller
@RequestMapping("/service")
public class ServiceController extends LanguageBoxController
{
    //<editor-fold desc="Static sections">
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
    //</editor-fold>


    //<editor-fold desc="Library">
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
                                    Model map,Long folder_id,HttpServletRequest request) throws Exception
    {
        uploader.addFilesToUpload(id(request),folder_id,uploadForm.getFiles());
        return new ModelAndView("document/plain");
    }
    @RequestMapping("/delete")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, Long id,String type) throws Exception
    {
        library.delete(type,id,id(request));
        ModelAndView model = new ModelAndView("document/plain");
        model.addObject("value","ok");
        return model;
    }
    @RequestMapping("/rename")
    public ModelAndView rename(HttpServletRequest request, HttpServletResponse response, Long id,String type,String name) throws Exception
    {
        library.rename(type, id, id(request), name);
        ModelAndView model = new ModelAndView("document/plain");
        model.addObject("value","ok");
        return model;
    }
    @RequestMapping("/download_material")
    public void downloadMaterial(HttpServletRequest request,HttpServletResponse response, Long id) throws Exception
    {
        DBRecord material = library.getMaterial(id(request), id);
        if(material!=null)
        {
            response.setContentType("application/doc");
            String fileName = StringHelper.getContentDespositionFilename(null, material.getStringValue("name"));
            response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
            response.setCharacterEncoding("UTF-8");
            BufferedInputStream stream = library.getMaterialStream(id);
            long size = material.getLongValue("size");
            long read=0;
            int b=0;
            int i=0;
            while((b=stream.read())!=-1)
            {
                i++;
//                int b = stream.read();
                response.getOutputStream().write(b);
                if(i>1000000)
                {
                    i=0;
                    response.getOutputStream().flush();
                }

            }
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }
    @RequestMapping("/upload_progress")
    public ModelAndView uploadProgress(HttpServletRequest request, Long folder_id) throws Exception
    {
        DBRecord rec = uploader.getFolderUploadInfo(id(request),folder_id);
        ModelAndView model = new ModelAndView("document/plain");
        model.addObject("value",rec.toXML());
        return model;
    }
    //</editor-fold>

    //<editor-fold desc="Courses">

    @RequestMapping("/get_courses")
    public ModelAndView getCourses(HttpServletRequest request, HttpServletResponse response, Long folder_id) throws Exception
    {
        ModelAndView model = new ModelAndView("section/courses/courses");
        ArrayList<DBRecord> courses = course.getTeacherCourses(id(request));
        model.addObject("courses",courses);

        return model;
    }
    @RequestMapping("/get_course")
    public ModelAndView getCourse(HttpServletRequest request, HttpServletResponse response, Long course_id) throws Exception
    {
        ModelAndView model = new ModelAndView("section/courses/courses_new");
        if(course_id!=null)
        {
            DBRecord c = course.getCourse(id(request),course_id,false);
            model.addObject("course",c);
        }

        return model;
    }

    @RequestMapping("/save_course")
    public ModelAndView saveCourse(HttpServletRequest request, HttpServletResponse response,Long course_id, String name, String lvl,String sale,String target,String type,Double price,String description) throws Exception
    {
        ModelAndView model = new ModelAndView("document/plain");
        Long id = 0l;
        if(course_id!=null)
        {
            id = course.updateCourse(course_id,id(request),name,lvl,true,price,description,target,type);
        }
        else
        {
            id = course.createCourse(id(request),name,lvl,true,price,description,target,type);
        }
        model.addObject("value",id);
        return model;
    }
    @RequestMapping("/get_course_lessons")
    public ModelAndView getCourseLessons(HttpServletRequest request, HttpServletResponse response,Long course_id) throws Exception
    {
        ModelAndView model = new ModelAndView("section/courses/courses_show");
        if(course_id!=null)
        {
            DBRecord c = course.getCourse(id(request),course_id,true);
            model.addObject("course",c);
        }

        return model;
    }
    @RequestMapping("/get_lesson")
    public ModelAndView getLesson(HttpServletRequest request, Long course_id, Long lesson_id,Boolean main_material) throws Exception
    {
        ModelAndView model = new ModelAndView("section/courses/courses_lessons_show");
        DBRecord c = course.getCourse(id(request),course_id,true);
        DBRecord lesson = course.getLesson(id(request),course_id,lesson_id);
        model.addObject("course",c);
        model.addObject("lesson",lesson);
        model.addObject("main_material",main_material);

        return model;
    }
    //</editor-fold>
}

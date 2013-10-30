package racoonsoft.chaos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import racoonsoft.chaos.dao.BookDao;

import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;


@Controller
public class FileUpload
{
    public static final int UPLOAD_RESULT_OK = 100000;
    @Autowired
    BookDao book_dao;

    @RequestMapping(value = "/admin/library/upload_file", method = RequestMethod.POST)
    public String saveFiles(MultipartFile file) throws IOException
    {
        if (!file.isEmpty())
        {
            byte[] bytes = file.getBytes();
            return "redirect:caps/total_fail";
        }
        else
        {
            return "redirect:caps/total_fail";
        }
    }
}

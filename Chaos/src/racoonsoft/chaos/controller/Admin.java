package racoonsoft.chaos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.chaos.dao.BookDao;
import racoonsoft.chaos.dao.ImageDao;
import racoonsoft.chaos.entity.ImageList;
import racoonsoft.chaos.service.GoogleImageService;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.processors.FileTransferProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class Admin
{
    @Autowired
    BookDao book_dao;

    @Autowired
    ImageDao image_dao;

    @Autowired
    GoogleImageService google;

    @Autowired
    FileTransferProcessor fileTransferProcessor;

    @RequestMapping("/login")
    public ModelAndView adminLogin()
    {
        ModelAndView model = new ModelAndView("admin_login");
        model.addObject("msg", "hello world");
        model.setViewName("admin_login");
        return model;
    }
    @RequestMapping("/library")
    public ModelAndView adminLibrary()
    {
        ModelAndView model = new ModelAndView("library");
        model.addObject("msg", "hello world");
        return model;
    }
    @RequestMapping("/library/get_books")
    public ModelAndView getBook(HttpServletResponse resp) throws Exception
    {
        fileTransferProcessor.start();
        ArrayList<DBRecord> books = book_dao.getBooks();
        ModelAndView model = new ModelAndView("books");
        model.addObject("books", books);
        model.setViewName("books");
        return model;
    }
    @RequestMapping("/library/save_book")
    public void saveBook(HttpServletResponse resp, String name, String type, String genre, String author, Long id) throws IOException, SQLException
    {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        Long book_id = book_dao.saveBook(id, name, type, genre, author);
        writer.println(book_id);
    }
    @RequestMapping("/library/save_chapter")
    public ModelAndView saveChapter(HttpServletResponse resp, String name, String keywords, String text, Long book_id, String book_name) throws Exception
    {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        Long chapter_id = book_dao.saveChapter(book_id, name, keywords, text, book_name);
        String[] recs = book_dao.getKeywordsByChapterId(chapter_id);
        ImageList list = google.getImages(recs);
        ModelAndView model = new ModelAndView("sections/image_select");
        model.addObject("chapter_id", chapter_id);
        model.addObject("imageList", list);
        return model;
    }
    @RequestMapping("/library/save_chapter_image")
    public void saveChapterImage(HttpServletResponse resp, HttpServletRequest req, String unescaped_url, String content_no_formatting, String tb_url, String keyword, Integer height, Integer width, String title_no_formatting) throws Exception
    {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        Long image_id = book_dao.saveImage(unescaped_url, content_no_formatting, tb_url, keyword, height, width, title_no_formatting);
        image_dao.setImagePath(req.getSession().getServletContext().getRealPath("/img"));
        image_dao.start();
        writer.println();
    }
}

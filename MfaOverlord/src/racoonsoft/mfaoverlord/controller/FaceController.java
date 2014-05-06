package racoonsoft.mfaoverlord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.mfaoverlord.service.ContentProcessor;
import racoonsoft.mfaoverlord.service.ContentService;
import racoonsoft.mfaoverlord.service.SearchService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FaceController
{
    @Autowired
    private SearchService search;

    @Autowired
    private ContentService content;

    @RequestMapping("/")
    public ModelAndView main(HttpServletRequest request,HttpServletRequest response) throws Exception
    {
        ModelAndView model = model("main");
        search.start();
        content.start();
        return model;
    }

    public ModelAndView model(String page)
    {
        ModelAndView model = new ModelAndView("structure/page");
        model.addObject("page",page);
        return model;
    }
}

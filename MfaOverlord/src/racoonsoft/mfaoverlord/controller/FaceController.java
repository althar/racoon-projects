package racoonsoft.mfaoverlord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.mfaoverlord.service.ContentProcessor;
import racoonsoft.mfaoverlord.service.ContentService;
import racoonsoft.mfaoverlord.service.DataService;
import racoonsoft.mfaoverlord.service.SearchService;
import racoonsoft.racoonspring.data.database.DatabaseProcessor;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;
import racoonsoft.racoonspring.mvc.controller.MainController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class FaceController extends MainController
{
    @Autowired
    private SearchService search;

    @Autowired
    private ContentService content;

    @Autowired
    private DataService data;

    @RequestMapping("/googlec96235edfde6b9c6.html")
    public ModelAndView googleConfirm()
    {
        return new ModelAndView("googlec96235edfde6b9c6");
    }
    @RequestMapping("/")
    public ModelAndView main(HttpServletRequest request,HttpServletRequest response,Long semantics_id,Long search_id,Long content_id) throws Exception
    {
        ModelAndView model = model("main",request);
        search.start();
        content.start();
        model = fill(model,semantics_id,search_id, content_id,request);

        return model;
    }
    public ModelAndView fill(ModelAndView model,Long semantics_id,Long search_id,Long content_id,HttpServletRequest request) throws Exception
    {
        DatabaseStructure current_content = null;
        DatabaseStructure current_semantics = null;
        DatabaseStructure current_search_item = null;

        ArrayList<DatabaseStructure> content = null;
        ArrayList<DatabaseStructure> related_semantics = null;
        ArrayList<DatabaseStructure> search_items = null;

        String domain = domain(request);
        if(content_id!=null)
        {
            current_content = dbProc.selectQuery("SELECT * FROM content WHERE id="+content_id+" LIMIT 1").selectOne();
            current_semantics = dbProc.selectQuery("SELECT * FROM semantics WHERE id="+current_content.getLongValue("semantics_id")+" LIMIT 1").selectOne();
            current_search_item = dbProc.selectQuery("SELECT * FROM search_item WHERE id="+current_content.getLongValue("search_item_id")+" LIMIT 1").selectOne();

            if(current_search_item!=null)
            {
                content = dbProc.selectQuery("SELECT * FROM content WHERE search_item_id="+current_search_item.getLongValue("id")+" LIMIT 100").select();
            }
            else
            {
                content = dbProc.selectQuery("SELECT * FROM content OFFSET random()*(SELECT count(id) FROM content) LIMIT 100").select();
            }
            related_semantics = dbProc.selectQuery("SELECT * FROM semantics OFFSET random()*(SELECT count(id) FROM semantics) LIMIT 30").select();
            if(current_semantics!=null)
            {
                search_items = dbProc.selectQuery("SELECT * FROM search_item WHERE semantics_id="+current_semantics.getLongValue("id")+" LIMIT 30").select();
            }
            else
            {
                search_items = dbProc.selectQuery("SELECT * FROM search_item OFFSET random()*(SELECT count(id) FROM search_item) LIMIT 30").select();
            }
        }
        else if(search_id!=null)
        {
            current_search_item = dbProc.selectQuery("SELECT * FROM search_item WHERE id="+search_id+" LIMIT 1").selectOne();
            current_content = dbProc.selectQuery("SELECT * FROM content WHERE search_item_id="+current_search_item.getLongValue("id")+" LIMIT 1").selectOne();
            current_semantics = dbProc.selectQuery("SELECT * FROM semantics WHERE id="+current_search_item.getLongValue("semantics_id")+" LIMIT 1").selectOne();


            content = dbProc.selectQuery("SELECT * FROM content WHERE search_item_id="+current_search_item.getLongValue("id")+" LIMIT 100").select();
            if(current_content!=null)
            {
                related_semantics = dbProc.selectQuery("SELECT * FROM semantics WHERE id="+current_content.getLongValue("semantics_id")+" LIMIT 30").select();
                search_items = dbProc.selectQuery("SELECT * FROM search_item WHERE id="+current_content.getLongValue("search_item_id")+" LIMIT 30").select();
            }
            else
            {
                related_semantics = dbProc.selectQuery("SELECT * FROM semantics OFFSET random()*(SELECT count(id) FROM semantics) LIMIT 30").select();
                search_items = dbProc.selectQuery("SELECT * FROM search_item OFFSET random()*(SELECT count(id) FROM search_item) LIMIT 30").select();
            }

        }
        else if(semantics_id!=null)
        {
            current_semantics = dbProc.selectQuery("SELECT * FROM semantics WHERE id="+semantics_id+" LIMIT 1").selectOne();
            current_search_item = dbProc.selectQuery("SELECT * FROM search_item WHERE semantics_id="+semantics_id+" LIMIT 1").selectOne();
            current_content = dbProc.selectQuery("SELECT * FROM content WHERE semantics_id="+semantics_id+" LIMIT 1").selectOne();
            if(current_content==null)
            {
                current_content = dbProc.selectQuery("SELECT * FROM content OFFSET random()*(SELECT count(id) FROM content) LIMIT 1").selectOne();
            }


            content = dbProc.selectQuery("SELECT * FROM content WHERE semantics_id="+semantics_id+" LIMIT 100").select();
            if(current_semantics!=null)
            {
                related_semantics = dbProc.selectQuery("SELECT * FROM semantics WHERE domain='"+current_semantics.getStringValue("domain")+"' LIMIT 10").select();
            }
            else
            {
                related_semantics = dbProc.selectQuery("SELECT * FROM semantics OFFSET random()*(SELECT count(id) FROM semantics) LIMIT 30").select();
            }
            if(current_content!=null)
            {
                search_items = dbProc.selectQuery("SELECT * FROM search_item WHERE semantics_id="+semantics_id+" LIMIT 30").select();
            }
            else
            {
                search_items = dbProc.selectQuery("SELECT * FROM search_item OFFSET random()*(SELECT count(id) FROM search_item) LIMIT 30").select();
            }
        }
        else
        {
            current_semantics = dbProc.selectQuery("SELECT * FROM semantics LIMIT 1").selectOne();
            current_search_item = dbProc.selectQuery("SELECT * FROM search_item WHERE semantics_id="+current_semantics.getLongValue("id")+" LIMIT 1").selectOne();
            current_content = dbProc.selectQuery("SELECT * FROM content WHERE semantics_id="+current_semantics.getLongValue("id")+" LIMIT 1").selectOne();


            content = dbProc.selectQuery("SELECT * FROM content WHERE semantics_id="+current_semantics.getLongValue("id")+" LIMIT 100").select();
            related_semantics = dbProc.selectQuery("SELECT * FROM semantics LIMIT 10").select();
            search_items = dbProc.selectQuery("SELECT * FROM search_item WHERE semantics_id="+current_semantics.getLongValue("id")+" LIMIT 30").select();
        }
        model.addObject("current_content",current_content);
        model.addObject("current_semantics",current_semantics);
        model.addObject("current_search_item",current_search_item);
        model.addObject("content",content);
        model.addObject("related_semantics",related_semantics);
        model.addObject("search_items",search_items);
        model.addObject("title",current_semantics.getValue("category"));
        return model;
    }
    public ModelAndView model(String page,HttpServletRequest request) throws Exception
    {
        ModelAndView model = new ModelAndView("structure/page");
        model.addObject("page",page);
        ArrayList<DatabaseStructure> semantics = dbProc.selectQuery("SELECT * FROM semantics WHERE domain='" + domain(request) + "'").select();
        model.addObject("semantics",semantics);
        return model;
    }
}

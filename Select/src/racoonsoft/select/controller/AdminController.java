package racoonsoft.select.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.settings.Settings;
import racoonsoft.select.database.PGSQLDataSource;
import racoonsoft.select.service.AerseService;
import racoonsoft.select.service.GoodFindService;

import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    private AerseService aerse;

    @Autowired
    private PGSQLDataSource dbProc;

    @Autowired
    private GoodFindService goodFindService;
    @RequestMapping("/order")
    public ModelAndView getOrder(String login,String password, Long id) throws Exception
    {
        if(login!=null&&login.equalsIgnoreCase("racoon")&&password!=null&&password.equalsIgnoreCase("racoonracoon2000"))
        {
            ModelAndView model = new ModelAndView("/admin/order");
            model.addObject("order_id",id);
            model.addObject("order",dbProc.getOrder(id));
            return model;
        }
        return null;
    }
    @RequestMapping("/goods_extract")
    public ModelAndView goodsExtract(String brand,String article,String cmd,String name,String value,String url) throws Exception
    {
        ModelAndView model = new ModelAndView("/admin/goods_extract");
        if(cmd==null)
        {
            return model;
        }
        if(cmd.equalsIgnoreCase("delete_characteristics"))
        {
            dbProc.deleteCharacteristics(brand,article);
        }
        else if(cmd.equalsIgnoreCase("add_image"))
        {
            dbProc.addImage(brand,article,url);
        }
        else if(cmd.equalsIgnoreCase("extract_characteristics"))
        {
            dbProc.addCharacteristics(brand,article,name,value);
        }
        return model;
    }

    @RequestMapping("/extract_characteristics")
    public ModelAndView extractCharacteristics() throws SQLException
    {
        ActionResult res = aerse.extractCharacteristics();
        ModelAndView model = new ModelAndView("/admin/aerse_report");
        model.addObject("importUnit","характеристик");
        model.addObject("successCount",res.getData("successCount").toString());
        model.addObject("failureCount",res.getData("failureCount").toString());
        model.addObject("goods",(ArrayList<String>)res.getData("failureGoods"));
        return model;
    }
    @RequestMapping("/extract_images")
    public ModelAndView extractImages() throws SQLException
    {
        ActionResult res = aerse.extractImages();
        ModelAndView model = new ModelAndView("/admin/aerse_report");
        model.addObject("importUnit","картинок");
        model.addObject("successCount",res.getData("successCount").toString());
        model.addObject("failureCount",res.getData("failureCount").toString());
        model.addObject("goods",(ArrayList<String>)res.getData("failureGoods"));
        return model;
    }
    @RequestMapping("/find_on_moyki.ru")
    public ModelAndView findOnMoykiRu() throws Exception
    {
        ActionResult res = goodFindService.extractGoodsMoykiRu();
        ModelAndView model = new ModelAndView("/admin/aerse_report");
        model.addObject("importUnit","картинок");
        model.addObject("successCount",res.getData("successCount").toString());
        model.addObject("failureCount","0");
//        model.addObject("goods",(ArrayList<String>)res.getData("failureGoods"));
        return model;
    }

}

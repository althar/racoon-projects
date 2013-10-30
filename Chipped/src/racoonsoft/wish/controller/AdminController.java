package racoonsoft.wish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.ozon.OzonProcessor;
import racoonsoft.wish.database.PGSQLDataSource;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    private OzonProcessor ozonProcessor;

    @Autowired
    private PGSQLDataSource dbProc;


    @RequestMapping("/login")
    public ModelAndView adminLogin() {
        ModelAndView model = new ModelAndView("admin/login");
        model.addObject("msg", "hello world");
        model.setViewName("admin_login");
        return model;
    }
    @RequestMapping("/transactions")
    public ModelAndView adminTransactions(Long id,Long userId) throws Exception
    {
        ModelAndView model = new ModelAndView("admin/transactions");
        String query = "SELECT t.*,u.facebook_name AS receiver, ts.name AS status_name FROM transactions t, transaction_status ts, users u WHERE t.for_user_id=u.id AND ts.id=t.status";
        if(id!=null)
        {
            query+=" AND t.id="+id;
        }
        if(userId!=null)
        {
            query+=" AND t.user_id="+userId;
        }
        ArrayList<DBRecord> transactions = dbProc.getRecords(query);
        model.addObject("transactions",transactions);

        return model;
    }
    @RequestMapping("/orders")
    public ModelAndView adminOrders(Long id,Long userId) throws Exception
    {
        ModelAndView model = new ModelAndView("admin/orders");
        String query = "SELECT wl.id, wl.user_id, wl.good_id, wl.price, ws.name AS status, wl.created, wl.reason, wl.good_name,u.facebook_name AS name FROM wish_list wl, wishlist_status ws, users u WHERE wl.status=ws.id AND u.id=wl.user_id";
        if(id!=null)
        {
            query+=" AND wl.id="+id;
        }
        if(userId!=null)
        {
            query+=" AND wl.user_id="+userId;
        }
        ArrayList<DBRecord> users = dbProc.getRecords(query);
        model.addObject("orders",users);

        return model;
    }
    @RequestMapping("/users")
    public ModelAndView adminUsers(String id, String facebookId) throws Exception
    {
        ModelAndView model = new ModelAndView("admin/users");
        String query = "SELECT id, facebook_id, facebook_name, created, amount FROM users WHERE id>0 ";
        try
        {
            Long.valueOf(id);
            query+=" AND id="+id;
        }
        catch(NumberFormatException nfe)
        {}
        if(facebookId!=null&&!facebookId.equalsIgnoreCase(""))
        {
            query+=" AND facebook_id='"+facebookId+"'";
        }
        query+=" ORDER BY amount";
        ArrayList<DBRecord> users = dbProc.getRecords(query);
        model.addObject("users",users);
        return model;
    }
    @RequestMapping("/certificate")
    public ModelAndView adminCertificate(String cert) throws Exception
    {
        ModelAndView model = new ModelAndView("admin/main");
        JSONProcessor certJson = ozonProcessor.activateCard("666",cert);
        HashMap cMap = (HashMap)certJson.getStructure().get("DCode");
        String message = (String)cMap.get("Message");
        Double resultValue = Double.valueOf(cMap.get("ResultValue").toString());
        Double discountValue = Double.valueOf(cMap.get("DiscountValue").toString());
        JSONProcessor userJson = ozonProcessor.getUserInfo("666");
        HashMap uMap = (HashMap)userJson.getStructure().get("ClientAccountEntryInformationForWeb");
        Integer value = (Integer)uMap.get("Current");
        model.addObject("balance",value);
        model.addObject("activationResult",message+"(Номинал:"+resultValue+", дискаунт: "+discountValue+")");
        model.addObject("resultValue",resultValue);
        model.addObject("discountValue",discountValue);

        return model;
    }
    @RequestMapping("/")
    public ModelAndView adminMain() throws Exception
    {
        ModelAndView model = new ModelAndView("admin/main");
        JSONProcessor userJson = ozonProcessor.getUserInfo("666");
        HashMap uMap = (HashMap)userJson.getStructure().get("ClientAccountEntryInformationForWeb");
        Double value = Double.valueOf(uMap.get("Current").toString());
        model.addObject("balance",value);
        return model;
    }
}

package racoonsoft.businesswin.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.businesswin.database.PostgresqlDataSource;
import racoonsoft.businesswin.structure.model.GoodsDeclaration;
import racoonsoft.businesswin.structure.data.StartSettings;
import racoonsoft.businesswin.structure.data.TradeFactors;
import racoonsoft.businesswin.structure.model.Player;
import racoonsoft.businesswin.structure.enums.GameMode;
import racoonsoft.businesswin.structure.enums.StatusCode;
import racoonsoft.library.access.User;
import racoonsoft.library.json.JSONProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/api")
public class GameController extends BusinessWinController
{
    @Autowired
    private PostgresqlDataSource dbProc;

    //<editor-fold desc="General">
    @RequestMapping("/games")
    public ModelAndView games(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("games", gameService.getGames());
        JSONProcessor json = new JSONProcessor(jsonMap);

        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/get_game")
    public ModelAndView getGames(HttpServletRequest request, HttpServletResponse response,Long game_id) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("game", gameService.getGame(game_id));
        JSONProcessor json = new JSONProcessor(jsonMap);

        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    //</editor-fold>

    //<editor-fold desc="Phase 0">
    @RequestMapping("/create_game")
    public ModelAndView addGame(HttpServletRequest request, HttpServletResponse response,String name,Integer company_count) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }
        StartSettings startSettings = new StartSettings();
        try
        {
            startSettings.fill(request);
        }
        catch(NumberFormatException nfex)
        {
            JSONProcessor json = new JSONProcessor("code",StatusCode.WRONG_DATA_FORMAT);
            ModelAndView model = new ModelAndView("json");
            model.addObject("json",json.jsonString());
            return model;
        }
        StatusCode code = gameService.createGame(name, GameMode.DEFAULT, startSettings,company_count);
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/join_game")
    public ModelAndView join(HttpServletRequest request, HttpServletResponse response,Long id) throws Exception
    {
        User u = user(request);
        Player p = new Player();
        p.login = u.getStringValue("login");
        p.id = u.getLongValue("id");

        StatusCode code = gameService.joinGame(id,p);
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/start_game")
    public ModelAndView start(HttpServletRequest request, HttpServletResponse response,Long id) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }
        StatusCode code = gameService.startGame(id);
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    //</editor-fold>

    //<editor-fold desc="Phase 1">
    @RequestMapping("/declare_goods")
    public ModelAndView declareGoods(HttpServletRequest request, HttpServletResponse response,String name) throws Exception
    {
        GoodsDeclaration goodsDeclaration = new GoodsDeclaration();
        goodsDeclaration.fill(request);

        StatusCode code = StatusCode.SUCCESS; // TODO: declare goods...
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/set_trade_factors")
    public ModelAndView setTradeFactors(HttpServletRequest request, HttpServletResponse response,String name) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }

        TradeFactors tradeFactors = new TradeFactors();
        tradeFactors.fill(request);

        StatusCode code = StatusCode.SUCCESS; // TODO. Set trade factors...
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/end_phase_1")
    public ModelAndView endPhase1(HttpServletRequest request, HttpServletResponse response,String name) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }

        StatusCode code = StatusCode.SUCCESS; // TODO. Calculate end phase 1.
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    //</editor-fold>
}

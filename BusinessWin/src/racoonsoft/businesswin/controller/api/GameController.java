package racoonsoft.businesswin.controller.api;

import com.rits.cloning.Cloner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.businesswin.database.PostgresqlDataSource;
import racoonsoft.businesswin.structure.data.*;
import racoonsoft.businesswin.structure.enums.CompanySellReason;
import racoonsoft.businesswin.structure.model.Game;
import racoonsoft.businesswin.structure.model.GameWorld;
import racoonsoft.businesswin.structure.model.GoodsDeclaration;
import racoonsoft.businesswin.structure.model.Player;
import racoonsoft.businesswin.structure.enums.GameMode;
import racoonsoft.businesswin.structure.enums.StatusCode;
import racoonsoft.library.access.User;
import racoonsoft.library.json.JSONProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
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
    @RequestMapping("/game_list")
    public ModelAndView gameList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        HashMap<Long,Game> games = new Cloner().deepClone(gameService.getGames());
        Collection<Game> gameColl = games.values();
        for(Game g: gameColl)
        {
            g.companies.clear();
            g.declared_event_cards= null;
            g.demand_supply_curve = null;
            g.industry_performance = null;
            g.product_price_and_production = null;
            g.startSettings = null;
            g.tradeFactors = null;
            Collection<Player> playerColl = g.players.values();
            for(Player p: playerColl)
            {
                p.companies.clear();
            }
        }
        jsonMap.put("games", games);
        JSONProcessor json = new JSONProcessor(jsonMap);

        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/get_game")
    public ModelAndView getGames(HttpServletRequest request, HttpServletResponse response,Long game_id,Boolean exclude_business_plan) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        Game g = gameService.getGame(game_id);
        Cloner cloner = new Cloner();
        Game clonedGame = null;
        if(g!=null)
        {
            clonedGame = cloner.deepClone(g);
            clonedGame.companies.clear();
            if(exclude_business_plan!=null&&exclude_business_plan)
            {
                clonedGame.removeBusinessPlan();
            }
        }
        jsonMap.put("game", clonedGame);
        JSONProcessor json = new JSONProcessor(jsonMap);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/get_player")
    public ModelAndView getPlayer(HttpServletRequest request, HttpServletResponse response,Long game_id,Long player_id,Boolean exclude_business_plan) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        Game g = gameService.getGame(game_id);
        if(g!=null)
        {
            g = new Cloner().deepClone(g);
        }
        if(exclude_business_plan!=null&&exclude_business_plan)
        {
            g.removeBusinessPlan();
        }
        Player p = null;
        if(g!=null)
        {
            p = g.getPlayer(player_id);
        }
        jsonMap.put("player", p);
        JSONProcessor json = new JSONProcessor(jsonMap);
        //json.BodyStructure.remove()
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/check_activity")
    public ModelAndView checkActivity(HttpServletRequest request, HttpServletResponse response,Long game_id) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        Game g = gameService.getGame(game_id);
        if(g!=null)
        {
            jsonMap.put("current_action", g.currentAction);
            jsonMap.put("code", StatusCode.SUCCESS);
        }
        else
        {
            jsonMap.put("current_action", null);
            jsonMap.put("code", StatusCode.NO_SUCH_GAME);
        }
        JSONProcessor json = new JSONProcessor(jsonMap);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/reboot")
    public ModelAndView reboot(HttpServletRequest request, HttpServletResponse response,Long game_id) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }
        GameWorld.reboot();
        JSONProcessor json = new JSONProcessor("code",StatusCode.SUCCESS);
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
    public ModelAndView declareGoods(HttpServletRequest request, HttpServletResponse response,Long game_id,Long company_id) throws Exception
    {
        GoodsDeclaration goodsDeclaration = new GoodsDeclaration();
        goodsDeclaration.fill(request);
        StatusCode code = gameService.declareGoods(game_id,id(request),company_id,goodsDeclaration);
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/set_trade_factors")
    public ModelAndView setTradeFactors(HttpServletRequest request, HttpServletResponse response,Long game_id) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }

        TradeFactors tradeFactors = new TradeFactors();
        tradeFactors.fill(request);
        StatusCode code = gameService.setTradeFactors(game_id,tradeFactors);
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/end_phase_1")
    public ModelAndView endPhase1(HttpServletRequest request, HttpServletResponse response,Long game_id) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }
        StatusCode code = gameService.endPhase1(game_id);
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    //</editor-fold>

    //<editor-fold desc="Phase 2">
    @RequestMapping("/set_event_cards")
    public ModelAndView setEventCards(HttpServletRequest request, HttpServletResponse response,Long game_id,Long company_id) throws Exception
    {
        DeclaredEventCards eventCards = new DeclaredEventCards();
        eventCards.fill(request);
        StatusCode code = gameService.setEventCards(game_id,eventCards);
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/accept_card")
    public ModelAndView acceptCard(HttpServletRequest request, HttpServletResponse response,Long game_id,Long company_id,String event_card_type,String size) throws Exception
    {
        Credit credit = new Credit();
        credit.fill(request);
        StatusCode code = gameService.acceptCard(game_id,id(request),company_id,event_card_type,size,credit);

        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/sell_company")
    public ModelAndView sellCompany(HttpServletRequest request, HttpServletResponse response
            ,Long game_id
            ,Long company_id) throws Exception
    {
        StatusCode code = gameService.sellCompany(game_id, id(request), company_id, CompanySellReason.FREE_WILL);
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/bet_company")
    public ModelAndView betCompany(HttpServletRequest request, HttpServletResponse response
            ,Long game_id
            ,Long buyer_company_id) throws Exception
    {
        CompanyBet companyBet = new CompanyBet();
        Credit credit = new Credit();
        companyBet.fill(request);
        credit.fill(request);
        if(companyBet.need_credit)
        {
            companyBet.credit = credit;
        }
        StatusCode code = gameService.betCompany(game_id, id(request), buyer_company_id, companyBet);
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/consolidation")
    public ModelAndView consolidation(HttpServletRequest request, HttpServletResponse response
            ,Long game_id
            ,Long company_id
            ,Double value) throws Exception
    {
        StatusCode code = gameService.consolidation(game_id, id(request), company_id, value);
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/end_phase_2")
    public ModelAndView endPhase2(HttpServletRequest request, HttpServletResponse response,Long game_id) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }
        StatusCode code = gameService.endPhase2(game_id);
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    //</editor-fold>

    //<editor-fold desc="Phase 3">
    @RequestMapping("/end_phase_3")
    public ModelAndView endPhase3(HttpServletRequest request, HttpServletResponse response,Long game_id) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }
        StatusCode code = gameService.endPhase3(game_id);
        JSONProcessor json = new JSONProcessor("code",code);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    //</editor-fold>

    @RequestMapping("/test")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return new ModelAndView("start_game");
    }
}

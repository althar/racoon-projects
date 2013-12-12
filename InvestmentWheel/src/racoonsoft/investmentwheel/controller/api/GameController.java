package racoonsoft.investmentwheel.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.investmentwheel.database.PostgresqlDataSource;
import racoonsoft.investmentwheel.game.structure.Player;
import racoonsoft.investmentwheel.game.structure.enums.GameMode;
import racoonsoft.investmentwheel.game.structure.GameWorld;
import racoonsoft.library.json.JSONProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/api")
public class GameController extends InvestmentWheelController
{
    @Autowired
    private PostgresqlDataSource dbProc;

    @Autowired
    private GameWorld gameWorld;

    @RequestMapping("/games")
    public ModelAndView games(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("games", gameWorld.getGames());
        JSONProcessor json = new JSONProcessor(jsonMap);

        ModelAndView model = new ModelAndView("json");
        //model.addObject("json",json.toJsonString());
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/add_game")
    public ModelAndView addGame(HttpServletRequest request, HttpServletResponse response,String name) throws Exception
    {
        JSONProcessor json = gameWorld.createGame(name, GameMode.DEFAULT);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/join_game")
    public ModelAndView join(HttpServletRequest request, HttpServletResponse response,Long id,String login,String password) throws Exception
    {
        Player p = new Player();
        p.setValue("login",login);
        p.setValue("id",123);
        JSONProcessor json = gameWorld.joinGame(id,p);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/setup_game")
    public ModelAndView setupGame(HttpServletRequest request, HttpServletResponse response,Long id) throws Exception
    {
        JSONProcessor json = gameWorld.setupGame(id, getParameters(request));
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/start_game")
    public ModelAndView start(HttpServletRequest request, HttpServletResponse response,Long id) throws Exception
    {
        JSONProcessor json = gameWorld.startGame(id);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/turn_game")
    public ModelAndView turn(HttpServletRequest request, HttpServletResponse response,Long id) throws Exception
    {
        JSONProcessor json = gameWorld.turnGame(id);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
}

package racoonsoft.businesswin.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.businesswin.database.PostgresqlDataSource;
import racoonsoft.businesswin.game.structure.Player;
import racoonsoft.businesswin.game.structure.enums.GameMode;
import racoonsoft.businesswin.game.structure.GameWorld;
import racoonsoft.businesswin.game.structure.enums.StatusCode;
import racoonsoft.library.access.User;
import racoonsoft.library.access.UserProcessor;
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

    @Autowired
    private GameWorld gameWorld;

    @RequestMapping("/games")
    public ModelAndView games(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("games", gameWorld.getGames());
        JSONProcessor json = new JSONProcessor(jsonMap);

        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/get_game")
    public ModelAndView getGames(HttpServletRequest request, HttpServletResponse response,Long game_id) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("game", gameWorld.getGame(game_id));
        JSONProcessor json = new JSONProcessor(jsonMap);

        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/add_game")
    public ModelAndView addGame(HttpServletRequest request, HttpServletResponse response,String name) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }
        JSONProcessor json = gameWorld.createGame(name, GameMode.DEFAULT);
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
        JSONProcessor json = gameWorld.joinGame(id,p);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/setup_game")
    public ModelAndView setupGame(HttpServletRequest request, HttpServletResponse response,Long id) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }
        JSONProcessor json = gameWorld.setupGame(id, getParameters(request));
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
        JSONProcessor json = gameWorld.startGame(id);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/finish_game")
    public ModelAndView finish(HttpServletRequest request, HttpServletResponse response,Long id) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }
        JSONProcessor json = gameWorld.finishGame(id);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/turn_game")
    public ModelAndView turn(HttpServletRequest request, HttpServletResponse response,Long id) throws Exception
    {
        if(!hasRole(request,"ADMIN"))
        {
            return generateError(StatusCode.NO_PERMISSIONS);
        }
        JSONProcessor json = gameWorld.turnGame(id);
        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
}

package racoonsoft.investmentwheel.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.investmentwheel.database.PostgresqlDataSource;
import racoonsoft.investmentwheel.game.structure.GameMode;
import racoonsoft.investmentwheel.game.structure.GameWorld;
import racoonsoft.library.json.JSONProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/api")
public class GameController
{
    @Autowired
    private PostgresqlDataSource dbProc;

    @RequestMapping("/games")
    public ModelAndView games(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("games", GameWorld.instance().getGames());
        JSONProcessor json = new JSONProcessor(jsonMap);

        ModelAndView model = new ModelAndView("json");
        //model.addObject("json",json.toJsonString());
        model.addObject("json",json.jsonString());
        return model;
    }
    @RequestMapping("/add_game")
    public ModelAndView addGame(HttpServletRequest request, HttpServletResponse response,String name) throws Exception
    {
        HashMap<String,Object> jsonMap = new HashMap<String, Object>();
        Long gameId = GameWorld.instance().createGame(name, GameMode.DEFAULT);
        jsonMap.put("game_id", gameId);
        System.out.println(GameWorld.instance().getGames());
        jsonMap.put("games", GameWorld.instance().getGames());
        JSONProcessor json = new JSONProcessor(jsonMap);

        ModelAndView model = new ModelAndView("json");
        model.addObject("json",json.jsonString());
        return model;
    }
}

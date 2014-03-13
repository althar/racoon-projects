package racoonsoft.businesswin.test;

import racoonsoft.businesswin.structure.model.Game;
import racoonsoft.businesswin.structure.model.Player;
import racoonsoft.businesswin.structure.data.StartSettings;
import racoonsoft.businesswin.structure.enums.GameMode;
import racoonsoft.library.json.JSONProcessor;

import java.util.HashMap;

public class Test
{
    public static void main(String[] args)
    {
        try
        {
//            StartSettings settings = new StartSettings();
//            Game g = new Game(0l,GameMode.DEFAULT,"name");
//            Player p = new Player();
//            p.id = 92l;
//            g.addPlayer(p);
//            HashMap<String, Object> vals = new HashMap<String, Object>();
//            vals.put("game",g);
//            JSONProcessor proc = new JSONProcessor(vals);
//            System.out.println(proc.toJsonString());
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
    }
}

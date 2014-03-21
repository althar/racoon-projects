package racoonsoft.businesswin.test;

import racoonsoft.businesswin.service.*;
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
            for(int i=0; i<20; i++)
            System.out.println(racoonsoft.businesswin.service.Math.random(100.100,999.999,3));
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
    }
}

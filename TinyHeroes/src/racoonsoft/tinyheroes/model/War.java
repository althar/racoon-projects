package racoonsoft.tinyheroes.model;

import java.util.HashMap;

public class War
{
    private static TimeMachine time_machine;
    private static Judge judge;
    private static AI ai;

    private static HashMap<String,Battlefield> battlefields;

    public War()
    {
        time_machine = new TimeMachine();
        judge = new Judge();
        ai = new AI();
        battlefields = new HashMap<String, Battlefield>();
    }
}

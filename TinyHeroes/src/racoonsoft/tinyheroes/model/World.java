package racoonsoft.tinyheroes.model;

import java.util.HashMap;

public class World
{
    private static HashMap<String,Barony> baronies;
    private static War war;

    public static void initWorld()
    {
        war = new War();
        baronies = new HashMap<String, Barony>();
    }
    public static Barony getBarony(String name)
    {
        return baronies.get(name);
    }
    public static void addBarony(Barony b)
    {
        baronies.put(b.getName(),b);
    }
}

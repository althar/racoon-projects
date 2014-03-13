package racoonsoft.businesswin.structure.model;

import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="turn")
public class Turn
{
    @DataStructureField(name="turn")
    public int turn = 1;
    @DataStructureField(name="phase")
    public int phase = 0;

    public void nextPhase()
    {
        if(phase==3)
        {
            turn++;
            phase=0;
        }
        phase++;
    }
}

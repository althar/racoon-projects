package racoonsoft.businesswin.structure.data;

import racoonsoft.businesswin.structure.model.Company;
import racoonsoft.businesswin.structure.model.Player;
import racoonsoft.businesswin.structure.model.Turn;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="credit")
public class Credit extends GameBindStructure
{
    @DataStructureField(name="turn")
    public Integer turn = 0;

    @DataStructureField(name="credit_value")
    public Double credit_value = 0.0;

    @DataStructureField(name="credit_term")
    public Integer credit_term = 0;

    @DataStructureField(name="rate_on_the_loan")
    public Double rate_on_the_loan = 0.0;

    public Double getYearlyPayment()
    {
        return credit_value/credit_term;
    }
}

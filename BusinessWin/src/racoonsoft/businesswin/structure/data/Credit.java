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

    @DataStructureField(name="current_percent_payment")
    public Double current_percent_payment = 0.0;

    @DataStructureField(name="current_debt")
    public Double current_debt = 0.0;

    public Double getYearlyPayment()
    {
        return credit_value/credit_term;
    }
    public Double getPercentPayment(int turn)
    {
        Double toPayLeft = credit_value-getYearlyPayment()*(turn-this.turn);
        current_percent_payment = toPayLeft*rate_on_the_loan;
        return current_percent_payment;
    }
    public Double getPaymentWithPercent(int turn)
    {
        return getYearlyPayment()+getPercentPayment(turn);
    }
    public Double getDebt(int turn)
    {
        current_debt = credit_value-(turn-this.turn)*getYearlyPayment();
        return current_debt;
    }
}

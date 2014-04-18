package racoonsoft.businesswin.structure.data;

import racoonsoft.businesswin.structure.enums.AcquisitionMethod;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="fixed_assets_and_depreciation")
public class FixedAssetsAndDepreciation
{
    public FixedAssetsAndDepreciation(Double cost, Double depreciation, Integer depreciation_period, Integer turn, AcquisitionMethod method)
    {
        this.acquisition_method = method;
        this.acquisition_turn = turn;
        this.depreciation.set(depreciation);
        this.depreciation_period = depreciation_period;
        this.fixed_assets_cost.set(cost);
    }
    public FixedAssetsAndDepreciation(Double cost, Integer depreciation_period, Integer turn, AcquisitionMethod method)
    {
        this.acquisition_method = method;
        this.acquisition_turn = turn;

        this.fixed_assets_cost.set(cost);
        this.depreciation_period = depreciation_period;
        this.depreciation.set(cost/depreciation_period);


    }

    @DataStructureField(name="fixed_assets_cost")
    public EconomicsValue fixed_assets_cost = new EconomicsValue(0.0,99999.0,2,0.0);

    @DataStructureField(name="depreciation_period")
    public Integer depreciation_period = 0;

    @DataStructureField(name="depreciation")
    public EconomicsValue depreciation = new EconomicsValue(0.0,99999.0,2,0.0);

    @DataStructureField(name="acquisition_method")
    public AcquisitionMethod acquisition_method = AcquisitionMethod.START;

    @DataStructureField(name="acquisition_turn")
    public Integer acquisition_turn = 0;

    public Double getFixedAssetsForTurn(int turn)
    {
        int depTurns = turn-acquisition_turn;
        if(depTurns>depreciation_period)
        {
            depTurns = depreciation_period;
        }
        Double result = fixed_assets_cost.get()-(depreciation.get()*depTurns);
        return result;
    }
    public Double getDepreciation(int turn)
    {
        if(turn-acquisition_turn<depreciation_period)
        {
            return depreciation.get();
        }
        return 0.0;
    }
}

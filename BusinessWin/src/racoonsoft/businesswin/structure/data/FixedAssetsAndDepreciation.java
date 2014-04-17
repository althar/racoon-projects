package racoonsoft.businesswin.structure.data;

import racoonsoft.businesswin.structure.enums.AcquisitionMethod;
import racoonsoft.library.annotations.DataStructure;
import racoonsoft.library.annotations.DataStructureField;

@DataStructure(name="fixed_assets_and_depreciation")
public class FixedAssetsAndDepreciation
{
    @DataStructureField(name="fixed_assets_cost")
    public EconomicsValue fixed_assets_cost = new EconomicsValue(0.0,99999.0,2,0.0);

    @DataStructureField(name="depreciation_period")
    public EconomicsValue depreciation_period = new EconomicsValue(0.0,99999.0,0,0.0);

    @DataStructureField(name="depreciation")
    public EconomicsValue depreciation = new EconomicsValue(0.0,99999.0,2,0.0);

    @DataStructureField(name="acquisition_method")
    public AcquisitionMethod acquisition_method = AcquisitionMethod.START;

    @DataStructureField(name="acquisition_turn")
    public Integer acquisition_turn = 0;
}

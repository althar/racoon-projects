package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructureValue;
import racoonsoft.library.helper.MathHelper;

public class EconomicsValue
{
    protected int accuracy;
    @DataStructureValue(name="value")
    private Double value;
    private Double max;
    private Double min;

    public EconomicsValue(Double min,Double max, int accuracy, Double value)
    {
        this.min = min;
        this.max = max;
        this.accuracy = accuracy;
        set(value);
    }
    public void set(Double val)
    {
        value = val;
        if(value>max)
        {
            value = max;
        }
        if(value<min)
        {
            value = min;
        }
        value = MathHelper.round(value,accuracy);
    }
    public Double get()
    {
        return value;
    }
    @Override
    public String toString()
    {
        return value.toString();
    }
}
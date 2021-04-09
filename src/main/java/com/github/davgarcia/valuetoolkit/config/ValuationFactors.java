package com.github.davgarcia.valuetoolkit.config;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ValuationFactors {

    /**
     * The terminal growth rate is the weighted average of the GDP rate and the Single Stage Model rate.
     * <br>
     * This is used during DCF valuation. The default GDP weight is 2/3.
     */
    Double gdpGrowthRateWeight;

    public double getGdpGrowthRateWeight() {
        return gdpGrowthRateWeight == null ? 2d / 3d : gdpGrowthRateWeight;
    }
}

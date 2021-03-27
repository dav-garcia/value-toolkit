package com.github.davgarcia.valuetoolkit.config;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ValuationFactors {

    /**
     * Earnings multiple threshold, x15 by default.
     * <br>
     * If the reference multiple is below this threshold, then the company is undervalued.
     */
    Double earningsMultiple;

    /**
     * The terminal growth rate is the weighted average of the GDP rate and the Single Stage Model rate.
     * <br>
     * This is used during DCF valuation. The default GDP weight is 2/3.
     */
    Double gdpGrowthRateWeight;

    public double getEarningsMultiple() {
        return earningsMultiple == null ? 15d : earningsMultiple;
    }

    public double getGdpGrowthRateWeight() {
        return gdpGrowthRateWeight == null ? 2d / 3d : gdpGrowthRateWeight;
    }
}

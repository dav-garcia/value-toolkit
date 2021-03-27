package com.github.davgarcia.valuetoolkit.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BusinessEstimates {

    /**
     * Estimated number of years the company will maintain a "growth" profile.
     * <br>
     * Used during DCF valuation. The default is 5 years.
     */
    int growthYears;

    /**
     * Estimated FCF growth rate.
     * <br>
     * Using during DCF valuation. The default is based on the PRAT model.
     */
    double growthRate;

    public int getGrowthYears(final int defaultValue) {
        return growthYears > 0 ? growthYears : defaultValue;
    }

    public double getGrowthRate(final double defaultValue) {
        return growthRate > 0d ? growthRate : defaultValue;
    }
}

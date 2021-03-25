package com.github.davgarcia.valuetoolkit.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BusinessEstimates {

    private static final int DEFAULT_GROWTH_YEARS = 5;

    /**
     * Estimated number of years the company will maintain a "growth" profile.
     * <br>
     * Used during DCF valuation. The default is 5 years.
     */
    int growthYears;

    public int getGrowthYears() {
        return growthYears > 0 ? growthYears : DEFAULT_GROWTH_YEARS;
    }
}

package com.github.davgarcia.valuetoolkit;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CompanyEstimates {

    /**
     * Estimated number of years the company will maintain a "growth" profile.
     * <br>
     * Used during DCF valuation. The default is 5 years.
     */
    int growthYears;

    /**
     * Estimated long-term P/E ratio. It is supposed the current P/E ratio will converge to this one.
     * <br>
     * The average industry trailing P/E is used by default.
     */
    double perTarget;

    /**
     * Estimated revenue growth rate.
     * <br>
     * Used by the simple valuation method. The default is based on the PRAT model.
     */
    double revenueCagr;

    /**
     * Estimated FCF growth rate.
     * <br>
     * Using during DCF valuation. The default is based on the PRAT model.
     */
    double fcfCagr;

    public int getGrowthYears(final int defaultValue) {
        return growthYears > 0 ? growthYears : defaultValue;
    }

    public double getPerTarget(final double defaultValue) {
        return perTarget > 0d ? perTarget : defaultValue;
    }

    public double getRevenueCagr(final double defaultValue) {
        return revenueCagr > 0d ? revenueCagr : defaultValue;
    }

    public double getFcfCagr(final double defaultValue) {
        return fcfCagr > 0d ? fcfCagr : defaultValue;
    }
}

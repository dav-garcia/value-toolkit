package com.github.davgarcia.valuetoolkit.method;

import com.github.davgarcia.valuetoolkit.ValuationMethod;
import com.github.davgarcia.valuetoolkit.config.EconomyConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.Period;

public class DcfMethod implements ValuationMethod {

    @Override
    public double value(final EconomyConfigProperties economy, final Business business) {
        final var period = business.getNewestPeriod(Period.Status.ACTUAL);

        final var growthYears = business.getEstimates().getGrowthYears();
        final var fcf0 = period.getCashFlowStatement().getFreeCashFlow();
        final var growthRate0 = business.getIndicators().getPratGrowthRate() / 100d;
        final var discountRate = business.getIndicators().getWacc() / 100d;
        final var terminalGrowthRate = business.getIndicators().getTerminalGrowthRate() / 100d;
        final var sharesOutstanding = period.getIncomeStatement().getWeightedAverageSharesOutstanding();

        // Interpolate growth rate from initial value down to terminal growth rate.
        double fcfi = fcf0;
        double growthRatei = growthRate0;
        double growthStage = 0d;
        for (int i = 0; i < growthYears; i++) {
            fcfi *= (1d + growthRatei) / (1d + discountRate);
            growthRatei -= (growthRate0 - terminalGrowthRate) / growthYears;
            growthStage += fcfi;
        }
        final var terminalStage = fcf0 * Math.pow(1d + terminalGrowthRate, growthYears + 1d)
                / (discountRate - terminalGrowthRate)
                / Math.pow(1d + discountRate, growthYears + 1d);

        return (growthStage + terminalStage) / sharesOutstanding;
    }
}

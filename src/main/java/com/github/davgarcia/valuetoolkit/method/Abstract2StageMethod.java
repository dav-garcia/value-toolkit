package com.github.davgarcia.valuetoolkit.method;

import com.github.davgarcia.valuetoolkit.Business;
import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import com.github.davgarcia.valuetoolkit.ValuationMethod;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Abstract2StageMethod implements ValuationMethod {

    private static final int DEFAULT_GROWTH_YEARS = 5;

    private final Logger log;

    public Abstract2StageMethod() {
        log = LoggerFactory.getLogger(getClass());
    }

    @Override
    public double value(final ValueToolkitConfigProperties params, final Business business) {
        final var period = business.getLatestYear();

        final var growthYears = business.getEstimates().getGrowthYears(DEFAULT_GROWTH_YEARS);
        final var value0 = getValue0(business, period);
        final var growthRate0 = getGrowthRate0(business, period);
        final var discountRate = business.getIndicators().getWacc() / 100d;
        final var terminalGrowthRate = business.getIndicators().getTerminalGrowthRate() / 100d;
        final var sharesOutstanding = period.getIncomeStatement().getWeightedAverageSharesOutstanding();

        final var growthStage = computeGrowthStage(business, growthYears, value0, growthRate0, discountRate, terminalGrowthRate);
        final var terminalStage = computeTerminalStage(business, growthYears, value0, discountRate, terminalGrowthRate);

        return (growthStage + terminalStage) / sharesOutstanding;
    }

    protected abstract String getLabel();

    protected abstract double getValue0(final Business business, final FiscalPeriod period);

    protected abstract double getGrowthRate0(final Business business, final FiscalPeriod period);

    private double computeGrowthStage(final Business business, final int growthYears,
                                      final double value0, final double growthRate0, final double discountRate,
                                      final double terminalGrowthRate) {
        // Interpolate growth rate from initial value down to terminal growth rate.
        if (log.isInfoEnabled()) {
            log.info(String.format("%s growth stage: years = %d, discount rate = %f:",
                    business.getLocator().getSymbol(), growthYears, discountRate));
        }
        double valuei = value0;
        double growthStage = 0d;
        double growthRatei = growthRate0;
        for (int i = 0; i < growthYears; i++) {
            valuei *= (1d + growthRatei) / (1d + discountRate);
            growthStage += valuei;
            if (log.isInfoEnabled()) {
                log.info(String.format("  %2d. %s = %f, growth rate = %f, accum = %f",
                        i + 1, getLabel(), valuei, growthRatei, growthStage));
            }
            growthRatei -= (growthRate0 - terminalGrowthRate) / growthYears;
        }
        return growthStage;
    }

    private double computeTerminalStage(final Business business, final int growthYears,
                                        final double value0, final double discountRate, final double terminalGrowthRate) {
        final var terminalStage = value0 * Math.pow(1d + terminalGrowthRate, growthYears + 1d)
                / (discountRate - terminalGrowthRate)
                / Math.pow(1d + discountRate, growthYears + 1d);
        if (log.isInfoEnabled()) {
            log.info(String.format("%s terminal stage: terminal growth rate = %f, total = %f",
                    business.getLocator().getSymbol(), terminalGrowthRate, terminalStage));
        }
        return terminalStage;
    }
}

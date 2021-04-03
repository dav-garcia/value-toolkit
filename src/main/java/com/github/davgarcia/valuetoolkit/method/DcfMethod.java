package com.github.davgarcia.valuetoolkit.method;

import com.github.davgarcia.valuetoolkit.ValuationMethod;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DcfMethod implements ValuationMethod {

    private static final Logger LOG = LoggerFactory.getLogger(DcfMethod.class);
    private static final int DEFAULT_GROWTH_YEARS = 5;

    @Override
    public double value(final ValueToolkitConfigProperties params, final Company company) {
        final var period = company.getLatestPeriod();

        final var growthYears = company.getEstimates().getGrowthYears(DEFAULT_GROWTH_YEARS);
        final var fcf0 = period.getCashFlowStatement().getFreeCashFlow();
        final var growthRate0 = company.getEstimates().getFcfCagr(company.getIndicators().getPratGrowthRate()) / 100d;
        final var discountRate = company.getIndicators().getWacc() / 100d;
        final var terminalGrowthRate = company.getIndicators().getTerminalGrowthRate() / 100d;
        final var sharesOutstanding = period.getIncomeStatement().getWeightedAverageSharesOutstanding();

        final var growthStage = computeGrowthStage(company, growthYears, fcf0, growthRate0, discountRate, terminalGrowthRate);
        final var terminalStage = computeTerminalStage(company, growthYears, fcf0, discountRate, terminalGrowthRate);

        return (growthStage + terminalStage) / sharesOutstanding;
    }

    private double computeGrowthStage(final Company company, final int growthYears,
                                      final double fcf0, final double growthRate0, final double discountRate, final double terminalGrowthRate) {
        // Interpolate growth rate from initial value down to terminal growth rate.
        if (LOG.isInfoEnabled()) {
            LOG.info(String.format("%s growth stage: years = %d, discount rate = %f:",
                    company.getLocator().getSymbol(), growthYears, discountRate));
        }
        double fcfi = fcf0;
        double growthStage = 0d;
        double growthRatei = growthRate0;
        for (int i = 0; i < growthYears; i++) {
            fcfi *= (1d + growthRatei) / (1d + discountRate);
            growthStage += fcfi;
            if (LOG.isInfoEnabled()) {
                LOG.info(String.format("  %2d. fcf = %f, growth rate = %f, accum = %f",
                        i + 1, fcfi, growthRatei, growthStage));
            }
            growthRatei -= (growthRate0 - terminalGrowthRate) / growthYears;
        }
        return growthStage;
    }

    private double computeTerminalStage(final Company company, final int growthYears,
                                        final double fcf0, final double discountRate, final double terminalGrowthRate) {
        final var terminalStage = fcf0 * Math.pow(1d + terminalGrowthRate, growthYears + 1d)
                / (discountRate - terminalGrowthRate)
                / Math.pow(1d + discountRate, growthYears + 1d);
        if (LOG.isInfoEnabled()) {
            LOG.info(String.format("%s terminal stage: terminal growth rate = %f, total = %f",
                    company.getLocator().getSymbol(), terminalGrowthRate, terminalStage));
        }
        return terminalStage;
    }
}

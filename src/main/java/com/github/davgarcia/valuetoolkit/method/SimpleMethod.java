package com.github.davgarcia.valuetoolkit.method;

import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;
import com.github.davgarcia.valuetoolkit.ValuationMethod;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleMethod implements ValuationMethod {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleMethod.class);
    private static final int FORECAST_YEARS = 5;

    @Override
    public double value(final ValueToolkitConfigProperties params, final Company company) {
        final var period = company.getLatestPeriod(Period.Status.ACTUAL);

        final var revenue = period.getIncomeStatement().getRevenue();
        final var profitMargin = company.getIndicators().getProfitMargin();
        final var growthRate = company.getEstimates().getRevenueCagr(company.getIndicators().getPratGrowthRate());
        final var sharesOutstanding = period.getIncomeStatement().getWeightedAverageSharesOutstanding();
        final var targetPer = company.getEstimates().getPerTarget(); // TODO: Use industry average as default value.

        if (LOG.isInfoEnabled()) {
            LOG.info(String.format("%s simple: revenue = %f, profit margin = %f, growth rate = %f, 5Y target per = %f",
                    company.getLocator(), revenue, profitMargin, growthRate, targetPer));
        }

        return revenue * profitMargin * Math.pow(1 + growthRate / 100d, FORECAST_YEARS) / sharesOutstanding * targetPer;
    }
}

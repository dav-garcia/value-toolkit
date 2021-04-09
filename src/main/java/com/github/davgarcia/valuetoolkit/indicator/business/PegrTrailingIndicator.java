package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Business;

public class PegrTrailingIndicator implements BusinessIndicator {

    public static final PegrTrailingIndicator INSTANCE = new PegrTrailingIndicator();

    private PegrTrailingIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Business business) {
        final var period = business.getLatestYear();
        final var previousPeriod = business.getPreviousYear(period);
        if (previousPeriod == null) {
            return 0d;
        }

        final var perTrailing = business.getIndicators().getPerTrailing();
        final var currentEps = period.getIncomeStatement().getEps();
        final var previousEps = previousPeriod.getIncomeStatement().getEps();
        final var epsGrowthRate = (currentEps / previousEps - 1) * 100;

        return perTrailing / epsGrowthRate;
    }
}

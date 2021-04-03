package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.CompanyIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;

public class PegrTrailingIndicator implements CompanyIndicator {

    public static final PegrTrailingIndicator INSTANCE = new PegrTrailingIndicator();

    private PegrTrailingIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Company company) {
        final var period = company.getLatestPeriod(Period.Status.ACTUAL);
        final var previousPeriod = company.getPreviousPeriod(period);
        if (previousPeriod == null) {
            return 0d;
        }

        final var perTrailing = company.getIndicators().getPerTrailing();
        final var currentEps = period.getIncomeStatement().getEps();
        final var previousEps = previousPeriod.getIncomeStatement().getEps();
        final var epsGrowthRate = (currentEps / previousEps - 1) * 100;

        return perTrailing / epsGrowthRate;
    }
}

package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.CompanyIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;

public class PerTrailingIndicator implements CompanyIndicator {

    public static final PerTrailingIndicator INSTANCE = new PerTrailingIndicator();

    private PerTrailingIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Company company) {
        final var period = company.getLatestPeriod(Period.Status.ACTUAL);

        final var marketCap = company.getProfile().getMarketCap();
        final var netIncome = period.getIncomeStatement().getNetIncome();

        return marketCap / netIncome;
    }
}

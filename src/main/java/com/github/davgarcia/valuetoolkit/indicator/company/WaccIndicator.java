package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.CompanyIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;

public class WaccIndicator implements CompanyIndicator {

    public static final WaccIndicator INSTANCE = new WaccIndicator();

    private WaccIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Company company) {
        final var period = company.getLatestPeriod();

        final var e = company.getProfile().getMarketCap();
        final var d = period.getBalanceSheet().getTotalDebt();
        final var v = e + d;
        final var costOfEquity = company.getIndicators().getCapmCostOfEquity();
        final var costOfDebt = company.getIndicators().getCostOfDebt();
        final var taxRate = company.getIndicators().getTaxRate() / 100d;

        return e / v * costOfEquity + d / v * costOfDebt * (1d - taxRate);
    }
}

package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.CompanyIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;

public class PriceToBookValueIndicator implements CompanyIndicator {

    public static final PriceToBookValueIndicator INSTANCE = new PriceToBookValueIndicator();

    private PriceToBookValueIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Company company) {
        final var period = company.getLatestPeriod();

        final var marketCap = company.getProfile().getMarketCap();
        final var bookValue = period.getBalanceSheet().getTotalStockholdersEquity();

        return marketCap / bookValue;
    }
}

package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.CompanyIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;

public class PriceToSalesIndicator implements CompanyIndicator {

    public static final PriceToSalesIndicator INSTANCE = new PriceToSalesIndicator();

    private PriceToSalesIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Company company) {
        final var period = company.getLatestPeriod();

        final var marketCap = company.getProfile().getMarketCap();
        final var revenue = period.getIncomeStatement().getRevenue();

        return marketCap / revenue;
    }
}

package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.CompanyIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;

public class PriceToFcfIndicator implements CompanyIndicator {

    public static final PriceToFcfIndicator INSTANCE = new PriceToFcfIndicator();

    private PriceToFcfIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Company company) {
        final var period = company.getLatestPeriod();

        final var marketCap = company.getProfile().getMarketCap();
        final var fcf = period.getCashFlowStatement().getFreeCashFlow();

        return marketCap / fcf;
    }
}

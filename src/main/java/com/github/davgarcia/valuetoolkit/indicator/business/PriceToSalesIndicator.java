package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Business;
import com.github.davgarcia.valuetoolkit.Period;

public class PriceToSalesIndicator implements BusinessIndicator {

    public static final PriceToSalesIndicator INSTANCE = new PriceToSalesIndicator();

    private PriceToSalesIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Business business) {
        final var period = business.getLatestPeriod(Period.Status.ACTUAL);

        final var marketCap = business.getProfile().getMarketCap();
        final var revenue = period.getIncomeStatement().getRevenue();

        return marketCap / revenue;
    }
}

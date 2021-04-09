package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Business;

public class PriceToBookValueIndicator implements BusinessIndicator {

    public static final PriceToBookValueIndicator INSTANCE = new PriceToBookValueIndicator();

    private PriceToBookValueIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Business business) {
        final var period = business.getLatestYear();

        final var marketCap = business.getProfile().getMarketCap();
        final var bookValue = period.getBalanceSheet().getTotalStockholdersEquity();

        return marketCap / bookValue;
    }
}

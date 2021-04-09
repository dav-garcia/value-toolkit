package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Business;

public class PriceToFcfIndicator implements BusinessIndicator {

    public static final PriceToFcfIndicator INSTANCE = new PriceToFcfIndicator();

    private PriceToFcfIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Business business) {
        final var period = business.getLatestYear();

        final var marketCap = business.getProfile().getMarketCap();
        final var fcf = period.getCashFlowStatement().getFreeCashFlow();

        return marketCap / fcf;
    }
}

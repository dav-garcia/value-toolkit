package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.EconomyConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.Period;

public class WaccIndicator implements BusinessIndicator {

    public static final WaccIndicator INSTANCE = new WaccIndicator();

    private WaccIndicator() {
        // Empty.
    }

    @Override
    public double eval(final EconomyConfigProperties economy, final Business business) {
        final var period = business.getNewestPeriod(Period.Status.ACTUAL);

        final var e = business.getProfile().getMarketCap();
        final var d = period.getBalanceSheet().getTotalDebt();
        final var v = e + d;
        final var costOfEquity = business.getIndicators().getCapmCostOfEquity();
        final var costOfDebt = business.getIndicators().getCostOfDebt();
        final var taxRate = business.getIndicators().getTaxRate() / 100d;

        return e / v * costOfEquity + d / v * costOfDebt * (1d - taxRate);
    }
}

package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.EconomyConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;

public class CapmCostOfEquityIndicator implements BusinessIndicator {

    public static final CapmCostOfEquityIndicator INSTANCE = new CapmCostOfEquityIndicator();

    private CapmCostOfEquityIndicator() {
        // Empty.
    }

    @Override
    public double eval(final EconomyConfigProperties economy, final Business business) {
        final var currency = business.getProfile().getCurrency();
        final var riskFreeRate = economy.getRiskFreeRate(currency);
        final var beta = business.getProfile().getBeta();
        final var erp = economy.getErp();

        return riskFreeRate + beta * erp;
    }
}

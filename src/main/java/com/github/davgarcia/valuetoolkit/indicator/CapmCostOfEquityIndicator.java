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
        final var riskFreeRate = economy.getRiskFreeRate(business);
        final var beta = business.getProfile().getBeta();
        final var erp = economy.getErp();
        final var countryRisk = economy.getCounrtyRiskRate(business);
        final var sizeRisk = economy.getSizeRiskRate(business);

        return riskFreeRate + beta * erp + countryRisk + sizeRisk;
    }
}

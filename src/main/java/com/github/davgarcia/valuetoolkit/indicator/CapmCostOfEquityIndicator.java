package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;

public class CapmCostOfEquityIndicator implements BusinessIndicator {

    public static final CapmCostOfEquityIndicator INSTANCE = new CapmCostOfEquityIndicator();

    private CapmCostOfEquityIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Business business) {
        final var riskFreeRate = params.getEconomicFactors().getRiskFreeRate(business);
        final var beta = business.getProfile().getBeta();
        final var erp = params.getEconomicFactors().getErp();
        final var countryRisk = params.getEconomicFactors().getCounrtyRiskRate(business);
        final var sizeRisk = params.getEconomicFactors().getSizeRiskRate(business);

        return riskFreeRate + beta * erp + countryRisk + sizeRisk;
    }
}

package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.CompanyIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;

public class CapmCostOfEquityIndicator implements CompanyIndicator {

    public static final CapmCostOfEquityIndicator INSTANCE = new CapmCostOfEquityIndicator();

    private CapmCostOfEquityIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Company company) {
        final var riskFreeRate = params.getEconomicFactors().getRiskFreeRate(company);
        final var beta = company.getProfile().getBeta();
        final var erp = params.getEconomicFactors().getErp();
        final var countryRisk = params.getEconomicFactors().getCounrtyRiskRate(company);
        final var sizeRisk = params.getEconomicFactors().getSizeRiskRate(company);

        return riskFreeRate + beta * erp + countryRisk + sizeRisk;
    }
}

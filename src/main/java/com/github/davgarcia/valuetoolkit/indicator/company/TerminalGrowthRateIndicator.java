package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.CompanyIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;

public class TerminalGrowthRateIndicator implements CompanyIndicator {

    public static final TerminalGrowthRateIndicator INSTANCE = new TerminalGrowthRateIndicator();

    private TerminalGrowthRateIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Company company) {
        final var period = company.getLatestPeriod(Period.Status.ACTUAL);

        final var gdpRate = params.getEconomicFactors().getGdpGrowthRate(company);
        final var marketValue = company.getProfile().getMarketCap();
        final var discountRate = company.getIndicators().getWacc();
        final var fcf0 = period.getCashFlowStatement().getFreeCashFlow();
        final var singleStageModel = (marketValue * discountRate - fcf0) / (marketValue + fcf0);
        final var gdpRateWeight = params.getValuationFactors().getGdpGrowthRateWeight();

        return gdpRateWeight * gdpRate + (1d - gdpRateWeight) * singleStageModel;
    }
}

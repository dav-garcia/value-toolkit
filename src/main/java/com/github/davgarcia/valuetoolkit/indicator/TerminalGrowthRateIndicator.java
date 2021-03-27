package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.Period;

public class TerminalGrowthRateIndicator implements BusinessIndicator {

    public static final TerminalGrowthRateIndicator INSTANCE = new TerminalGrowthRateIndicator();

    private TerminalGrowthRateIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Business business) {
        final var period = business.getNewestPeriod(Period.Status.ACTUAL);

        final var gdpRate = params.getEconomicFactors().getGdpGrowthRate(business);
        final var marketValue = business.getProfile().getMarketCap();
        final var discountRate = business.getIndicators().getWacc();
        final var fcf0 = period.getCashFlowStatement().getFreeCashFlow();
        final var singleStageModel = (marketValue * discountRate - fcf0) / (marketValue + fcf0);
        final var gdpRateWeight = params.getValuationFactors().getGdpGrowthRateWeight();

        return gdpRateWeight * gdpRate + (1d - gdpRateWeight) * singleStageModel;
    }
}

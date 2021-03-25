package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.EconomyConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.Period;

public class TerminalGrowthRateIndicator implements BusinessIndicator {

    public static final TerminalGrowthRateIndicator INSTANCE = new TerminalGrowthRateIndicator();

    private TerminalGrowthRateIndicator() {
        // Empty.
    }

    @Override
    public double eval(final EconomyConfigProperties economy, final Business business) {
        final var period = business.getNewestPeriod(Period.Status.ACTUAL);

        final var gdpRate = economy.getGdpGrowthRate(business.getProfile().getCountry());
        final var marketValue = business.getProfile().getMarketCap();
        final var discountRate = business.getIndicators().getWacc();
        final var fcf0 = period.getCashFlowStatement().getFreeCashFlow();
        final var singleStageModel = (marketValue * discountRate - fcf0) / (marketValue + fcf0);

        return (gdpRate + singleStageModel) / 2d;
    }
}

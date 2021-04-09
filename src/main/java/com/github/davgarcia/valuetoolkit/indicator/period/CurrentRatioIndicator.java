package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import com.github.davgarcia.valuetoolkit.FiscalPeriodIndicator;

public class CurrentRatioIndicator implements FiscalPeriodIndicator {

    public static final CurrentRatioIndicator INSTANCE = new CurrentRatioIndicator();

    private CurrentRatioIndicator() {
        // Empty.
    }

    @Override
    public double eval(final FiscalPeriod period) {
        final var currentAssets = period.getBalanceSheet().getTotalCurrentAssets();
        final var currentLiabilities = period.getBalanceSheet().getTotalCurrentLiabilities();

        return currentAssets / currentLiabilities;
    }
}

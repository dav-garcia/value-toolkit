package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.Period;
import com.github.davgarcia.valuetoolkit.PeriodIndicator;

public class CurrentRatioIndicator implements PeriodIndicator {

    public static final CurrentRatioIndicator INSTANCE = new CurrentRatioIndicator();

    private CurrentRatioIndicator() {
        // Empty.
    }

    @Override
    public double eval(final Period period) {
        final var currentAssets = period.getBalanceSheet().getTotalCurrentAssets();
        final var currentLiabilities = period.getBalanceSheet().getTotalCurrentLiabilities();

        return currentAssets / currentLiabilities;
    }
}

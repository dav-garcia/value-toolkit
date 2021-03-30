package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.Period;
import com.github.davgarcia.valuetoolkit.PeriodIndicator;

public class RoicIndicator implements PeriodIndicator {

    public static final RoicIndicator INSTANCE = new RoicIndicator();

    private RoicIndicator() {
        // Empty.
    }

    @Override
    public double eval(final Period period) {
        final var currentAssets = period.getBalanceSheet().getTotalCurrentAssets();
        final var currentLiabilities = period.getBalanceSheet().getTotalCurrentLiabilities();
        final var cash = period.getBalanceSheet().getCashAndCashEquivalents();
        final var nopat = period.getIndicators().nopat();
        final var nonCurrentAssets = period.getBalanceSheet().getTotalNonCurrentAssets();

        final var nonCashWorkingCapital = currentAssets - currentLiabilities - cash;

        return nopat / (nonCashWorkingCapital + nonCurrentAssets) * 100d;
    }
}

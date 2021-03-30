package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.Period;
import com.github.davgarcia.valuetoolkit.PeriodIndicator;

public class RoaIndicator implements PeriodIndicator {

    public static final RoaIndicator INSTANCE = new RoaIndicator();

    private RoaIndicator() {
        // Empty.
    }

    @Override
    public double eval(final Period period) {
        final var netIncome = period.getIncomeStatement().getNetIncome();
        final var assets = period.getBalanceSheet().getTotalAssets();

        return netIncome / assets * 100d;
    }
}

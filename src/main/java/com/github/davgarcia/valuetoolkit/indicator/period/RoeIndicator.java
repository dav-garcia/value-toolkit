package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.Period;
import com.github.davgarcia.valuetoolkit.PeriodIndicator;

public class RoeIndicator implements PeriodIndicator {

    public static final RoeIndicator INSTANCE = new RoeIndicator();

    private RoeIndicator() {
        // Empty.
    }

    @Override
    public double eval(final Period period) {
        final var netIncome = period.getIncomeStatement().getNetIncome();
        final var equity = period.getBalanceSheet().getTotalStockholdersEquity();

        return netIncome / equity * 100d;
    }
}

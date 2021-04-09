package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import com.github.davgarcia.valuetoolkit.FiscalPeriodIndicator;

public class RoeIndicator implements FiscalPeriodIndicator {

    public static final RoeIndicator INSTANCE = new RoeIndicator();

    private RoeIndicator() {
        // Empty.
    }

    @Override
    public double eval(final FiscalPeriod period) {
        final var netIncome = period.getIncomeStatement().getNetIncome();
        final var equity = period.getBalanceSheet().getTotalStockholdersEquity();

        return netIncome / equity * 100d;
    }
}

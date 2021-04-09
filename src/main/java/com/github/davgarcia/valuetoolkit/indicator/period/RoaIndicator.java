package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import com.github.davgarcia.valuetoolkit.FiscalPeriodIndicator;

public class RoaIndicator implements FiscalPeriodIndicator {

    public static final RoaIndicator INSTANCE = new RoaIndicator();

    private RoaIndicator() {
        // Empty.
    }

    @Override
    public double eval(final FiscalPeriod period) {
        final var netIncome = period.getIncomeStatement().getNetIncome();
        final var assets = period.getBalanceSheet().getTotalAssets();

        return netIncome / assets * 100d;
    }
}

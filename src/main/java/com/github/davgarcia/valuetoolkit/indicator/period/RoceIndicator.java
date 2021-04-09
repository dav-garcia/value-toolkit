package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import com.github.davgarcia.valuetoolkit.FiscalPeriodIndicator;

public class RoceIndicator implements FiscalPeriodIndicator {

    public static final RoceIndicator INSTANCE = new RoceIndicator();

    private RoceIndicator() {
        // Empty.
    }

    @Override
    public double eval(final FiscalPeriod period) {
        final var ebitda = period.getIncomeStatement().getEbitda();
        final var depreciationAndAmortization = period.getIncomeStatement().getDepreciationAndAmortization();
        final var assets = period.getBalanceSheet().getTotalAssets();
        final var liabilities = period.getBalanceSheet().getTotalCurrentLiabilities();

        return (ebitda - depreciationAndAmortization) / (assets - liabilities) * 100d;
    }
}

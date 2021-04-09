package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import com.github.davgarcia.valuetoolkit.FiscalPeriodIndicator;

public class DebtToEquityRatioIndicator implements FiscalPeriodIndicator {

    public static final DebtToEquityRatioIndicator INSTANCE = new DebtToEquityRatioIndicator();

    private DebtToEquityRatioIndicator() {
        // Empty.
    }

    @Override
    public double eval(final FiscalPeriod period) {
        final var liabilities = period.getBalanceSheet().getTotalLiabilities();
        final var equity = period.getBalanceSheet().getTotalStockholdersEquity();

        return liabilities / equity;
    }
}

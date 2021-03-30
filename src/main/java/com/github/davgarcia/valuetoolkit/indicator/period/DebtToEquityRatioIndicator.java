package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.Period;
import com.github.davgarcia.valuetoolkit.PeriodIndicator;

public class DebtToEquityRatioIndicator implements PeriodIndicator {

    public static final DebtToEquityRatioIndicator INSTANCE = new DebtToEquityRatioIndicator();

    private DebtToEquityRatioIndicator() {
        // Empty.
    }

    @Override
    public double eval(final Period period) {
        final var liabilities = period.getBalanceSheet().getTotalLiabilities();
        final var equity = period.getBalanceSheet().getTotalStockholdersEquity();

        return liabilities / equity;
    }
}

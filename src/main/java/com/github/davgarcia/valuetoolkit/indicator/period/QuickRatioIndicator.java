package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.Period;
import com.github.davgarcia.valuetoolkit.PeriodIndicator;

public class QuickRatioIndicator implements PeriodIndicator {

    public static final QuickRatioIndicator INSTANCE = new QuickRatioIndicator();

    private QuickRatioIndicator() {
        // Empty.
    }

    @Override
    public double eval(final Period period) {
        final var cash = period.getBalanceSheet().getCashAndCashEquivalents();
        final var shortTermInvestments = period.getBalanceSheet().getShortTermInvestments();
        final var accountReceivables = period.getBalanceSheet().getNetReceivables();
        final var currentLiabilities = period.getBalanceSheet().getTotalCurrentLiabilities();

        return (cash + shortTermInvestments + accountReceivables) / currentLiabilities;
    }
}

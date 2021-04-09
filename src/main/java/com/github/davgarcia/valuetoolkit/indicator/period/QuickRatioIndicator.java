package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import com.github.davgarcia.valuetoolkit.FiscalPeriodIndicator;

public class QuickRatioIndicator implements FiscalPeriodIndicator {

    public static final QuickRatioIndicator INSTANCE = new QuickRatioIndicator();

    private QuickRatioIndicator() {
        // Empty.
    }

    @Override
    public double eval(final FiscalPeriod period) {
        final var cash = period.getBalanceSheet().getCashAndCashEquivalents();
        final var shortTermInvestments = period.getBalanceSheet().getShortTermInvestments();
        final var accountReceivables = period.getBalanceSheet().getNetReceivables();
        final var currentLiabilities = period.getBalanceSheet().getTotalCurrentLiabilities();

        return (cash + shortTermInvestments + accountReceivables) / currentLiabilities;
    }
}

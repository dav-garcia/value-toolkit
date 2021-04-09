package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import com.github.davgarcia.valuetoolkit.FiscalPeriodIndicator;

public class DaysSalesOutstandingIndicator implements FiscalPeriodIndicator {

    public static final DaysSalesOutstandingIndicator INSTANCE = new DaysSalesOutstandingIndicator();

    private DaysSalesOutstandingIndicator() {
        // Empty.
    }

    @Override
    public double eval(final FiscalPeriod period) {
        final var accountReceivables = period.getBalanceSheet().getNetReceivables();
        final var revenue = period.getIncomeStatement().getRevenue();
        final var days = period.getType() == FiscalPeriod.Type.YEAR ? 365d : 365d / 4d;

        return accountReceivables / revenue * days;
    }
}

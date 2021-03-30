package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.Period;
import com.github.davgarcia.valuetoolkit.PeriodIndicator;

public class DaysSalesOutstandingIndicator implements PeriodIndicator {

    public static final DaysSalesOutstandingIndicator INSTANCE = new DaysSalesOutstandingIndicator();

    private DaysSalesOutstandingIndicator() {
        // Empty.
    }

    @Override
    public double eval(final Period period) {
        final var accountReceivables = period.getBalanceSheet().getNetReceivables();
        final var revenue = period.getIncomeStatement().getRevenue();
        final var days = period.getType() == Period.Type.YEAR ? 365d : 365d / 4d;

        return accountReceivables / revenue * days;
    }
}

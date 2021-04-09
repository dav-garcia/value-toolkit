package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import com.github.davgarcia.valuetoolkit.FiscalPeriodIndicator;

public class NopatIndicator implements FiscalPeriodIndicator {

    public static final NopatIndicator INSTANCE = new NopatIndicator();

    private NopatIndicator() {
        // Empty.
    }

    @Override
    public double eval(final FiscalPeriod period) {
        final var operatingIncome = period.getIncomeStatement().getOperatingIncome();
        final var incomeTaxExpense = period.getIncomeStatement().getIncomeTaxExpense();
        final var incomeBeforeTax = period.getIncomeStatement().getIncomeBeforeTax();

        return operatingIncome * (1d - incomeTaxExpense / incomeBeforeTax);
    }
}

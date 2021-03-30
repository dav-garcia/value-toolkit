package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.Period;
import com.github.davgarcia.valuetoolkit.PeriodIndicator;

public class NopatIndicator implements PeriodIndicator {

    public static final NopatIndicator INSTANCE = new NopatIndicator();

    private NopatIndicator() {
        // Empty.
    }

    @Override
    public double eval(final Period period) {
        final var operatingIncome = period.getIncomeStatement().getOperatingIncome();
        final var incomeTaxExpense = period.getIncomeStatement().getIncomeTaxExpense();
        final var incomeBeforeTax = period.getIncomeStatement().getIncomeBeforeTax();

        return operatingIncome * (1d - incomeTaxExpense / incomeBeforeTax);
    }
}

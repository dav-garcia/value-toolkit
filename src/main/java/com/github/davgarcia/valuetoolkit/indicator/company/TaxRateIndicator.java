package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;

public class TaxRateIndicator extends AbstractAverageIndicator {

    public static final TaxRateIndicator INSTANCE = new TaxRateIndicator();

    private TaxRateIndicator() {
        super(2);
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Company company, final Period period) {
        final var incomeTaxExpense = period.getIncomeStatement().getIncomeTaxExpense();
        final var incomeBeforeTax = period.getIncomeStatement().getIncomeBeforeTax();

        return incomeTaxExpense / incomeBeforeTax * 100d;
    }
}

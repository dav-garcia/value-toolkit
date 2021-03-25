package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.config.EconomyConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.Period;

public class TaxRateIndicator extends AbstractAverageIndicator {

    public static final TaxRateIndicator INSTANCE = new TaxRateIndicator();

    private TaxRateIndicator() {
        super(2, Period.Status.ACTUAL);
    }

    @Override
    public double eval(final EconomyConfigProperties economy, final Business business, final Period period) {
        final var incomeTaxExpense = period.getIncomeStatement().getIncomeTaxExpense();
        final var incomeBeforeTax = period.getIncomeStatement().getIncomeBeforeTax();

        return incomeTaxExpense / incomeBeforeTax * 100d;
    }
}

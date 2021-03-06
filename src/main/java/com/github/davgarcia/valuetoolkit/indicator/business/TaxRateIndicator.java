package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Business;
import com.github.davgarcia.valuetoolkit.FiscalPeriod;

public class TaxRateIndicator extends AbstractAverageIndicator {

    public static final TaxRateIndicator INSTANCE = new TaxRateIndicator();

    private TaxRateIndicator() {
        super(2);
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Business business, final FiscalPeriod period) {
        final var incomeTaxExpense = period.getIncomeStatement().getIncomeTaxExpense();
        final var incomeBeforeTax = period.getIncomeStatement().getIncomeBeforeTax();

        return incomeTaxExpense / incomeBeforeTax * 100d;
    }
}

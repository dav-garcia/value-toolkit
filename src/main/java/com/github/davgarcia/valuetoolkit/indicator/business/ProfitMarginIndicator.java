package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.Business;
import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;

public class ProfitMarginIndicator extends AbstractAverageIndicator {

    public static final ProfitMarginIndicator INSTANCE = new ProfitMarginIndicator();

    private ProfitMarginIndicator() {
        super(4);
    }

    @Override
    protected double eval(final ValueToolkitConfigProperties params, final Business business, final FiscalPeriod period) {
        final var revenue = period.getIncomeStatement().getRevenue();
        final var netIncome = period.getIncomeStatement().getNetIncome();

        return netIncome / revenue;
    }
}

package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;

public class ProfitMarginIndicator extends AbstractAverageIndicator {

    public static final ProfitMarginIndicator INSTANCE = new ProfitMarginIndicator();

    private ProfitMarginIndicator() {
        super(4, Period.Status.ACTUAL);
    }

    @Override
    protected double eval(final ValueToolkitConfigProperties params, final Company company, final Period period) {
        final var revenue = period.getIncomeStatement().getRevenue();
        final var netIncome = period.getIncomeStatement().getNetIncome();

        return netIncome / revenue;
    }
}

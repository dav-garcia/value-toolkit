package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Business;
import org.springframework.stereotype.Component;

@Component
public class CostOfDebtIndicator implements BusinessIndicator {

    public static final CostOfDebtIndicator INSTANCE = new CostOfDebtIndicator();

    private CostOfDebtIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Business business) {
        final var newestPeriod = business.getLatestYear();

        final var interestExpense = newestPeriod.getIncomeStatement().getInterestExpense();
        final var totalDebt = newestPeriod.getBalanceSheet().getTotalDebt();

        return interestExpense / totalDebt * 100d;
    }
}

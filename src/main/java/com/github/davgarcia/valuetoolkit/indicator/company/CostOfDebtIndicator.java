package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.CompanyIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;
import org.springframework.stereotype.Component;

@Component
public class CostOfDebtIndicator implements CompanyIndicator {

    public static final CostOfDebtIndicator INSTANCE = new CostOfDebtIndicator();

    private CostOfDebtIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Company company) {
        final var newestPeriod = company.getLatestPeriod();

        final var interestExpense = newestPeriod.getIncomeStatement().getInterestExpense();
        final var totalDebt = newestPeriod.getBalanceSheet().getTotalDebt();

        return interestExpense / totalDebt * 100d;
    }
}

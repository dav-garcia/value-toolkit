package com.github.davgarcia.valuetoolkit.enrichers;

import com.github.davgarcia.valuetoolkit.BusinessDataEnricher;
import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.ComputedData;
import com.github.davgarcia.valuetoolkit.domain.Period;
import org.springframework.stereotype.Component;

@Component
public class CostOfDebtEnricher implements BusinessDataEnricher {

    @Override
    public void enrich(final Business business, final ComputedData.ComputedDataBuilder builder) {
        final var newestPeriod = business.getNewestPeriod(Period.Status.ACTUAL);
        final var interestExpense = newestPeriod.getIncomeStatement().getInterestExpense();
        final var totalDebt = newestPeriod.getBalanceSheet().getTotalDebt();

        builder.costOfDebt(interestExpense / totalDebt);
    }
}

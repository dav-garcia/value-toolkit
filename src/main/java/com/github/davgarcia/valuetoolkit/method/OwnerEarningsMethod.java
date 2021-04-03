package com.github.davgarcia.valuetoolkit.method;

import com.github.davgarcia.valuetoolkit.ValuationMethod;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;

public class OwnerEarningsMethod implements ValuationMethod {

    @Override
    public double value(final ValueToolkitConfigProperties params, final Company company) {
        final var period = company.getLatestPeriod(Period.Status.ACTUAL);

        final var ownerEarnings = company.getIndicators().getOwnerEarnings();
        final var sharesOutstanding = period.getIncomeStatement().getWeightedAverageSharesOutstanding();
        final var earningsMultiple = params.getValuationFactors().getEarningsMultiple();

        return ownerEarnings / sharesOutstanding * earningsMultiple;
    }
}

package com.github.davgarcia.valuetoolkit.method;

import com.github.davgarcia.valuetoolkit.ValuationMethod;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.Period;

public class OwnerEarningsMethod implements ValuationMethod {

    @Override
    public double value(final ValueToolkitConfigProperties params, final Business business) {
        final var period = business.getNewestPeriod(Period.Status.ACTUAL);

        final var ownerEarnings = business.getIndicators().getOwnerEarnings();
        final var sharesOutstanding = period.getIncomeStatement().getWeightedAverageSharesOutstanding();
        final var earningsMultiple = params.getValuationFactors().getEarningsMultiple();

        return ownerEarnings / sharesOutstanding * earningsMultiple;
    }
}

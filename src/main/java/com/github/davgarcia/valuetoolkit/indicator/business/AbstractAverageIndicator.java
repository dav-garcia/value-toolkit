package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Business;
import com.github.davgarcia.valuetoolkit.FiscalPeriod;

public abstract class AbstractAverageIndicator implements BusinessIndicator {

    private final int maxPeriods;

    protected AbstractAverageIndicator(final int maxPeriods) {
        this.maxPeriods = maxPeriods;
    }

    @Override
    @SuppressWarnings("java:S3518")
    public double eval(final ValueToolkitConfigProperties params, final Business business) {
        double sum = 0d;
        int num = 0;
        for (final var period : business.getYears()) {
            if (num >= maxPeriods) {
                break;
            }
            sum += eval(params, business, period);
            num++;
        }
        return num > 1 ? sum / num : sum;
    }

    protected abstract double eval(final ValueToolkitConfigProperties params, final Business business, final FiscalPeriod period);
}

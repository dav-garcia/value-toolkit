package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Business;
import com.github.davgarcia.valuetoolkit.Period;

public abstract class AbstractAverageIndicator implements BusinessIndicator {

    private final int maxPeriods;
    private final Period.Status withStatus;

    protected AbstractAverageIndicator(final int maxPeriods, final Period.Status withStatus) {
        this.maxPeriods = maxPeriods;
        this.withStatus = withStatus;
    }

    @Override
    @SuppressWarnings("java:S3518")
    public double eval(final ValueToolkitConfigProperties params, final Business business) {
        double sum = 0d;
        int num = 0;
        for (final var period : business.getPeriods()) {
            if (num >= maxPeriods) {
                break;
            }
            if (period.getStatus() == withStatus || withStatus == null) {
                sum += eval(params, business, period);
                num++;
            }
        }
        return num > 1 ? sum / num : sum;
    }

    protected abstract double eval(final ValueToolkitConfigProperties params, final Business business, final Period period);
}

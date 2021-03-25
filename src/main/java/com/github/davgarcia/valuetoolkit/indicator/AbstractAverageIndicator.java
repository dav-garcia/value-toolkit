package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.EconomyConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.Period;

public abstract class AbstractAverageIndicator implements BusinessIndicator {

    private final int maxPeriods;
    private final Period.Status withStatus;

    protected AbstractAverageIndicator(final int maxPeriods, final Period.Status withStatus) {
        this.maxPeriods = maxPeriods;
        this.withStatus = withStatus;
    }

    @Override
    public double eval(final EconomyConfigProperties economy, final Business business) {
        double sum = 0d;
        int num = 0;
        for (final var period : business.getPeriods()) {
            if (num >= maxPeriods) {
                break;
            }
            if (period.getStatus() == withStatus || withStatus == null) {
                sum += eval(economy, business, period);
                num++;
            }
        }
        return num > 1 ? sum / num : sum;
    }

    protected abstract double eval(final EconomyConfigProperties economy, final Business business, final Period period);
}

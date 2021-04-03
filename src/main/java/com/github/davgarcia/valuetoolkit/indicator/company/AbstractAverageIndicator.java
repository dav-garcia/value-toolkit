package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.CompanyIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;

public abstract class AbstractAverageIndicator implements CompanyIndicator {

    private final int maxPeriods;

    protected AbstractAverageIndicator(final int maxPeriods) {
        this.maxPeriods = maxPeriods;
    }

    @Override
    @SuppressWarnings("java:S3518")
    public double eval(final ValueToolkitConfigProperties params, final Company company) {
        double sum = 0d;
        int num = 0;
        for (final var period : company.getYearPeriods()) {
            if (num >= maxPeriods) {
                break;
            }
            sum += eval(params, company, period);
            num++;
        }
        return num > 1 ? sum / num : sum;
    }

    protected abstract double eval(final ValueToolkitConfigProperties params, final Company company, final Period period);
}

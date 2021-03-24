package com.github.davgarcia.valuetoolkit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class Business {

    BusinessLocator locator;
    BusinessProfile profile;
    /**
     * Financial data periods inversely ordered by date (newest first, oldest last).
     */
    List<Period> periods;
    @NonFinal
    ComputedData computedData;

    public void addPeriod(final Period period) {
        periods.add(0, period);
    }

    public Period getNewestPeriod() {
        return periods.get(0);
    }

    public Period getNewestPeriod(final Period.Status withStatus) {
        return periods.stream()
                .filter(p -> p.getStatus() == withStatus)
                .findFirst()
                .orElse(null);
    }

    public Period getOldestPeriod() {
        return periods.get(periods.size() - 1);
    }

    public Period getOldestPeriod(final Period.Status withStatus) {
        for (int i = periods.size() - 1; i >= 0; i--) {
            final var period = periods.get(i);
            if (period.getStatus() == withStatus) {
                return period;
            }
        }
        return null;
    }
}

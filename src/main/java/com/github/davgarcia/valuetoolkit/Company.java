package com.github.davgarcia.valuetoolkit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.util.List;

/**
 * Root entity aggregating all company data.
 */
@Value
@AllArgsConstructor
@Builder
public class Company {

    /**
     * Company identifier.
     */
    CompanyLocator locator;

    /**
     * Company profile.
     */
    CompanyProfile profile;

    /**
     * Financial data periods inversely ordered by date (newest first, oldest last).
     */
    List<Period> periods;

    /**
     * Company specific estimates and other parameters used during valuation.
     */
    CompanyEstimates estimates;

    /**
     * Financial indicators, manually set afterwards.
     */
    @NonFinal
    @Setter
    @EqualsAndHashCode.Exclude
    CompanyIndicators indicators;

    public void addPeriod(final Period period) {
        periods.add(0, period);
    }

    public Period getLatestPeriod() {
        return periods.get(0);
    }

    public Period getLatestPeriod(final Period.Status withStatus) {
        return periods.stream()
                .filter(p -> p.getStatus() == withStatus)
                .findFirst()
                .orElse(null);
    }

    public Period getEearliestPeriod() {
        return periods.get(periods.size() - 1);
    }

    public Period getEearliestPeriod(final Period.Status withStatus) {
        for (int i = periods.size() - 1; i >= 0; i--) {
            final var period = periods.get(i);
            if (period.getStatus() == withStatus) {
                return period;
            }
        }
        return null;
    }

    public Period getPreviousPeriod(final Period period) {
        for (int i = 0; i < periods.size() - 1; i++) { // Stop at 1 item before end of list.
            if (periods.get(i) == period) {
                return periods.get(i + 1);
            }
        }
        return null;
    }
}

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
     * Fiscal year periods inversely ordered by date (newest first, oldest last).
     */
    List<Period> yearPeriods;

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
        yearPeriods.add(0, period);
    }

    public Period getLatestPeriod() {
        return yearPeriods.get(0);
    }

    public Period getEearliestPeriod() {
        return yearPeriods.get(yearPeriods.size() - 1);
    }

    public Period getPreviousPeriod(final Period period) {
        for (int i = 0; i < yearPeriods.size() - 1; i++) { // Stop at 1 item before end of list.
            if (yearPeriods.get(i) == period) {
                return yearPeriods.get(i + 1);
            }
        }
        return null;
    }
}

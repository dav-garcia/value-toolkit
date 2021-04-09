package com.github.davgarcia.valuetoolkit;

import org.threeten.extra.YearQuarter;

import java.time.Year;
import java.util.List;

public interface BusinessDataProvider {

    BusinessProfile getBusinessProfile(final BusinessLocator locator);
    List<FiscalPeriod> getFiscalYears(final BusinessLocator locator, final Year first, final Year last);
    List<FiscalPeriod> getQuarters(final BusinessLocator locator, final YearQuarter first, final YearQuarter last);
}

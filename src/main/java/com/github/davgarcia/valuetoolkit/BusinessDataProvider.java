package com.github.davgarcia.valuetoolkit;

import java.time.LocalDate;
import java.util.List;

public interface BusinessDataProvider {

    BusinessProfile getBusinessProfile(BusinessLocator locator);
    List<Period> getFiscalYears(BusinessLocator locator, LocalDate first, LocalDate last);
    List<Period> getQuarters(BusinessLocator locator, LocalDate first, LocalDate last);
}

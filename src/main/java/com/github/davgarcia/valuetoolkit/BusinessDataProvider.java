package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.domain.BusinessLocator;
import com.github.davgarcia.valuetoolkit.domain.BusinessProfile;
import com.github.davgarcia.valuetoolkit.domain.Period;

import java.time.LocalDate;
import java.util.List;

public interface BusinessDataProvider {

    BusinessProfile getBusinessProfile(BusinessLocator locator);
    List<Period> getFiscalYears(BusinessLocator locator, LocalDate first, LocalDate last);
    List<Period> getQuarters(BusinessLocator locator, LocalDate first, LocalDate last);
}

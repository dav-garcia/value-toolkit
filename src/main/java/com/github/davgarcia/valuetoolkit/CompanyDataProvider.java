package com.github.davgarcia.valuetoolkit;

import java.time.LocalDate;
import java.util.List;

public interface CompanyDataProvider {

    CompanyProfile getCompanyProfile(CompanyLocator locator);
    List<Period> getFiscalYears(CompanyLocator locator, LocalDate first, LocalDate last);
    List<Period> getQuarters(CompanyLocator locator, LocalDate first, LocalDate last);
}

package com.github.davgarcia.valuetoolkit.adapter.marketstack;

import com.github.davgarcia.valuetoolkit.CompanyDataProvider;
import com.github.davgarcia.valuetoolkit.CompanyLocator;
import com.github.davgarcia.valuetoolkit.CompanyProfile;
import com.github.davgarcia.valuetoolkit.Period;

import java.time.LocalDate;
import java.util.List;

/**
 * Market Stack API
 * <p>
 * Doc: https://marketstack.com/documentation
 */
public class MarketStackAdapter implements CompanyDataProvider {

    @Override
    public CompanyProfile getCompanyProfile(final CompanyLocator locator) {
        return null;
    }

    @Override
    public List<Period> getFiscalYears(final CompanyLocator locator, final LocalDate first, final LocalDate last) {
        return null;
    }

    @Override
    public List<Period> getQuarters(final CompanyLocator locator, final LocalDate first, final LocalDate last) {
        return null;
    }
}

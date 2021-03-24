package com.github.davgarcia.valuetoolkit.adapter.alphavantage;

import com.github.davgarcia.valuetoolkit.BusinessDataProvider;
import com.github.davgarcia.valuetoolkit.domain.BusinessLocator;
import com.github.davgarcia.valuetoolkit.domain.BusinessProfile;
import com.github.davgarcia.valuetoolkit.domain.Period;

import java.time.LocalDate;
import java.util.List;

/**
 * Alpha Vantage API
 * <p>
 * Doc: https://www.alphavantage.co/documentation/
 */
public class AlphaVantageAdapter implements BusinessDataProvider {

    @Override
    public BusinessProfile getBusinessProfile(final BusinessLocator locator) {
        return null;
    }

    @Override
    public List<Period> getFiscalYears(final BusinessLocator locator, final LocalDate first, final LocalDate last) {
        return null;
    }

    @Override
    public List<Period> getQuarters(final BusinessLocator locator, final LocalDate first, final LocalDate last) {
        return null;
    }
}

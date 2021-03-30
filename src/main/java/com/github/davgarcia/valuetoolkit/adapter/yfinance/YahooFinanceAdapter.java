package com.github.davgarcia.valuetoolkit.adapter.yfinance;

import com.github.davgarcia.valuetoolkit.BusinessDataProvider;
import com.github.davgarcia.valuetoolkit.BusinessLocator;
import com.github.davgarcia.valuetoolkit.BusinessProfile;
import com.github.davgarcia.valuetoolkit.Period;

import java.time.LocalDate;
import java.util.List;

/**
 * Yahoo! Finance API
 * <p>
 * Python Lib: https://github.com/ranaroussi/yfinance
 */
public class YahooFinanceAdapter implements BusinessDataProvider {

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

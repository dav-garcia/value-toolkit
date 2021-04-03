package com.github.davgarcia.valuetoolkit.adapter.yfinance;

import com.github.davgarcia.valuetoolkit.CompanyDataProvider;
import com.github.davgarcia.valuetoolkit.CompanyLocator;
import com.github.davgarcia.valuetoolkit.CompanyProfile;
import com.github.davgarcia.valuetoolkit.Period;

import java.time.LocalDate;
import java.util.List;

/**
 * Yahoo! Finance API
 * <p>
 * Python Lib: https://github.com/ranaroussi/yfinance
 */
public class YahooFinanceAdapter implements CompanyDataProvider {

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

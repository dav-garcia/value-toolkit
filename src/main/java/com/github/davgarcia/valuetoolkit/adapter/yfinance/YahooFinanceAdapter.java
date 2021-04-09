package com.github.davgarcia.valuetoolkit.adapter.yfinance;

import com.github.davgarcia.valuetoolkit.BusinessDataProvider;
import com.github.davgarcia.valuetoolkit.BusinessLocator;
import com.github.davgarcia.valuetoolkit.BusinessProfile;
import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import org.threeten.extra.YearQuarter;

import java.time.Year;
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
    public List<FiscalPeriod> getFiscalYears(final BusinessLocator locator, final Year first, final Year last) {
        return null;
    }

    @Override
    public List<FiscalPeriod>getQuarters(final BusinessLocator locator, final YearQuarter first, final YearQuarter last) {
        return null;
    }
}

package com.github.davgarcia.valuetoolkit.adapter.yfinance;

import com.github.davgarcia.valuetoolkit.BusinessDataProvider;
import com.github.davgarcia.valuetoolkit.domain.BusinessLocator;
import com.github.davgarcia.valuetoolkit.domain.BusinessProfile;

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
}

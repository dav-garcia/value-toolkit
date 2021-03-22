package com.github.davgarcia.valuetoolkit.adapter.fmp;

import com.github.davgarcia.valuetoolkit.BusinessDataProvider;
import com.github.davgarcia.valuetoolkit.domain.BusinessLocator;
import com.github.davgarcia.valuetoolkit.domain.BusinessProfile;

/**
 * Financial Modeling Prep API
 * <p>
 * Doc: https://financialmodelingprep.com/developer/docs/
 */
public class FmpAdapter implements BusinessDataProvider {

    private final FmpFeignClient feignClient;

    public FmpAdapter(final FmpFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public BusinessProfile getBusinessProfile(final BusinessLocator locator) {
        final var suffix = FmpExchange.suffixForValue(locator.getExchange());
        final var id = suffix == null ? locator.getSymbol() :
                String.format("%s.%s", locator.getSymbol(), suffix);

        final var profiles = feignClient.getBusinessProfile(id);
        return profiles.length == 1 ? profiles[0] : null;
    }
}

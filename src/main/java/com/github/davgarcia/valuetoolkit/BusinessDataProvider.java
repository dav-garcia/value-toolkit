package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.domain.BusinessLocator;
import com.github.davgarcia.valuetoolkit.domain.BusinessProfile;

public interface BusinessDataProvider {

    BusinessProfile getBusinessProfile(BusinessLocator locator);
}

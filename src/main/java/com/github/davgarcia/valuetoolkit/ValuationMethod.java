package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.config.EconomyConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;

public interface ValuationMethod {

    double value(final EconomyConfigProperties economy, final Business business);
}

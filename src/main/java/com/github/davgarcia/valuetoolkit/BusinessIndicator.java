package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.config.EconomyConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;

public interface BusinessIndicator {

    double eval(final EconomyConfigProperties economy, final Business business);
}

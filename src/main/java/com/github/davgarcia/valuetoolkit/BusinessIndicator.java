package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;

/**
 * Computes a business indicator, i.e. a value that depends on current and/or historic financial data.
 */
public interface BusinessIndicator {

    double eval(final ValueToolkitConfigProperties params, final Business business);
}

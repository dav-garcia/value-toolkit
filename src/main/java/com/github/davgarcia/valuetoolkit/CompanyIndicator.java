package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;

/**
 * Computes a company indicator, i.e. a value that depends on current and/or historic financial data.
 */
public interface CompanyIndicator {

    double eval(final ValueToolkitConfigProperties params, final Company company);
}

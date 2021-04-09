package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;

public interface ValuationMethod {

    double value(final ValueToolkitConfigProperties params, final Business business);
}

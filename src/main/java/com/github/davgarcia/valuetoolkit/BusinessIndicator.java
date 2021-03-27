package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;

public interface BusinessIndicator {

    double eval(final ValueToolkitConfigProperties params, final Business business);
}

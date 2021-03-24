package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.ComputedData;

public interface BusinessDataEnricher {

    void enrich(final Business business, final ComputedData.ComputedDataBuilder builder);
}

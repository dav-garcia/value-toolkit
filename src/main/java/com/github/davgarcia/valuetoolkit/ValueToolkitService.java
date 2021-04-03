package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;

public class ValueToolkitService {

    private final ValueToolkitConfigProperties params;
    private final CompanyDataProvider provider;

    public ValueToolkitService(final ValueToolkitConfigProperties params, final CompanyDataProvider provider) {
        this.params = params;
        this.provider = provider;
    }

    public CompanyResult analyze(final CompanyLocator locator) {
        return null;
    }
}

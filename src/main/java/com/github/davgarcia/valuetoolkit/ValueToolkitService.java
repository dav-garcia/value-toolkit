package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;

public class ValueToolkitService {

    private final ValueToolkitConfigProperties params;
    private final BusinessDataProvider provider;

    public ValueToolkitService(final ValueToolkitConfigProperties params, final BusinessDataProvider provider) {
        this.params = params;
        this.provider = provider;
    }

    public BusinessResult analyze(final BusinessLocator locator) {
        return null;
    }
}

package com.github.davgarcia.valuetoolkit;

import org.springframework.stereotype.Service;

@Service
public class ValueToolkitService {

    private final BusinessDataProvider provider;

    public ValueToolkitService(final BusinessDataProvider provider) {
        this.provider = provider;
    }
}

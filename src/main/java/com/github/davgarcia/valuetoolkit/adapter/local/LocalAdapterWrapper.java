package com.github.davgarcia.valuetoolkit.adapter.local;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.davgarcia.valuetoolkit.BusinessDataProvider;
import com.github.davgarcia.valuetoolkit.domain.BusinessLocator;
import com.github.davgarcia.valuetoolkit.domain.BusinessProfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LocalAdapterWrapper implements BusinessDataProvider {

    private static final String BUSINESS_PROFILE = "profile";

    private final BusinessDataProvider wrappedAdapter;
    private final Path localDir;
    private final ObjectMapper objectMapper;

    public LocalAdapterWrapper(final BusinessDataProvider wrappedAdapter, final Path localDir) {
        this.wrappedAdapter = wrappedAdapter;
        this.localDir = localDir;
        objectMapper = new ObjectMapper();

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public BusinessProfile getBusinessProfile(final BusinessLocator locator) {
        var profile = load(locator, BusinessProfile.class);
        if (profile == null) {
            profile = wrappedAdapter.getBusinessProfile(locator);
            save(locator, profile);
        }
        return profile;
    }

    private <T> T load(final BusinessLocator locator, Class<T> type) {
        final var path = buildPath(locator, BUSINESS_PROFILE);
        if (!Files.isRegularFile(path)) {
            return null;
        }
        try (final var is = Files.newInputStream(path)) {
            return objectMapper.readValue(is, type);
        } catch (IOException e) {
            throw new RuntimeException("Error loading local data: " + path, e);
        }
    }

    private <T> void save(final BusinessLocator locator, final T data) {
        final var path = buildPath(locator, BUSINESS_PROFILE);
        try (final var os = Files.newOutputStream(path)) {
            objectMapper.writeValue(os, data);
        } catch (IOException e) {
            throw new RuntimeException("Error saving local data: " + path, e);
        }
    }

    private Path buildPath(final BusinessLocator locator, final String type) {
        return localDir.resolve(String.format("%s.%s-%s.json", locator.getExchange(), locator.getSymbol(), type));
    }
}

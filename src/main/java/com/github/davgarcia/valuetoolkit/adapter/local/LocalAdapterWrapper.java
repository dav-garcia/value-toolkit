package com.github.davgarcia.valuetoolkit.adapter.local;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.davgarcia.valuetoolkit.BusinessDataProvider;
import com.github.davgarcia.valuetoolkit.domain.BusinessLocator;
import com.github.davgarcia.valuetoolkit.domain.BusinessProfile;
import com.github.davgarcia.valuetoolkit.domain.Period;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class LocalAdapterWrapper implements BusinessDataProvider {

    private static final String BUSINESS_PROFILE = "profile";

    private final BusinessDataProvider wrappedAdapter;
    private final Path localDir;
    private final ObjectMapper objectMapper;

    public LocalAdapterWrapper(final BusinessDataProvider wrappedAdapter, final Path localDir) {
        this.wrappedAdapter = wrappedAdapter;
        this.localDir = localDir;
        objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(SerializationFeature.INDENT_OUTPUT);
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

    @Override
    public List<Period> getFiscalYears(final BusinessLocator locator, final LocalDate first, final LocalDate last) {
        return null;
    }

    @Override
    public List<Period> getQuarters(final BusinessLocator locator, final LocalDate first, final LocalDate last) {
        return null;
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

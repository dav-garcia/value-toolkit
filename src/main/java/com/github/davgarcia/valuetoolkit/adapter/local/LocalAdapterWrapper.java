package com.github.davgarcia.valuetoolkit.adapter.local;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LocalAdapterWrapper implements BusinessDataProvider {

    private static final String PROFILE = "profile";
    private static final String PERIODS = "periods";

    private final BusinessDataProvider wrappedAdapter;
    private final Path localDir;
    private final ObjectMapper objectMapper;
    private final JavaType profileType;
    private final JavaType periodsType;

    public LocalAdapterWrapper(final BusinessDataProvider wrappedAdapter, final Path localDir) {
        this.wrappedAdapter = wrappedAdapter;
        this.localDir = localDir;
        objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        profileType = objectMapper.getTypeFactory().constructType(BusinessProfile.class);
        periodsType = objectMapper.getTypeFactory().constructCollectionType(List.class, Period.class);
    }

    @Override
    public BusinessProfile getBusinessProfile(final BusinessLocator locator) {
        BusinessProfile profile = load(locator, PROFILE, profileType);
        if (profile == null) {
            profile = wrappedAdapter.getBusinessProfile(locator);
            save(locator, PROFILE, profile);
        }
        return profile;
    }

    @Override
    public List<Period> getFiscalYears(final BusinessLocator locator, final LocalDate first, final LocalDate last) {
        final var suffix = String.format("%s-%s-%s", PERIODS, format(first), format(last));

        List<Period> periods = load(locator, suffix, periodsType);
        if (periods == null) {
            periods = wrappedAdapter.getFiscalYears(locator, first, last);
            save(locator, suffix, periods);
        }
        return periods;
    }

    @Override
    public List<Period> getQuarters(final BusinessLocator locator, final LocalDate first, final LocalDate last) {
        return null;
    }

    private String format(final LocalDate date) {
        return date.format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    private <T> T load(final BusinessLocator locator, final String suffix, final JavaType type) {
        final var path = buildPath(locator, suffix);
        if (!Files.isRegularFile(path)) {
            return null;
        }
        try (final var is = Files.newInputStream(path)) {
            return objectMapper.readValue(is, type);
        } catch (IOException e) {
            throw new RuntimeException("Error loading local data: " + path, e);
        }
    }

    private <T> void save(final BusinessLocator locator, final String suffix, final T data) {
        final var path = buildPath(locator, suffix);
        try (final var os = Files.newOutputStream(path)) {
            objectMapper.writeValue(os, data);
        } catch (IOException e) {
            throw new RuntimeException("Error saving local data: " + path, e);
        }
    }

    private Path buildPath(final BusinessLocator locator, final String suffix) {
        return localDir.resolve(String.format("%s.%s-%s.json", locator.getExchange(), locator.getSymbol(), suffix));
    }
}

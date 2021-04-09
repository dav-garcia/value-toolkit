package com.github.davgarcia.valuetoolkit.adapter.local;

import com.github.davgarcia.valuetoolkit.BusinessDataProvider;
import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LocalAdapterWrapperTest {

    private static final String PROFILE_NAME = "NASDAQ.MSFT-profile.json";
    private static final String PERIODS_NAME = "NASDAQ.MSFT-fy-2016-2020.json";
    private static final Year FIRST_YEAR = Year.of(2016);
    private static final Year LAST_YEAR = Year.of(2020);

    @Mock
    private BusinessDataProvider provider;
    private FileSystem fileSystem;
    private LocalAdapterWrapper sut;

    @BeforeEach
    public void setup() throws IOException {
        if (fileSystem != null) {
            fileSystem.close();
        }
        fileSystem = Jimfs.newFileSystem(Configuration.unix());

        sut = new LocalAdapterWrapper(provider, fileSystem.getPath(""));
    }

    @Test
    void givenNoLocalProfileWhenGetThenSave() throws IOException {
        final var locator = DomainObjectMother.businessLocator();
        final var profile = DomainObjectMother.businessProfile();
        doReturn(profile).when(provider).getBusinessProfile(locator);

        final var resultObject = sut.getBusinessProfile(locator);
        final var resultJson = Files.readString(fileSystem.getPath(PROFILE_NAME));

        assertThat(resultObject).isEqualTo(profile);
        assertThat(resultJson).isEqualTo(loadResource("local", PROFILE_NAME));
    }

    @Test
    void givenLocalProfileWhenGetThenReturnIt() throws IOException {
        Files.writeString(fileSystem.getPath(PROFILE_NAME), loadResource("local", PROFILE_NAME));

        final var result = sut.getBusinessProfile(DomainObjectMother.businessLocator());

        assertThat(result).isEqualTo(DomainObjectMother.businessProfile());
        verify(provider, never()).getBusinessProfile(any());
    }

    @Test
    void givenNoLocalPeriodsWhenGetThenSave() throws IOException {
        final var locator = DomainObjectMother.businessLocator();
        final var periods = DomainObjectMother.business().getYears();
        doReturn(periods).when(provider).getFiscalYears(locator, FIRST_YEAR, LAST_YEAR);

        final var resultObject = sut.getFiscalYears(locator, FIRST_YEAR, LAST_YEAR);
        final var resultJson = Files.readString(fileSystem.getPath(PERIODS_NAME));

        assertThat(resultObject).isEqualTo(periods);
        assertThat(resultJson).isEqualTo(loadResource("local", PERIODS_NAME));
    }

    @Test
    void givenLocalPeriodsWhenGetThenReturnThem() throws IOException {
        Files.writeString(fileSystem.getPath(PERIODS_NAME), loadResource("local", PERIODS_NAME));

        final var result = sut.getFiscalYears(DomainObjectMother.businessLocator(), FIRST_YEAR, LAST_YEAR);

        assertThat(result).isEqualTo(DomainObjectMother.business().getYears());
        verify(provider, never()).getFiscalYears(any(), any(), any());
    }

    private String loadResource(final String dir, final String name) throws IOException {
        final var content = IOUtils.resourceToString(String.format("/%s/%s", dir, name), StandardCharsets.UTF_8);
        return StringUtils.stripEnd(content, null); // Some editors add a newline at EOF, but Jackson doesn't.
    }
}

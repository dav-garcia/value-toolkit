package com.github.davgarcia.valuetoolkit.adapter.local;

import com.github.davgarcia.valuetoolkit.BusinessDataProvider;
import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import com.github.davgarcia.valuetoolkit.domain.BusinessLocator;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class LocalAdapterWrapperTest {

    private static final String PROFILE_FILENAME = "NASDAQ.MSFT-profile.json";

    @Mock
    private BusinessDataProvider provider;
    private FileSystem fileSystem;
    private LocalAdapterWrapper sut;

    @Before
    public void setup() throws IOException {
        if (fileSystem != null) {
            fileSystem.close();
        }
        fileSystem = Jimfs.newFileSystem(Configuration.unix());

        sut = new LocalAdapterWrapper(provider, fileSystem.getPath(""));
    }

    @Test
    public void givenNoLocalFileWhenGetThenSave() throws IOException {
        final var locator = new BusinessLocator("NASDAQ", "MSFT");
        final var profile = DomainObjectMother.businessProfile();
        doReturn(profile).when(provider).getBusinessProfile(locator);

        final var resultObject = sut.getBusinessProfile(locator);
        final var resultJson = Files.readString(fileSystem.getPath(PROFILE_FILENAME));

        assertThat(resultObject).isEqualTo(profile);
        assertThat(resultJson).isEqualTo(loadResource(PROFILE_FILENAME));
    }

    @Test
    public void givenLocalFileWhenGetThenReturnIt() throws IOException {
        final var locator = new BusinessLocator("NASDAQ", "MSFT");
        Files.writeString(fileSystem.getPath(PROFILE_FILENAME), loadResource(PROFILE_FILENAME));

        final var result = sut.getBusinessProfile(locator);

        assertThat(result).isEqualTo(DomainObjectMother.businessProfile());
        verify(provider, never()).getBusinessProfile(any());
    }

    private String loadResource(final String name) throws IOException {
        final var path = ResourceUtils.getFile("classpath:local/" + name).toPath();
        return StringUtils.stripEnd(Files.readString(path, StandardCharsets.UTF_8), null);
    }
}

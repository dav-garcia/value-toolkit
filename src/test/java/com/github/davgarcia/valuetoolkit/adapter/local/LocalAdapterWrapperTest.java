package com.github.davgarcia.valuetoolkit.adapter.local;

import com.github.davgarcia.valuetoolkit.BusinessDataProvider;
import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
class LocalAdapterWrapperTest {

    private static final String PROFILE_NAME = "NASDAQ.MSFT-profile.json";

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
    void givenNoLocalFileWhenGetThenSave() throws IOException {
        final var profile = DomainObjectMother.businessProfile();
        doReturn(profile).when(provider).getBusinessProfile(DomainObjectMother.businessLocator());

        final var resultObject = sut.getBusinessProfile(DomainObjectMother.businessLocator());
        final var resultJson = Files.readString(fileSystem.getPath(PROFILE_NAME));

        assertThat(resultObject).isEqualTo(profile);
        assertThat(resultJson).isEqualTo(loadResource("local", PROFILE_NAME));
    }

    @Test
    void givenLocalFileWhenGetThenReturnIt() throws IOException {
        Files.writeString(fileSystem.getPath(PROFILE_NAME), loadResource("local", PROFILE_NAME));

        final var result = sut.getBusinessProfile(DomainObjectMother.businessLocator());

        assertThat(result).isEqualTo(DomainObjectMother.businessProfile());
        verify(provider, never()).getBusinessProfile(any());
    }

    private String loadResource(final String dir, final String name) throws IOException {
        final var path = ResourceUtils.getFile(String.format("classpath:%s/%s", dir, name)).toPath();
        return StringUtils.stripEnd(Files.readString(path, StandardCharsets.UTF_8), null);
    }
}

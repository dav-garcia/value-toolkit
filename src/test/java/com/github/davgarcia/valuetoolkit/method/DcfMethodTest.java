package com.github.davgarcia.valuetoolkit.method;

import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class DcfMethodTest {

    private final DcfMethod sut = new DcfMethod();

    @Test
    void givenBusinessThenComputeDcf() throws IOException {
        final var economy = DomainObjectMother.economy();
        final var business = DomainObjectMother.business();

        final var result = sut.value(economy, business);

        assertThat(result).isEqualTo(258.29604799207203);
    }
}

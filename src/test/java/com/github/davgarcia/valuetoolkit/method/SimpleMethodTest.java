package com.github.davgarcia.valuetoolkit.method;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleMethodTest {

    private final SimpleMethod sut = new SimpleMethod();

    @Test
    void givenCompanyThenComputeSimple() throws IOException {
        final var params = DomainObjectMother.params();
        final var business = DomainObjectMother.business();

        final var result = sut.value(params, business);

        assertThat(result).isEqualTo(255.8534469945909);
    }
}

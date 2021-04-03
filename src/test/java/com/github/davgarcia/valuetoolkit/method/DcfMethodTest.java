package com.github.davgarcia.valuetoolkit.method;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class DcfMethodTest {

    private final DcfMethod sut = new DcfMethod();

    @Test
    void givenCompanyThenComputeDcf() throws IOException {
        final var params = DomainObjectMother.params();
        final var company = DomainObjectMother.company();

        final var result = sut.value(params, company);

        assertThat(result).isEqualTo(285.73646272031317);
    }
}

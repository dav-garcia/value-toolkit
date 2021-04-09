package com.github.davgarcia.valuetoolkit.method;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OwnerEarningsMethodTest {

    private final OwnerEarningsMethod sut = new OwnerEarningsMethod();

    @Test
    void givenCompanyThenComputeOwnerEarnings() {
        final var params = DomainObjectMother.params();
        final var business = DomainObjectMother.business();

        final var result = sut.value(params, business);

        assertThat(result).isEqualTo(284.9468562773711);
    }
}

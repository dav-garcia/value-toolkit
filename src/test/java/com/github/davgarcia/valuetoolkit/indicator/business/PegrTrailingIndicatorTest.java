package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PegrTrailingIndicatorTest {

    private final PegrTrailingIndicator sut = PegrTrailingIndicator.INSTANCE;

    @Test
    void givenCompanyThenComputePegr() {
        final var params = DomainObjectMother.params();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(params, business);

        assertThat(result).isEqualTo(2.8784646768667823);
    }
}

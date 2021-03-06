package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PerTrailingIndicatorTest {

    private final PerTrailingIndicator sut = PerTrailingIndicator.INSTANCE;

    @Test
    void givenCompanyThenComputePer() {
        final var params = DomainObjectMother.params();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(params, business);

        assertThat(result).isEqualTo(39.994323298931825);
    }
}

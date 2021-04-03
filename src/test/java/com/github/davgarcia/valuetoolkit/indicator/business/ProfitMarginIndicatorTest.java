package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitMarginIndicatorTest {

    private final ProfitMarginIndicator sut = ProfitMarginIndicator.INSTANCE;

    @Test
    void givenBusinessThenComputeProfitMargin() {
        final var params = DomainObjectMother.params();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(params, business);

        assertThat(result).isEqualTo(0.25183174323257096);
    }
}

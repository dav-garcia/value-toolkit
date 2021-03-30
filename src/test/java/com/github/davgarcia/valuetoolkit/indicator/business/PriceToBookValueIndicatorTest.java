package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PriceToBookValueIndicatorTest {

    private final PriceToBookValueIndicator sut = PriceToBookValueIndicator.INSTANCE;

    @Test
    void givenBusinessThenComputePriceToBookValue() {
        final var params = DomainObjectMother.params();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(params, business);

        assertThat(result).isEqualTo(14.96981192520963);
    }
}

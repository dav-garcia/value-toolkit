package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PriceToFcfIndicatorTest {

    private final PriceToFcfIndicator sut = PriceToFcfIndicator.INSTANCE;

    @Test
    void givenBusinessThenComputePriceToFcf() {
        final var params = DomainObjectMother.params();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(params, business);

        assertThat(result).isEqualTo(39.151713976212584);
    }
}

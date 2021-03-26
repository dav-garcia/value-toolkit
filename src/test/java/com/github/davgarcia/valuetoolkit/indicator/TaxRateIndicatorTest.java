package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaxRateIndicatorTest {

    private final TaxRateIndicator sut = TaxRateIndicator.INSTANCE;

    @Test
    void givenBusinessThenComputeTaxRate() {
        final var economy = DomainObjectMother.economy();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(economy, business);

        assertThat(result).isEqualTo(13.344470328232617);
    }
}

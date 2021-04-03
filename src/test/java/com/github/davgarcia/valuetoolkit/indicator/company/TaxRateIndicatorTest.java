package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaxRateIndicatorTest {

    private final TaxRateIndicator sut = TaxRateIndicator.INSTANCE;

    @Test
    void givenCompanyThenComputeTaxRate() {
        final var params = DomainObjectMother.params();
        final var company = DomainObjectMother.company();

        final var result = sut.eval(params, company);

        assertThat(result).isEqualTo(13.344470328232617);
    }
}

package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PriceToBookValueIndicatorTest {

    private final PriceToBookValueIndicator sut = PriceToBookValueIndicator.INSTANCE;

    @Test
    void givenCompanyThenComputePriceToBookValue() {
        final var params = DomainObjectMother.params();
        final var company = DomainObjectMother.company();

        final var result = sut.eval(params, company);

        assertThat(result).isEqualTo(14.96981192520963);
    }
}

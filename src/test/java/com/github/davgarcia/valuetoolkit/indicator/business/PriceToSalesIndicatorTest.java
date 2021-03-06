package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PriceToSalesIndicatorTest {

    private final PriceToSalesIndicator sut = PriceToSalesIndicator.INSTANCE;

    @Test
    void givenCompanyThenComputePriceToSales() {
        final var params = DomainObjectMother.params();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(params, business);

        assertThat(result).isEqualTo(12.383236933188826);
    }
}

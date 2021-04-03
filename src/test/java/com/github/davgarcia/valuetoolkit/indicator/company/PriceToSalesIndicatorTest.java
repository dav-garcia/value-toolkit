package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PriceToSalesIndicatorTest {

    private final PriceToSalesIndicator sut = PriceToSalesIndicator.INSTANCE;

    @Test
    void givenCompanyThenComputePriceToSales() {
        final var params = DomainObjectMother.params();
        final var company = DomainObjectMother.company();

        final var result = sut.eval(params, company);

        assertThat(result).isEqualTo(12.383236933188826);
    }
}

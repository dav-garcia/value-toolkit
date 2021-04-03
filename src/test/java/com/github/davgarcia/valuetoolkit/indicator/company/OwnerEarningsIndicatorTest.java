package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OwnerEarningsIndicatorTest {

    private final OwnerEarningsIndicator sut = OwnerEarningsIndicator.INSTANCE;

    @Test
    void givenCompanyThenComputeOwnerEarnings() {
        final var params = DomainObjectMother.params();
        final var company = DomainObjectMother.company();

        final var result = sut.eval(params, company);

        assertThat(result).isEqualTo(45109000000d);
    }
}

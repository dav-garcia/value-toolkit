package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CapmCostOfEquityIndicatorTest {

    private final CapmCostOfEquityIndicator sut = CapmCostOfEquityIndicator.INSTANCE;

    @Test
    void givenCompanyThenComputeCostOfEquity() {
        final var params = DomainObjectMother.params();
        final var company = DomainObjectMother.company();

        final var result = sut.eval(params, company);

        assertThat(result).isEqualTo(5.5995297);
    }
}
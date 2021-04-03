package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CostOfDebtIndicatorTest {

    private final CostOfDebtIndicator sut = CostOfDebtIndicator.INSTANCE;

    @Test
    void givenCompanyThenComputeCostOfDebt() {
        final var params = DomainObjectMother.params();
        final var company = DomainObjectMother.company();

        final var result = sut.eval(params, company);

        assertThat(result).isEqualTo(4.091461777756724);
    }
}

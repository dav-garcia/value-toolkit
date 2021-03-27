package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CostOfDebtIndicatorTest {

    private final CostOfDebtIndicator sut = CostOfDebtIndicator.INSTANCE;

    @Test
    void givenBusinessThenComputeCostOfDebt() {
        final var params = DomainObjectMother.params();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(params, business);

        assertThat(result).isEqualTo(4.091461777756724);
    }
}

package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CapmCostOfEquityIndicatorTest {

    private final CapmCostOfEquityIndicator sut = CapmCostOfEquityIndicator.INSTANCE;

    @Test
    void givenBusinessThenComputeCostOfEquity() {
        final var economy = DomainObjectMother.economy();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(economy, business);

        assertThat(result).isEqualTo(5.5995297);
    }
}

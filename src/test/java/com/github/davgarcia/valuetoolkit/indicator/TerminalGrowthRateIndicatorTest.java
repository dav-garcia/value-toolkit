package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TerminalGrowthRateIndicatorTest {

    private final TerminalGrowthRateIndicator sut = TerminalGrowthRateIndicator.INSTANCE;

    @Test
    void givenBusinessThenComputeTerminalGrowthRate() {
        final var economy = DomainObjectMother.economy();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(economy, business);

        assertThat(result).isEqualTo(3.0515044833544196);
    }
}

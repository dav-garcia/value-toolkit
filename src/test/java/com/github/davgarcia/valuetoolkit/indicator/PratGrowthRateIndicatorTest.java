package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PratGrowthRateIndicatorTest {

    private final PratGrowthRateIndicator sut = PratGrowthRateIndicator.INSTANCE;

    @Test
    void givenBusinessThenComputeGrowthRate() {
        final var economy = DomainObjectMother.economy();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(economy, business);

        assertThat(result).isEqualTo(24.634839058696242);
    }
}

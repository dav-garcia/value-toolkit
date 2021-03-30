package com.github.davgarcia.valuetoolkit.indicator.business;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PratGrowthRateIndicatorTest {

    private final PratGrowthRateIndicator sut = PratGrowthRateIndicator.INSTANCE;

    @Test
    void givenBusinessThenComputeGrowthRate() {
        final var params = DomainObjectMother.params();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(params, business);

        assertThat(result).isEqualTo(16.773416340697782);
    }
}

package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PratGrowthRateIndicatorTest {

    private final PratGrowthRateIndicator sut = PratGrowthRateIndicator.INSTANCE;

    @Test
    void givenCompanyThenComputeGrowthRate() {
        final var params = DomainObjectMother.params();
        final var company = DomainObjectMother.company();

        final var result = sut.eval(params, company);

        assertThat(result).isEqualTo(16.773416340697782);
    }
}

package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoceIndicatorTest {

    private final RoceIndicator sut = RoceIndicator.INSTANCE;

    @Test
    void givenPeriodThenComputeRoce() {
        final var period = DomainObjectMother.business().getLatestPeriod();

        final var result = sut.eval(period);

        assertThat(result).isEqualTo(23.108632713394265);
    }
}

package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NopatIndicatorTest {

    private final NopatIndicator sut = NopatIndicator.INSTANCE;

    @Test
    void givenPeriodThenComputeNopat() {
        final var period = DomainObjectMother.company().getLatestPeriod();

        final var result = sut.eval(period);

        assertThat(result).isEqualTo(44216710894.48676);
    }
}

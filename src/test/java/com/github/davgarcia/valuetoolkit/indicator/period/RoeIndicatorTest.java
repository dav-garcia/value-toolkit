package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoeIndicatorTest {

    private final RoeIndicator sut = RoeIndicator.INSTANCE;

    @Test
    void givenPeriodThenComputeRoe() {
        final var period = DomainObjectMother.company().getLatestPeriod();

        final var result = sut.eval(period);

        assertThat(result).isEqualTo(37.4298417635921);
    }
}

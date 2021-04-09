package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OwnerEarningsIndicatorTest {

    private final OwnerEarningsIndicator sut = OwnerEarningsIndicator.INSTANCE;

    @Test
    void givenPeriodThenComputeNopat() {
        final var period = DomainObjectMother.business().getLatestYear();

        final var result = sut.eval(period);

        assertThat(result).isEqualTo(45109000000d);
    }
}

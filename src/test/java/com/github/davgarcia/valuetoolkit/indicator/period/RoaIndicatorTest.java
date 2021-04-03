package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoaIndicatorTest {

    private final RoaIndicator sut = RoaIndicator.INSTANCE;

    @Test
    void givenPeriodThenComputeRoa() {
        final var period = DomainObjectMother.company().getLatestPeriod();

        final var result = sut.eval(period);

        assertThat(result).isEqualTo(14.696111326835065);
    }
}

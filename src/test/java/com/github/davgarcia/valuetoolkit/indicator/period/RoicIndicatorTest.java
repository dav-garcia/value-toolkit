package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoicIndicatorTest {

    private final RoicIndicator sut = RoicIndicator.INSTANCE;

    @Test
    void givenPeriodThenComputeRoic() {
        final var period = DomainObjectMother.business().getLatestYear();

        final var result = sut.eval(period);

        assertThat(result).isEqualTo(20.52533870000546);
    }
}

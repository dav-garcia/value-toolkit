package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CurrentRatioIndicatorTest {

    private final CurrentRatioIndicator sut = CurrentRatioIndicator.INSTANCE;

    @Test
    void givenPeriodThenComputeCurrentRatio() {
        final var period = DomainObjectMother.business().getLatestYear();

        final var result = sut.eval(period);

        assertThat(result).isEqualTo(2.5157654542940118);
    }
}

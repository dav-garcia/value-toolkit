package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QuickRatioIndicatorTest {

    private final QuickRatioIndicator sut = QuickRatioIndicator.INSTANCE;

    @Test
    void givenPeriodThenComputeQuickRatio() {
        final var period = DomainObjectMother.company().getLatestPeriod();

        final var result = sut.eval(period);

        assertThat(result).isEqualTo(2.3307702945650672);
    }
}

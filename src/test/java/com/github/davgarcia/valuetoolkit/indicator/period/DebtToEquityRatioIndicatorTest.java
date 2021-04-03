package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DebtToEquityRatioIndicatorTest {

    private final DebtToEquityRatioIndicator sut = DebtToEquityRatioIndicator.INSTANCE;

    @Test
    void givenPeriodThenComputeDebtToEquityRatio() {
        final var period = DomainObjectMother.company().getLatestPeriod();

        final var result = sut.eval(period);

        assertThat(result).isEqualTo(1.5469214903976196);
    }
}

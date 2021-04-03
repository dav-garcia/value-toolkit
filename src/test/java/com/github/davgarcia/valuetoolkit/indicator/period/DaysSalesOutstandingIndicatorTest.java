package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DaysSalesOutstandingIndicatorTest {

    private final DaysSalesOutstandingIndicator sut = DaysSalesOutstandingIndicator.INSTANCE;

    @Test
    void givenPeriodThenComputeDaysSalesOutstanding() {
        final var period = DomainObjectMother.company().getLatestPeriod();

        final var result = sut.eval(period);

        assertThat(result).isEqualTo(81.69782889906654);
    }
}

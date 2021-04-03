package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.IncomeStatement;
import com.github.davgarcia.valuetoolkit.Period;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractAverageIndicatorTest {

    private static class TestAverageIndicator extends AbstractAverageIndicator {

        protected TestAverageIndicator(final int maxPeriods) {
            super(maxPeriods);
        }

        @Override
        protected double eval(final ValueToolkitConfigProperties params, final Company company, final Period period) {
            return period.getIncomeStatement().getRevenue();
        }
    }

    private static final Company FULL_COMPANY = Company.builder()
            .yearPeriods(List.of(
                    Period.builder()
                            .incomeStatement(IncomeStatement.builder().revenue(1d).build())
                            .build(),
                    Period.builder()
                            .incomeStatement(IncomeStatement.builder().revenue(10d).build())
                            .build(),
                    Period.builder()
                            .incomeStatement(IncomeStatement.builder().revenue(100d).build())
                            .build()))
            .build();
    private static final Company EMPTY_COMPANY = Company.builder()
            .yearPeriods(Collections.emptyList())
            .build();

    @Test
    void given3PeriodsWhen0MaxThenReturn0() {
        final var sut = new TestAverageIndicator(0);

        final var result = sut.eval(null, FULL_COMPANY);

        assertThat(result).isEqualTo(0d);
    }

    @Test
    void given3PeriodsWhen1MaxThenReturnIt() {
        final var sut = new TestAverageIndicator(1);

        final var result = sut.eval(null, FULL_COMPANY);

        assertThat(result).isEqualTo(1d);
    }

    @Test
    void given3PeriodsWhen2MaxThenReturnAverage() {
        final var sut = new TestAverageIndicator(2);

        final var result = sut.eval(null, FULL_COMPANY);

        assertThat(result).isEqualTo((1d + 10d) / 2d);
    }

    @Test
    void givenNoPeriodsThenReturn0() {
        final var sut = new TestAverageIndicator(2);

        final var result = sut.eval(null, EMPTY_COMPANY);

        assertThat(result).isEqualTo(0d);
    }
}

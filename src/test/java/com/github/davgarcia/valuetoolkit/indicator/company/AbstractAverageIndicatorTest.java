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

        protected TestAverageIndicator(final int maxPeriods, final Period.Status withStatus) {
            super(maxPeriods, withStatus);
        }

        @Override
        protected double eval(final ValueToolkitConfigProperties params, final Company company, final Period period) {
            return period.getIncomeStatement().getRevenue();
        }
    }

    private static final Company FULL_COMPANY = Company.builder()
            .periods(List.of(
                    Period.builder()
                            .status(Period.Status.COMPUTE)
                            .incomeStatement(IncomeStatement.builder().revenue(1d).build())
                            .build(),
                    Period.builder()
                            .status(Period.Status.ACTUAL)
                            .incomeStatement(IncomeStatement.builder().revenue(10d).build())
                            .build(),
                    Period.builder()
                            .status(Period.Status.ESTIMATE)
                            .incomeStatement(IncomeStatement.builder().revenue(100d).build())
                            .build(),
                    Period.builder()
                            .status(Period.Status.ACTUAL)
                            .incomeStatement(IncomeStatement.builder().revenue(1000d).build())
                            .build(),
                    Period.builder()
                            .status(Period.Status.ACTUAL)
                            .incomeStatement(IncomeStatement.builder().revenue(10000d).build())
                            .build()))
            .build();
    private static final Company EMPTY_COMPANY = Company.builder()
            .periods(Collections.emptyList())
            .build();

    @Test
    void given5PeriodsWhen0MaxThenReturn0() {
        final var sut = new TestAverageIndicator(0, null);

        final var result = sut.eval(null, FULL_COMPANY);

        assertThat(result).isEqualTo(0d);
    }

    @Test
    void given3ActualperiodsWhen1MaxActualThenReturnIt() {
        final var sut = new TestAverageIndicator(1, Period.Status.ACTUAL);

        final var result = sut.eval(null, FULL_COMPANY);

        assertThat(result).isEqualTo(10d);
    }

    @Test
    void given5PeriodsWhen2MaxThenReturnAverage() {
        final var sut = new TestAverageIndicator(2, null);

        final var result = sut.eval(null, FULL_COMPANY);

        assertThat(result).isEqualTo((1d + 10d) / 2d);
    }

    @Test
    void given3ActualPeriodsWhenMax4ActualThenReturnAverage() {
        final var sut = new TestAverageIndicator(4, Period.Status.ACTUAL);

        final var result = sut.eval(null, FULL_COMPANY);

        assertThat(result).isEqualTo((10d + 1000d + 10000d) / 3d);
    }

    @Test
    void givenNoPeriodsThenReturn0() {
        final var sut = new TestAverageIndicator(2, null);

        final var result = sut.eval(null, EMPTY_COMPANY);

        assertThat(result).isEqualTo(0d);
    }
}

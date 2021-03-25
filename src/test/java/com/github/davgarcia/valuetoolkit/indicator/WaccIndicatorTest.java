package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WaccIndicatorTest {

    private final WaccIndicator sut = WaccIndicator.INSTANCE;

    @Test
    void givenBusinessThenComputeWacc() {
        final var economy = DomainObjectMother.economy();
        final var business = DomainObjectMother.business();

        final var result = sut.eval(economy, business);

        assertThat(result).isEqualTo(5.524148588038859);
    }
}

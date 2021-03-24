package com.github.davgarcia.valuetoolkit.enrichers;

import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import com.github.davgarcia.valuetoolkit.domain.ComputedData;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CostOfDebtEnricherTest {

    private final CostOfDebtEnricher sut = new CostOfDebtEnricher();

    @Test
    void givenBusinessThenComputeCostOfDebt() {
        final var business = DomainObjectMother.business();
        final var builder = ComputedData.builder();

        sut.enrich(business, builder);
        final var result = builder.build();

        assertThat(result.getCostOfDebt()).isEqualTo(0.04091461777756723);
    }
}

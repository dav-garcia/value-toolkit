package com.github.davgarcia.valuetoolkit.enrichers;

import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import com.github.davgarcia.valuetoolkit.domain.ComputedData;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CapmCostOfEquityEnricherTest {

    private final CapmCostOfEquityEnricher sut = new CapmCostOfEquityEnricher(DomainObjectMother.configProperties());

    @Test
    void givenBusinessThenComputeCostOfEquity() {
        final var business = DomainObjectMother.business();
        final var builder = ComputedData.builder();

        sut.enrich(business, builder);
        final var result = builder.build();

        assertThat(result.getCostOfEquity()).isEqualTo(5.5995297);
    }
}

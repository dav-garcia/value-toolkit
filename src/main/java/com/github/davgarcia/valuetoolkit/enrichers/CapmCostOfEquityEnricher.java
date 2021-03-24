package com.github.davgarcia.valuetoolkit.enrichers;

import com.github.davgarcia.valuetoolkit.BusinessDataEnricher;
import com.github.davgarcia.valuetoolkit.config.EconomyConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.ComputedData;
import org.springframework.stereotype.Component;

@Component
public class CapmCostOfEquityEnricher implements BusinessDataEnricher {

    private final EconomyConfigProperties economy;

    public CapmCostOfEquityEnricher(final EconomyConfigProperties economy) {
        this.economy = economy;
    }

    @Override
    public void enrich(final Business business, final ComputedData.ComputedDataBuilder builder) {
        final var currency = business.getProfile().getCurrency();
        final var riskFreeRate = economy.getRiskFreeRate(currency);
        final var beta = business.getProfile().getBeta();
        final var erp = economy.getErp();

        builder.costOfEquity(riskFreeRate + beta * erp);
    }
}

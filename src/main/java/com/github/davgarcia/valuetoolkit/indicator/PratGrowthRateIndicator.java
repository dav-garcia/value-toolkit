package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.config.EconomyConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.Period;

public class PratGrowthRateIndicator extends AbstractAverageIndicator {

    public static final PratGrowthRateIndicator INSTANCE = new PratGrowthRateIndicator();

    private PratGrowthRateIndicator() {
        super(4, Period.Status.ACTUAL);
    }

    @Override
    public double eval(final EconomyConfigProperties economy, final Business business, final Period period) {
        final var netIncome = period.getIncomeStatement().getNetIncome();
        final var revenue = period.getIncomeStatement().getRevenue();
        final var dividendsPaid = period.getCashFlowStatement().getDividendsPaid();
        final var totalAssets = period.getBalanceSheet().getTotalAssets();
        final var totalStockholdersEquity = period.getBalanceSheet().getTotalStockholdersEquity();

        final var profitMargin = netIncome / revenue;
        final var retentionRate = (netIncome + dividendsPaid) / netIncome * 100d;
        final var assetTurnover = revenue / totalAssets;
        final var financialLeverage = totalAssets / totalStockholdersEquity;

        return profitMargin * retentionRate * assetTurnover * financialLeverage;
    }
}

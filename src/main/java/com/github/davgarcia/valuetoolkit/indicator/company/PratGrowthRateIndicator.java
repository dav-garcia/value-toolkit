package com.github.davgarcia.valuetoolkit.indicator.company;

import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.Period;

public class PratGrowthRateIndicator extends AbstractAverageIndicator {

    public static final PratGrowthRateIndicator INSTANCE = new PratGrowthRateIndicator();

    private PratGrowthRateIndicator() {
        super(4);
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Company company, final Period period) {
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

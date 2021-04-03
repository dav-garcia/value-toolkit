package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.indicator.company.CapmCostOfEquityIndicator;
import com.github.davgarcia.valuetoolkit.indicator.company.CostOfDebtIndicator;
import com.github.davgarcia.valuetoolkit.indicator.company.ProfitMarginIndicator;
import com.github.davgarcia.valuetoolkit.indicator.company.OwnerEarningsIndicator;
import com.github.davgarcia.valuetoolkit.indicator.company.PegrTrailingIndicator;
import com.github.davgarcia.valuetoolkit.indicator.company.PerTrailingIndicator;
import com.github.davgarcia.valuetoolkit.indicator.company.PratGrowthRateIndicator;
import com.github.davgarcia.valuetoolkit.indicator.company.PriceToBookValueIndicator;
import com.github.davgarcia.valuetoolkit.indicator.company.PriceToFcfIndicator;
import com.github.davgarcia.valuetoolkit.indicator.company.PriceToSalesIndicator;
import com.github.davgarcia.valuetoolkit.indicator.company.TaxRateIndicator;
import com.github.davgarcia.valuetoolkit.indicator.company.TerminalGrowthRateIndicator;
import com.github.davgarcia.valuetoolkit.indicator.company.WaccIndicator;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple inference engine that computes company indicators on demand.
 * <br>
 * Beware it's not thread safe!
 */
public class CompanyIndicators {

    @Data
    private static class Evaluation {

        double value;
        boolean evaluating;

        public Evaluation() {
            value = 0d;
            evaluating = true;
        }
    }

    private final ValueToolkitConfigProperties params;
    private final Company company;
    private final Map<CompanyIndicator, Evaluation> indicators;

    public CompanyIndicators(final ValueToolkitConfigProperties params, final Company company) {
        this.params = params;
        this.company = company;
        indicators = new HashMap<>();
    }

    public double getCapmCostOfEquity() {
        return eval(CapmCostOfEquityIndicator.INSTANCE);
    }

    public double getCostOfDebt() {
        return eval(CostOfDebtIndicator.INSTANCE);
    }

    public double getPratGrowthRate() {
        return eval(PratGrowthRateIndicator.INSTANCE);
    }

    public double getTaxRate() {
        return eval(TaxRateIndicator.INSTANCE);
    }

    public double getTerminalGrowthRate() {
        return eval(TerminalGrowthRateIndicator.INSTANCE);
    }

    public double getWacc() {
        return eval(WaccIndicator.INSTANCE);
    }

    public double getOwnerEarnings() {
        return eval(OwnerEarningsIndicator.INSTANCE);
    }

    public double getProfitMargin() {
        return eval(ProfitMarginIndicator.INSTANCE);
    }

    public double getPerTrailing() {
        return eval(PerTrailingIndicator.INSTANCE);
    }

    public double getPegrTrailing() {
        return eval(PegrTrailingIndicator.INSTANCE);
    }

    public double getPriceToSales() {
        return eval(PriceToSalesIndicator.INSTANCE);
    }

    public double getPriceToBookValue() {
        return eval(PriceToBookValueIndicator.INSTANCE);
    }

    public double getPriceToFcf() {
        return eval(PriceToFcfIndicator.INSTANCE);
    }

    private double eval(final CompanyIndicator indicator) {
        var evaluation = indicators.get(indicator);
        if (evaluation == null) {
            evaluation = new Evaluation();
            indicators.put(indicator, evaluation);

            evaluation.setValue(indicator.eval(params, company));
            evaluation.setEvaluating(false);
        } else if (evaluation.isEvaluating()) {
            throw new RuntimeException("Detected an infinite loop in: " + indicator.getClass().getSimpleName());
        }
        return evaluation.getValue();
    }
}

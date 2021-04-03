package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.indicator.business.CapmCostOfEquityIndicator;
import com.github.davgarcia.valuetoolkit.indicator.business.CostOfDebtIndicator;
import com.github.davgarcia.valuetoolkit.indicator.business.ProfitMarginIndicator;
import com.github.davgarcia.valuetoolkit.indicator.business.OwnerEarningsIndicator;
import com.github.davgarcia.valuetoolkit.indicator.business.PegrTrailingIndicator;
import com.github.davgarcia.valuetoolkit.indicator.business.PerTrailingIndicator;
import com.github.davgarcia.valuetoolkit.indicator.business.PratGrowthRateIndicator;
import com.github.davgarcia.valuetoolkit.indicator.business.PriceToBookValueIndicator;
import com.github.davgarcia.valuetoolkit.indicator.business.PriceToFcfIndicator;
import com.github.davgarcia.valuetoolkit.indicator.business.PriceToSalesIndicator;
import com.github.davgarcia.valuetoolkit.indicator.business.TaxRateIndicator;
import com.github.davgarcia.valuetoolkit.indicator.business.TerminalGrowthRateIndicator;
import com.github.davgarcia.valuetoolkit.indicator.business.WaccIndicator;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple inference engine that computes business indicators on demand.
 * <br>
 * Beware it's not thread safe!
 */
public class BusinessIndicators {

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
    private final Business business;
    private final Map<BusinessIndicator, Evaluation> indicators;

    public BusinessIndicators(final ValueToolkitConfigProperties params, final Business business) {
        this.params = params;
        this.business = business;
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

    private double eval(final BusinessIndicator indicator) {
        var evaluation = indicators.get(indicator);
        if (evaluation == null) {
            evaluation = new Evaluation();
            indicators.put(indicator, evaluation);

            evaluation.setValue(indicator.eval(params, business));
            evaluation.setEvaluating(false);
        } else if (evaluation.isEvaluating()) {
            throw new RuntimeException("Detected an infinite loop in: " + indicator.getClass().getSimpleName());
        }
        return evaluation.getValue();
    }
}

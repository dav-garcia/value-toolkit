package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.indicator.period.CurrentRatioIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.DaysSalesOutstandingIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.DebtToEquityRatioIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.NopatIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.OwnerEarningsIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.QuickRatioIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.RoaIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.RoceIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.RoeIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.RoicIndicator;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiscalPeriodIndicators {

    @Data
    private static class Evaluation {

        double value;
        boolean evaluating;

        public Evaluation() {
            value = 0d;
            evaluating = true;
        }
    }

    private final FiscalPeriod period;
    private final List<FiscalPeriod> periods;
    private final Map<FiscalPeriodIndicator, Evaluation> indicators;

    public FiscalPeriodIndicators(final FiscalPeriod period) {
        this.period = period;
        periods = null;
        indicators = new HashMap<>();
    }

    public FiscalPeriodIndicators(final List<FiscalPeriod> periods) {
        period = null;
        this.periods = periods;
        indicators = new HashMap<>();
    }

    public double getCurrentRatio() {
        return eval(CurrentRatioIndicator.INSTANCE);
    }

    public double getQuickRatio() {
        return eval(QuickRatioIndicator.INSTANCE);
    }

    public double getDaysSalesOutstanding() {
        return eval(DaysSalesOutstandingIndicator.INSTANCE);
    }

    public double getDebtToEquityRatio() {
        return eval(DebtToEquityRatioIndicator.INSTANCE);
    }

    public double getNopat() {
        return eval(NopatIndicator.INSTANCE);
    }

    public double getOwnerEarnings() {
        return eval(OwnerEarningsIndicator.INSTANCE);
    }

    public double getRoa() {
        return eval(RoaIndicator.INSTANCE);
    }

    public double getRoe() {
        return eval(RoeIndicator.INSTANCE);
    }

    public double getRoce() {
        return eval(RoceIndicator.INSTANCE);
    }

    public double getRoic() {
        return eval(RoicIndicator.INSTANCE);
    }

    private double eval(final FiscalPeriodIndicator indicator) {
        var evaluation = indicators.get(indicator);
        if (evaluation == null) {
            evaluation = new Evaluation();
            indicators.put(indicator, evaluation);

            evaluation.setValue(period == null ? average(indicator) : indicator.eval(period));
            evaluation.setEvaluating(false);
        } else if (evaluation.isEvaluating()) {
            throw new RuntimeException("Detected an infinite loop in: " + indicator.getClass().getSimpleName());
        }
        return evaluation.getValue();
    }

    private double average(final FiscalPeriodIndicator indicator) {
        return periods.stream()
                .map(FiscalPeriod::getIndicators)
                .mapToDouble(i -> i.eval(indicator))
                .average().orElse(0d);
    }
}

package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.indicator.period.CurrentRatioIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.DaysSalesOutstandingIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.DebtToEquityRatioIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.NopatIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.QuickRatioIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.RoaIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.RoceIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.RoeIndicator;
import com.github.davgarcia.valuetoolkit.indicator.period.RoicIndicator;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PeriodIndicators {

    @Data
    private static class Evaluation {

        double value;
        boolean evaluating;

        public Evaluation() {
            value = 0d;
            evaluating = true;
        }
    }

    private final Period period;
    private final List<Period> periods;
    private final Map<PeriodIndicator, Evaluation> indicators;

    public PeriodIndicators(final Period period) {
        this.period = period;
        periods = null;
        indicators = new HashMap<>();
    }

    public PeriodIndicators(final List<Period> periods) {
        period = null;
        this.periods = periods.stream()
                .filter(p -> p.getStatus() == Period.Status.ACTUAL)
                .collect(Collectors.toList());
        indicators = new HashMap<>();
    }

    public double currentRatio() {
        return eval(CurrentRatioIndicator.INSTANCE);
    }

    public double quickRatio() {
        return eval(QuickRatioIndicator.INSTANCE);
    }

    public double daysSalesOutstanding() {
        return eval(DaysSalesOutstandingIndicator.INSTANCE);
    }

    public double debtToEquityRatio() {
        return eval(DebtToEquityRatioIndicator.INSTANCE);
    }

    public double nopat() {
        return eval(NopatIndicator.INSTANCE);
    }

    public double roa() {
        return eval(RoaIndicator.INSTANCE);
    }

    public double roe() {
        return eval(RoeIndicator.INSTANCE);
    }

    public double roce() {
        return eval(RoceIndicator.INSTANCE);
    }

    public double roic() {
        return eval(RoicIndicator.INSTANCE);
    }

    private double eval(final PeriodIndicator indicator) {
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

    private double average(final PeriodIndicator indicator) {
        return periods.stream()
                .map(Period::getIndicators)
                .mapToDouble(i -> i.eval(indicator))
                .average().orElse(0d);
    }
}

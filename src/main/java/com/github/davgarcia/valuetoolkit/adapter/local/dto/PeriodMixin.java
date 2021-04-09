package com.github.davgarcia.valuetoolkit.adapter.local.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.davgarcia.valuetoolkit.FiscalPeriodIndicators;

public interface PeriodMixin {

    @JsonIgnore
    FiscalPeriodIndicators getIndicators();
}

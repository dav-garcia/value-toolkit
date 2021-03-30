package com.github.davgarcia.valuetoolkit.adapter.local.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.davgarcia.valuetoolkit.PeriodIndicators;

public interface PeriodMixin {

    @JsonIgnore
    PeriodIndicators getIndicators();
}

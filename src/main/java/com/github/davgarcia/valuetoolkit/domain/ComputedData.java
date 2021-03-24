package com.github.davgarcia.valuetoolkit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class ComputedData {

    double costOfEquity;
    double costOfDebt;
    double wacc;
    double corporateTaxRate;
    double pratGrowthRate;
    double terminalGrowthRate;
}

package com.github.davgarcia.valuetoolkit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@AllArgsConstructor
@Builder
public class Period {

    public enum Type {
        QUARTER,
        YEAR
    }

    public enum Status {
        ACTUAL,
        ESTIMATE,
        COMPUTE
    }

    Type type;
    Status status;
    String name;
    LocalDate date;
    IncomeStatement incomeStatement;
    BalanceSheet balanceSheet;
    CashFlowStatement cashFlowStatement;
}

package com.github.davgarcia.valuetoolkit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

/**
 * Gathers financial data either a fiscal year or a quarter.
 */
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

    /**
     * Fiscal year or quarter.
     */
    Type type;

    /**
     * Whether it's actual (real), analyst estimates or computed data.
     */
    Status status;

    /**
     * Period name, like "FY-2020" or "Q1-2020".
     */
    String name;

    /**
     * The first day of the period.
     */
    LocalDate date;

    /**
     * Income statement for the period.
     */
    IncomeStatement incomeStatement;

    /**
     * Balance sheet for the period.
     */
    BalanceSheet balanceSheet;

    /**
     * Cash flow statement for the period.
     */
    CashFlowStatement cashFlowStatement;
}

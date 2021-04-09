package com.github.davgarcia.valuetoolkit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.time.LocalDate;

/**
 * Gathers financial data either a fiscal year or a quarter.
 */
@Value
@AllArgsConstructor
@Builder
public class FiscalPeriod {

    public enum Type {
        QUARTER,
        YEAR
    }

    /**
     * Fiscal year or quarter.
     */
    Type type;

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

    /**
     * Business ratios for the period.
     */
    @NonFinal
    @Setter
    @EqualsAndHashCode.Exclude
    FiscalPeriodIndicators indicators;
}

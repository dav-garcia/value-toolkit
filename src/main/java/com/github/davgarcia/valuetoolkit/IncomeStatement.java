package com.github.davgarcia.valuetoolkit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Financial data for a period: income statement.
 */
@Value
@AllArgsConstructor
@Builder
public class IncomeStatement {

    double revenue;
    double costOfRevenue;
    double grossProfit;

    double researchAndDevelopmentExpenses;
    double generalAndAdministrativeExpenses;
    double sellingAndMarketingExpenses;
    double otherExpenses;
    double operatingExpenses;
    double interestExpense;
    double depreciationAndAmortization;
    double ebitda;

    double operatingIncome;

    double totalOtherIncomeExpensesNet;
    double incomeBeforeTax;

    double incomeTaxExpense;
    double netIncome;

    double eps;
    double epsDiluted;

    double weightedAverageSharesOutstanding;
    double weightedAverageSharesOutstandingDiluted;
}

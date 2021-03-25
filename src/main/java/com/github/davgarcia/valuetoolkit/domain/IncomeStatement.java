package com.github.davgarcia.valuetoolkit.domain;

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
    double grossProfitRatio;

    double researchAndDevelopmentExpenses;
    double generalAndAdministrativeExpenses;
    double sellingAndMarketingExpenses;
    double otherExpenses;
    double operatingExpenses;
    double costAndExpenses;
    double interestExpense;
    double depreciationAndAmortization;
    double ebitda;
    double ebitdaRatio;

    double operatingIncome;
    double operatingIncomeRatio;

    double totalOtherIncomeExpensesNet;
    double incomeBeforeTax;
    double incomeBeforeTaxRatio;

    double incomeTaxExpense;
    double netIncome;
    double netIncomeRatio;

    double eps;
    double epsDiluted;

    double weightedAverageSharesOutstanding;
    double weightedAverageSharesOutstandingDiluted;
}

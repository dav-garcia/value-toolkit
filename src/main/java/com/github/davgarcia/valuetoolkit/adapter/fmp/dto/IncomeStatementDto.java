package com.github.davgarcia.valuetoolkit.adapter.fmp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDate;

@Value
public class IncomeStatementDto {

    LocalDate date;
    String period;

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
    @JsonProperty("epsdiluted")
    double epsDiluted;

    @JsonProperty("weightedAverageShsOut")
    double weightedAverageSharesOutstanding;
    @JsonProperty("weightedAverageShsOutDil")
    double weightedAverageSharesOutstandingDiluted;
}

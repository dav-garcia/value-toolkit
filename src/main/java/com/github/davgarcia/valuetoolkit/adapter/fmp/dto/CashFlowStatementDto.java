package com.github.davgarcia.valuetoolkit.adapter.fmp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDate;

@Value
public class CashFlowStatementDto {

    LocalDate date;
    String period;

    double netIncome;
    double depreciationAndAmortization;
    double deferredIncomeTax;
    double stockBasedCompensation;
    double changeInWorkingCapital;
    double accountsReceivables;
    double inventory;
    double accountsPayables;
    double otherWorkingCapital;
    double otherNonCashItems;
    @JsonProperty("netCashProvidedByOperatingActivities")
    double netCashFromOperatingActivities;

    double investmentsInPropertyPlantAndEquipment;
    @JsonProperty("acquisitionsNet")
    double netAcquisitions;
    double purchasesOfInvestments;
    double salesMaturitiesOfInvestments;
    @JsonProperty("otherInvestingActivites")
    double otherInvestingActivities;
    @JsonProperty("netCashUsedForInvestingActivites")
    double netCashFromInvestingActivities;

    double debtRepayment;
    double commonStockIssued;
    double commonStockRepurchased;
    double dividendsPaid;
    @JsonProperty("otherFinancingActivites")
    double otherFinancingActivities;
    @JsonProperty("netCashUsedProvidedByFinancingActivities")
    double netCashFromFinancingActivities;

    double effectOfForexChangesOnCash;
    double netChangeInCash;

    double cashAtEndOfPeriod;
    double cashAtBeginningOfPeriod;
    double operatingCashFlow;
    double capitalExpenditure;
    double freeCashFlow;
}

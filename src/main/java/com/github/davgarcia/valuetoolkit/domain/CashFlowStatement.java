package com.github.davgarcia.valuetoolkit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Financial data for a period: cash flow statement.
 */
@Value
@AllArgsConstructor
@Builder
public class CashFlowStatement {

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
    double netCashFromOperatingActivities;

    double investmentsInPropertyPlantAndEquipment;
    double netAcquisitions;
    double purchasesOfInvestments;
    double salesMaturitiesOfInvestments;
    double otherInvestingActivities;
    double netCashFromInvestingActivities;

    double debtRepayment;
    double commonStockIssued;
    double commonStockRepurchased;
    double dividendsPaid;
    double otherFinancingActivities;
    double netCashFromFinancingActivities;

    double effectOfForexChangesOnCash;
    double netChangeInCash;

    double cashAtEndOfPeriod;
    double cashAtBeginningOfPeriod;
    double operatingCashFlow;
    double capitalExpenditure;
    double freeCashFlow;
}

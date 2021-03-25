package com.github.davgarcia.valuetoolkit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Financial data for a period: balance sheet.
 */
@Value
@AllArgsConstructor
@Builder
public class BalanceSheet {

    double cashAndCashEquivalents;
    double shortTermInvestments;
    double netReceivables;
    double inventory;
    double otherCurrentAssets;
    double totalCurrentAssets;
    double netPropertyPlantEquipment;
    double goodwill;
    double intangibleAssets;
    double longTermInvestments;
    double taxAssets;
    double otherNonCurrentAssets;
    double totalNonCurrentAssets;
    double otherAssets;
    double totalAssets;

    double accountPayables;
    double shortTermDebt;
    double taxPayables;
    double deferredRevenue;
    double otherCurrentLiabilities;
    double totalCurrentLiabilities;
    double longTermDebt;
    double deferredRevenueNonCurrent;
    double deferredTaxLiabilitiesNonCurrent;
    double otherNonCurrentLiabilities;
    double totalNonCurrentLiabilities;
    double otherLiabilities;
    double totalLiabilities;

    double commonStock;
    double retainedEarnings;
    double accumulatedOtherComprehensiveIncomeLoss;
    double othertotalStockholdersEquity;
    double totalStockholdersEquity;
    double totalInvestments;
    double totalDebt;
    double netDebt;
}

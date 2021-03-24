package com.github.davgarcia.valuetoolkit.adapter.fmp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDate;

@Value
public class BalanceSheetDto {

    LocalDate date;
    String period;

    double cashAndCashEquivalents;
    double shortTermInvestments;
    double netReceivables;
    double inventory;
    double otherCurrentAssets;
    double totalCurrentAssets;
    @JsonProperty("propertyPlantEquipmentNet")
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

package com.github.davgarcia.valuetoolkit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

/**
 * Root entity aggregating all business data.
 */
@Value
@AllArgsConstructor
@Builder
public class Business {

    /**
     * Company identifier.
     */
    BusinessLocator locator;

    /**
     * Business profile.
     */
    BusinessProfile profile;

    /**
     * Quarter periods inversely ordered by date (newest first, oldest last).
     * <br>
     * Quarters are aggregated by years and are therefore hidden from the API.
     */
    @Getter(AccessLevel.NONE)
    List<FiscalPeriod> quarters;

    /**
     * Fiscal year periods inversely ordered by date (newest first, oldest last).
     */
    @NonFinal
    List<FiscalPeriod> years;

    /**
     * Business estimates and other parameters used during valuation.
     */
    BusinessEstimates estimates;

    /**
     * Financial indicators, manually set afterwards.
     */
    @NonFinal
    @Setter
    @EqualsAndHashCode.Exclude
    BusinessIndicators indicators;

    public List<FiscalPeriod> getYears() {
        aggregateQuarters();
        return years;
    }

    public FiscalPeriod getLatestYear() {
        aggregateQuarters();
        return years.get(0);
    }

    public FiscalPeriod getPreviousYear(final FiscalPeriod period) {
        aggregateQuarters();
        for (int i = 0; i < years.size() - 1; i++) { // Stop at 1 item before end of list.
            if (years.get(i) == period) {
                return years.get(i + 1);
            }
        }
        return null;
    }

    private void aggregateQuarters() {
        if (years == null) {
            final var numYears = quarters.size() / 4; // Lower bound; discard oldest year if incomplete.

            this.years = new ArrayList<>(numYears);
            for (int i = 0; i < numYears; i++) {
                this.years.add(buildYearPeriod(quarters.subList(i * 4, (i + 1) * 4)));
            }
        }
    }

    private FiscalPeriod buildYearPeriod(final List<FiscalPeriod> quarters) {
        final var latest = quarters.get(0);
        final var earliest = quarters.get(3);

        return FiscalPeriod.builder()
                .type(FiscalPeriod.Type.YEAR)
                .name("AY-" + earliest.getDate().getYear())
                .date(earliest.getDate())
                .incomeStatement(IncomeStatement.builder()
                        .revenue(sumIncomeStatement(quarters, IncomeStatement::getRevenue))
                        .costOfRevenue(sumIncomeStatement(quarters, IncomeStatement::getCostOfRevenue))
                        .grossProfit(sumIncomeStatement(quarters, IncomeStatement::getGrossProfit))
                        .researchAndDevelopmentExpenses(sumIncomeStatement(quarters, IncomeStatement::getResearchAndDevelopmentExpenses))
                        .generalAndAdministrativeExpenses(sumIncomeStatement(quarters, IncomeStatement::getGeneralAndAdministrativeExpenses))
                        .sellingAndMarketingExpenses(sumIncomeStatement(quarters, IncomeStatement::getSellingAndMarketingExpenses))
                        .otherExpenses(sumIncomeStatement(quarters, IncomeStatement::getOtherExpenses))
                        .operatingExpenses(sumIncomeStatement(quarters, IncomeStatement::getOperatingExpenses))
                        .interestExpense(sumIncomeStatement(quarters, IncomeStatement::getInterestExpense))
                        .depreciationAndAmortization(sumIncomeStatement(quarters, IncomeStatement::getDepreciationAndAmortization))
                        .ebitda(sumIncomeStatement(quarters, IncomeStatement::getEbitda))
                        .operatingIncome(sumIncomeStatement(quarters, IncomeStatement::getOperatingIncome))
                        .totalOtherIncomeExpensesNet(sumIncomeStatement(quarters, IncomeStatement::getTotalOtherIncomeExpensesNet))
                        .incomeBeforeTax(sumIncomeStatement(quarters, IncomeStatement::getIncomeBeforeTax))
                        .incomeTaxExpense(sumIncomeStatement(quarters, IncomeStatement::getIncomeTaxExpense))
                        .netIncome(sumIncomeStatement(quarters, IncomeStatement::getNetIncome))
                        .eps(sumIncomeStatement(quarters, IncomeStatement::getEps))
                        .epsDiluted(sumIncomeStatement(quarters, IncomeStatement::getEpsDiluted))
                        .weightedAverageSharesOutstanding(latest.getIncomeStatement().getWeightedAverageSharesOutstanding())
                        .weightedAverageSharesOutstandingDiluted(latest.getIncomeStatement().getWeightedAverageSharesOutstandingDiluted())
                        .build())
                .balanceSheet(BalanceSheet.builder()
                        .cashAndCashEquivalents(sumBalanceSheet(quarters, BalanceSheet::getCashAndCashEquivalents))
                        .shortTermInvestments(sumBalanceSheet(quarters, BalanceSheet::getShortTermInvestments))
                        .netReceivables(sumBalanceSheet(quarters, BalanceSheet::getNetReceivables))
                        .inventory(sumBalanceSheet(quarters, BalanceSheet::getInventory))
                        .otherCurrentAssets(sumBalanceSheet(quarters, BalanceSheet::getOtherCurrentAssets))
                        .totalCurrentAssets(sumBalanceSheet(quarters, BalanceSheet::getTotalCurrentAssets))
                        .netPropertyPlantEquipment(sumBalanceSheet(quarters, BalanceSheet::getNetPropertyPlantEquipment))
                        .goodwill(sumBalanceSheet(quarters, BalanceSheet::getGoodwill))
                        .intangibleAssets(sumBalanceSheet(quarters, BalanceSheet::getIntangibleAssets))
                        .longTermInvestments(sumBalanceSheet(quarters, BalanceSheet::getLongTermInvestments))
                        .taxAssets(sumBalanceSheet(quarters, BalanceSheet::getTaxAssets))
                        .otherNonCurrentAssets(sumBalanceSheet(quarters, BalanceSheet::getOtherNonCurrentAssets))
                        .totalNonCurrentAssets(sumBalanceSheet(quarters, BalanceSheet::getTotalNonCurrentAssets))
                        .otherAssets(sumBalanceSheet(quarters, BalanceSheet::getOtherAssets))
                        .totalAssets(sumBalanceSheet(quarters, BalanceSheet::getTotalAssets))
                        .accountPayables(sumBalanceSheet(quarters, BalanceSheet::getAccountPayables))
                        .shortTermDebt(sumBalanceSheet(quarters, BalanceSheet::getShortTermDebt))
                        .taxPayables(sumBalanceSheet(quarters, BalanceSheet::getTaxPayables))
                        .deferredRevenue(sumBalanceSheet(quarters, BalanceSheet::getDeferredRevenue))
                        .otherCurrentLiabilities(sumBalanceSheet(quarters, BalanceSheet::getOtherCurrentLiabilities))
                        .totalCurrentLiabilities(sumBalanceSheet(quarters, BalanceSheet::getTotalCurrentLiabilities))
                        .longTermDebt(sumBalanceSheet(quarters, BalanceSheet::getLongTermDebt))
                        .deferredRevenueNonCurrent(sumBalanceSheet(quarters, BalanceSheet::getDeferredRevenueNonCurrent))
                        .deferredTaxLiabilitiesNonCurrent(sumBalanceSheet(quarters, BalanceSheet::getDeferredTaxLiabilitiesNonCurrent))
                        .otherNonCurrentLiabilities(sumBalanceSheet(quarters, BalanceSheet::getOtherNonCurrentLiabilities))
                        .totalNonCurrentLiabilities(sumBalanceSheet(quarters, BalanceSheet::getTotalNonCurrentLiabilities))
                        .otherLiabilities(sumBalanceSheet(quarters, BalanceSheet::getOtherLiabilities))
                        .totalLiabilities(sumBalanceSheet(quarters, BalanceSheet::getTotalLiabilities))
                        .commonStock(sumBalanceSheet(quarters, BalanceSheet::getCommonStock))
                        .retainedEarnings(sumBalanceSheet(quarters, BalanceSheet::getRetainedEarnings))
                        .accumulatedOtherComprehensiveIncomeLoss(sumBalanceSheet(quarters, BalanceSheet::getAccumulatedOtherComprehensiveIncomeLoss))
                        .otherTotalStockholdersEquity(sumBalanceSheet(quarters, BalanceSheet::getOtherTotalStockholdersEquity))
                        .totalStockholdersEquity(sumBalanceSheet(quarters, BalanceSheet::getTotalStockholdersEquity))
                        .totalInvestments(sumBalanceSheet(quarters, BalanceSheet::getTotalInvestments))
                        .totalDebt(sumBalanceSheet(quarters, BalanceSheet::getTotalDebt))
                        .netDebt(sumBalanceSheet(quarters, BalanceSheet::getNetDebt))
                        .build())
                .cashFlowStatement(CashFlowStatement.builder()
                        .netIncome(sumCashFlowStatement(quarters, CashFlowStatement::getNetIncome))
                        .depreciationAndAmortization(sumCashFlowStatement(quarters, CashFlowStatement::getDepreciationAndAmortization))
                        .deferredIncomeTax(sumCashFlowStatement(quarters, CashFlowStatement::getDeferredIncomeTax))
                        .stockBasedCompensation(sumCashFlowStatement(quarters, CashFlowStatement::getStockBasedCompensation))
                        .changeInWorkingCapital(sumCashFlowStatement(quarters, CashFlowStatement::getChangeInWorkingCapital))
                        .accountsReceivables(sumCashFlowStatement(quarters, CashFlowStatement::getAccountsReceivables))
                        .inventory(sumCashFlowStatement(quarters, CashFlowStatement::getInventory))
                        .accountsPayables(sumCashFlowStatement(quarters, CashFlowStatement::getAccountsPayables))
                        .otherWorkingCapital(sumCashFlowStatement(quarters, CashFlowStatement::getOtherWorkingCapital))
                        .otherNonCashItems(sumCashFlowStatement(quarters, CashFlowStatement::getOtherNonCashItems))
                        .netCashFromOperatingActivities(sumCashFlowStatement(quarters, CashFlowStatement::getNetCashFromOperatingActivities))
                        .investmentsInPropertyPlantAndEquipment(sumCashFlowStatement(quarters, CashFlowStatement::getInvestmentsInPropertyPlantAndEquipment))
                        .netAcquisitions(sumCashFlowStatement(quarters, CashFlowStatement::getNetAcquisitions))
                        .purchasesOfInvestments(sumCashFlowStatement(quarters, CashFlowStatement::getPurchasesOfInvestments))
                        .salesMaturitiesOfInvestments(sumCashFlowStatement(quarters, CashFlowStatement::getSalesMaturitiesOfInvestments))
                        .otherInvestingActivities(sumCashFlowStatement(quarters, CashFlowStatement::getOtherInvestingActivities))
                        .netCashFromInvestingActivities(sumCashFlowStatement(quarters, CashFlowStatement::getNetCashFromInvestingActivities))
                        .debtRepayment(sumCashFlowStatement(quarters, CashFlowStatement::getDebtRepayment))
                        .commonStockIssued(sumCashFlowStatement(quarters, CashFlowStatement::getCommonStockIssued))
                        .commonStockRepurchased(sumCashFlowStatement(quarters, CashFlowStatement::getCommonStockRepurchased))
                        .dividendsPaid(sumCashFlowStatement(quarters, CashFlowStatement::getDividendsPaid))
                        .otherFinancingActivities(sumCashFlowStatement(quarters, CashFlowStatement::getOtherFinancingActivities))
                        .netCashFromFinancingActivities(sumCashFlowStatement(quarters, CashFlowStatement::getNetCashFromFinancingActivities))
                        .effectOfForexChangesOnCash(sumCashFlowStatement(quarters, CashFlowStatement::getEffectOfForexChangesOnCash))
                        .netChangeInCash(sumCashFlowStatement(quarters, CashFlowStatement::getNetChangeInCash))
                        .cashAtEndOfPeriod(sumCashFlowStatement(quarters, CashFlowStatement::getCashAtEndOfPeriod))
                        .cashAtBeginningOfPeriod(sumCashFlowStatement(quarters, CashFlowStatement::getCashAtBeginningOfPeriod))
                        .operatingCashFlow(sumCashFlowStatement(quarters, CashFlowStatement::getOperatingCashFlow))
                        .capitalExpenditure(sumCashFlowStatement(quarters, CashFlowStatement::getCapitalExpenditure))
                        .freeCashFlow(sumCashFlowStatement(quarters, CashFlowStatement::getFreeCashFlow))
                        .build())
                .build();
    }

    private double sumIncomeStatement(final List<FiscalPeriod> quarters, final ToDoubleFunction<IncomeStatement> extractor) {
        return quarters.stream()
                .map(FiscalPeriod::getIncomeStatement)
                .mapToDouble(extractor)
                .sum();
    }

    private double sumBalanceSheet(final List<FiscalPeriod> quarters, final ToDoubleFunction<BalanceSheet> extractor) {
        return quarters.stream()
                .map(FiscalPeriod::getBalanceSheet)
                .mapToDouble(extractor)
                .sum();
    }

    private double sumCashFlowStatement(final List<FiscalPeriod> quarters, final ToDoubleFunction<CashFlowStatement> extractor) {
        return quarters.stream()
                .map(FiscalPeriod::getCashFlowStatement)
                .mapToDouble(extractor)
                .sum();
    }
}

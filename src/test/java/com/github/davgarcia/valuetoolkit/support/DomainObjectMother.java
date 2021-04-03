package com.github.davgarcia.valuetoolkit.support;

import com.github.davgarcia.valuetoolkit.BalanceSheet;
import com.github.davgarcia.valuetoolkit.Company;
import com.github.davgarcia.valuetoolkit.CompanyEstimates;
import com.github.davgarcia.valuetoolkit.CompanyIndicators;
import com.github.davgarcia.valuetoolkit.CompanyLocator;
import com.github.davgarcia.valuetoolkit.CompanyProfile;
import com.github.davgarcia.valuetoolkit.CashFlowStatement;
import com.github.davgarcia.valuetoolkit.IncomeStatement;
import com.github.davgarcia.valuetoolkit.PeriodIndicators;
import com.github.davgarcia.valuetoolkit.adapter.fmp.FmpAdapter;
import com.github.davgarcia.valuetoolkit.config.EconomicFactors;
import com.github.davgarcia.valuetoolkit.config.ValuationFactors;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;

import java.time.LocalDate;
import java.util.Map;

public class DomainObjectMother {

    private static final ValueToolkitConfigProperties PARAMS = new ValueToolkitConfigProperties(
            EconomicFactors.builder()
                    .erp(4.63)
                    .riskFreeRate(Map.of("usd", 1.64))
                    .countryRiskRate(Map.of("cn", 1.00))
                    .gdpGrowthRate(Map.of("us", 2.28))
                    .build(),
            ValuationFactors.builder()
                    .build());
    private static final CompanyLocator BUSINESS_LOCATOR = new CompanyLocator("NASDAQ", "MSFT");
    private static final CompanyProfile BUSINESS_PROFILE = CompanyProfile.builder()
            .name("Microsoft Corp")
            .description("Microsoft Corporation develops, licenses, and supports software, services, devices, and solutions worldwide...")
            .ceo("Mr. Satya Nadella")
            .industry("Software Infrastructure")
            .country("US")
            .currency("USD")
            .price(234.81)
            .beta(0.85519)
            .marketCap(1770988630000d)
            .build();
    private static final IncomeStatement INCOME_STATEMENT_2020 = IncomeStatement.builder()
            .revenue(143015000000d)
            .costOfRevenue(46078000000d)
            .grossProfit(96937000000d)
            .researchAndDevelopmentExpenses(19269000000d)
            .generalAndAdministrativeExpenses(24709000000d)
            .sellingAndMarketingExpenses(19598000000d)
            .otherExpenses(-40000000d)
            .operatingExpenses(43978000000d)
            .interestExpense(2591000000d)
            .depreciationAndAmortization(12796000000d)
            .ebitda(65715000000d)
            .operatingIncome(52959000000d)
            .totalOtherIncomeExpensesNet(2668000000d)
            .incomeBeforeTax(53036000000d)
            .incomeTaxExpense(8755000000d)
            .netIncome(44281000000d)
            .eps(5.82)
            .epsDiluted(5.76)
            .weightedAverageSharesOutstanding(7610000000d)
            .weightedAverageSharesOutstandingDiluted(7683000000d)
            .build();
    private static final BalanceSheet BALANCE_SHEET_2020 = BalanceSheet.builder()
            .cashAndCashEquivalents(13576000000d)
            .shortTermInvestments(122951000000d)
            .netReceivables(32011000000d)
            .inventory(1895000000d)
            .otherCurrentAssets(11482000000d)
            .totalCurrentAssets(181915000000d)
            .netPropertyPlantEquipment(52904000000d)
            .goodwill(43351000000d)
            .intangibleAssets(7038000000d)
            .longTermInvestments(0.0)
            .taxAssets(0.0)
            .otherNonCurrentAssets(13138000000d)
            .totalNonCurrentAssets(119396000000d)
            .otherAssets(11482000000d)
            .totalAssets(301311000000d)
            .accountPayables(12530000000d)
            .shortTermDebt(3749000000d)
            .taxPayables(0.0)
            .deferredRevenue(36000000000d)
            .otherCurrentLiabilities(10027000000d)
            .totalCurrentLiabilities(72310000000d)
            .longTermDebt(59578000000d)
            .deferredRevenueNonCurrent(3180000000d)
            .deferredTaxLiabilitiesNonCurrent(204000000d)
            .otherNonCurrentLiabilities(10632000000d)
            .totalNonCurrentLiabilities(110697000000d)
            .otherLiabilities(10027000000d)
            .totalLiabilities(183007000000d)
            .commonStock(80552000000d)
            .retainedEarnings(34566000000d)
            .accumulatedOtherComprehensiveIncomeLoss(3186000000d)
            .othertotalStockholdersEquity(0.0)
            .totalStockholdersEquity(118304000000d)
            .totalInvestments(0.0)
            .totalDebt(63327000000d)
            .netDebt(49751000000d)
            .build();
    private static final CashFlowStatement CASH_FLOW_STATEMENT_2020 = CashFlowStatement.builder()
            .netIncome(44281000000d)
            .depreciationAndAmortization(12796000000d)
            .deferredIncomeTax(-3620000000d)
            .stockBasedCompensation(5289000000d)
            .changeInWorkingCapital(3473000000d)
            .accountsReceivables(-2577000000d)
            .inventory(168000000d)
            .accountsPayables(3018000000d)
            .otherWorkingCapital(109605000000d)
            .otherNonCashItems(0.0)
            .netCashFromOperatingActivities(60675000000d)
            .investmentsInPropertyPlantAndEquipment(-15441000000d)
            .netAcquisitions(-2521000000d)
            .purchasesOfInvestments(-77190000000d)
            .salesMaturitiesOfInvestments(84170000000d)
            .otherInvestingActivities(-1241000000d)
            .netCashFromInvestingActivities(-12223000000d)
            .debtRepayment(-5518000000d)
            .commonStockIssued(1343000000d)
            .commonStockRepurchased(-22968000000d)
            .dividendsPaid(-15137000000d)
            .otherFinancingActivities(-3751000000d)
            .netCashFromFinancingActivities(0.0)
            .effectOfForexChangesOnCash(0.0)
            .netChangeInCash(2220000000d)
            .cashAtEndOfPeriod(13576000000d)
            .cashAtBeginningOfPeriod(11356000000d)
            .operatingCashFlow(60675000000d)
            .capitalExpenditure(-15441000000d)
            .freeCashFlow(45234000000d)
            .build();
    private static final CompanyEstimates BUSINESS_ESTIMATES = CompanyEstimates.builder()
            .growthYears(10)
            .perTarget(30d)
            .revenueCagr(12.5)
            .fcfCagr(12.5)
            .build();

    private static final Company COMPANY = Company.builder()
            .locator(BUSINESS_LOCATOR)
            .profile(BUSINESS_PROFILE)
            // Beware this is using real fiscal years from 2016 to 2020!
            .yearPeriods(new FmpAdapter(new FakeFmpFeignClient())
                    .getFiscalYears(BUSINESS_LOCATOR, LocalDate.ofYearDay(2016, 1), LocalDate.ofYearDay(2020, 1)))
            .estimates(BUSINESS_ESTIMATES)
            .build();

    private DomainObjectMother() {
        // Empty.
    }

    public static ValueToolkitConfigProperties params() {
        return PARAMS;
    }

    public static CompanyLocator companyLocator() {
        return BUSINESS_LOCATOR;
    }

    public static CompanyProfile companyProfile() {
        return BUSINESS_PROFILE;
    }

    public static IncomeStatement incomeStatement2020() {
        return INCOME_STATEMENT_2020;
    }

    public static BalanceSheet balanceSheet2020() {
        return BALANCE_SHEET_2020;
    }

    public static CashFlowStatement cashFlowStatement2020() {
        return CASH_FLOW_STATEMENT_2020;
    }

    public static CompanyEstimates companyEstimates() {
        return BUSINESS_ESTIMATES;
    }

    public static Company company() {
        if (COMPANY.getIndicators() == null) {
            COMPANY.setIndicators(new CompanyIndicators(PARAMS, COMPANY));
            COMPANY.getYearPeriods().forEach(p -> p.setIndicators(new PeriodIndicators(p)));
        }
        return COMPANY;
    }
}

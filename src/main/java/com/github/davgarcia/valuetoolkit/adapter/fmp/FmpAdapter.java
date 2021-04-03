package com.github.davgarcia.valuetoolkit.adapter.fmp;

import com.github.davgarcia.valuetoolkit.CompanyDataProvider;
import com.github.davgarcia.valuetoolkit.CompanyLocator;
import com.github.davgarcia.valuetoolkit.CompanyProfile;
import com.github.davgarcia.valuetoolkit.Period;
import org.apache.commons.lang3.tuple.Pair;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Financial Modeling Prep API
 * <p>
 * Doc: https://financialmodelingprep.com/developer/docs/
 */
public class FmpAdapter implements CompanyDataProvider {

    private final FmpFeignClient feignClient;

    private Clock clock;

    public FmpAdapter(final FmpFeignClient feignClient) {
        this.feignClient = feignClient;
        clock = Clock.systemDefaultZone();
    }

    /**
     * Only for testing.
     *
     * @param clock the clock to get the current date from; null to restore default clock.
     */
    public void setClock(final Clock clock) {
        this.clock = clock == null ? Clock.systemDefaultZone() : clock;
    }

    @Override
    public CompanyProfile getCompanyProfile(final CompanyLocator locator) {
        final var profiles = feignClient.getCompanyProfile(buildId(locator));
        return profiles.length == 1 ? profiles[0] : null;
    }

    @Override
    public List<Period> getFiscalYears(final CompanyLocator locator, final LocalDate first, final LocalDate last) {
        final var periodBuilders = IntStream.rangeClosed(first.getYear(), last.getYear())
                .mapToObj(y -> Pair.of(y, Period.builder()
                        .type(Period.Type.YEAR)
                        .status(Period.Status.ACTUAL)
                        .name("FY-" + y)
                        .date(LocalDate.ofYearDay(y, 1))))
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));

        final var id = buildId(locator);
        final var limit = LocalDate.now(clock).getYear() - first.getYear() + 1;
        final var incomeDtos = feignClient.getIncomeStatement(id, limit, null);
        final var balanceDtos = feignClient.getBalanceSheet(id, limit, null);
        final var cashFlowDtos = feignClient.getCashFlowStatement(id, limit, null);
        for (final var incomeDto : incomeDtos) {
            final var periodBuilder = periodBuilders.get(incomeDto.getDate().getYear());
            if (periodBuilder != null) {
                periodBuilder.incomeStatement(FmpMapper.INSTANCE.toIncomeStatement(incomeDto));
            }
        }
        for (final var balanceDto : balanceDtos) {
            final var periodBuilder = periodBuilders.get(balanceDto.getDate().getYear());
            if (periodBuilder != null) {
                periodBuilder.balanceSheet(FmpMapper.INSTANCE.toBalanceSheet(balanceDto));
            }
        }
        for (final var cashFlowDto : cashFlowDtos) {
            final var periodBuilder = periodBuilders.get(cashFlowDto.getDate().getYear());
            if (periodBuilder != null) {
                periodBuilder.cashFlowStatement(FmpMapper.INSTANCE.toCashFlowStatement(cashFlowDto));
            }
        }

        return periodBuilders.values().stream()
                .map(Period.PeriodBuilder::build)
                .sorted((p1, p2) -> p2.getDate().compareTo(p1.getDate())) // Inverse order!
                .collect(Collectors.toList());
    }

    @Override
    public List<Period> getQuarters(final CompanyLocator locator, final LocalDate first, final LocalDate last) {
        return null;
    }

    private String buildId(final CompanyLocator locator) {
        final var suffix = FmpExchange.suffixForValue(locator.getExchange());
        return suffix == null ? locator.getSymbol() :
                String.format("%s.%s", locator.getSymbol(), suffix);
    }


}

package com.github.davgarcia.valuetoolkit.adapter.fmp;

import com.github.davgarcia.valuetoolkit.BusinessDataProvider;
import com.github.davgarcia.valuetoolkit.BusinessLocator;
import com.github.davgarcia.valuetoolkit.BusinessProfile;
import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import org.apache.commons.lang3.tuple.Pair;
import org.threeten.extra.YearQuarter;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Financial Modeling Prep API
 * <p>
 * Doc: https://financialmodelingprep.com/developer/docs/
 */
public class FmpAdapter implements BusinessDataProvider {

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
    public BusinessProfile getBusinessProfile(final BusinessLocator locator) {
        final var profiles = feignClient.getBusinessProfile(buildId(locator));
        return profiles.length == 1 ? profiles[0] : null;
    }

    @Override
    public List<FiscalPeriod> getFiscalYears(final BusinessLocator locator, final Year first, final Year last) {
        final var builders = IntStream.rangeClosed(first.getValue(), last.getValue())
                .mapToObj(y -> Pair.of(Year.of(y), FiscalPeriod.builder()
                        .type(FiscalPeriod.Type.YEAR)
                        .name(String.format("FY-%d", y))))
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
        final var limit = (int) first.until(Year.now(clock), ChronoUnit.YEARS) + 1;

        return fetchPeriods(locator, builders, Year::from, limit, null);
    }

    @Override
    public List<FiscalPeriod> getQuarters(final BusinessLocator locator, final YearQuarter first, final YearQuarter last) {
        final var builders = first.quartersUntil(last.plusQuarters(1L))
                .map(q -> Pair.of(q, FiscalPeriod.builder()
                        .type(FiscalPeriod.Type.QUARTER)
                        .name(String.format("Q%d-%d", q.getQuarterValue(), q.getYear()))))
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
        final var limit = (int) first.until(last, IsoFields.QUARTER_YEARS) + 1;

        return fetchPeriods(locator, builders, YearQuarter::from, limit, "quarter");
    }

    private <T extends Temporal> List<FiscalPeriod> fetchPeriods(final BusinessLocator locator,
                                            final Map<T, FiscalPeriod.FiscalPeriodBuilder> builders,
                                            final Function<LocalDate, T> keyMapper,
                                            final int limit, final String type) {
        final var id = buildId(locator);
        final var incomeDtos = feignClient.getIncomeStatement(id, limit, type);
        final var balanceDtos = feignClient.getBalanceSheet(id, limit, type);
        final var cashFlowDtos = feignClient.getCashFlowStatement(id, limit, type);
        for (final var incomeDto : incomeDtos) {
            final var key = keyMapper.apply(incomeDto.getDate());
            final var builder = builders.get(key);
            if (builder != null) {
                // Also set fiscal period's date.
                builder.date(incomeDto.getDate()).incomeStatement(FmpMapper.INSTANCE.toIncomeStatement(incomeDto));
            }
        }
        for (final var balanceDto : balanceDtos) {
            final var key = keyMapper.apply(balanceDto.getDate());
            final var builder = builders.get(key);
            if (builder != null) {
                builder.balanceSheet(FmpMapper.INSTANCE.toBalanceSheet(balanceDto));
            }
        }
        for (final var cashFlowDto : cashFlowDtos) {
            final var key = keyMapper.apply(cashFlowDto.getDate());
            final var builder = builders.get(key);
            if (builder != null) {
                builder.cashFlowStatement(FmpMapper.INSTANCE.toCashFlowStatement(cashFlowDto));
            }
        }

        return builders.values().stream()
                .map(FiscalPeriod.FiscalPeriodBuilder::build)
                .filter(p -> p.getDate() != null)
                .sorted((p1, p2) -> p2.getDate().compareTo(p1.getDate())) // Inverse order!
                .collect(Collectors.toList());
    }

    private String buildId(final BusinessLocator locator) {
        final var suffix = FmpExchange.suffixForValue(locator.getExchange());
        return suffix == null ? locator.getSymbol() :
                String.format("%s.%s", locator.getSymbol(), suffix);
    }


}

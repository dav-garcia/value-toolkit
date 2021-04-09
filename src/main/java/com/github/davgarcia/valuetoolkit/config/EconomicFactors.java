package com.github.davgarcia.valuetoolkit.config;

import com.github.davgarcia.valuetoolkit.Business;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class EconomicFactors {

    private static final double M = 1000000;

    @Value
    public static class SizeRiskRate {

        Double large;
        Double mid;
        Double small;
        Double micro;
    }

    /**
     * Equity Risk Premium (ERP), required.
     * <br>
     * See: http://pages.stern.nyu.edu/~adamodar/
     */
    double erp;

    /**
     * Risk-free rates by currency, based on 10Y bond rates. Required.
     * <br>
     * See: https://www.bloomberg.com/markets/rates-bonds
     */
    Map<String, Double> riskFreeRate;

    /**
     * Optional additional risk rates by country.
     */
    Map<String, Double> countryRiskRate;

    /**
     * Optional additional risk rates depending on CapSize.
     */
    SizeRiskRate sizeRiskRate;

    /**
     * Historical (average for a number of years) GDP growth rates by country. Required.
     * <br>
     * List by the World Bank: https://en.wikipedia.org/wiki/List_of_countries_by_real_GDP_growth_rate
     */
    Map<String, Double> gdpGrowthRate;

    public double getRiskFreeRate(final Business business) {
        return riskFreeRate.get(business.getProfile().getCurrency().toLowerCase());
    }

    public double getCounrtyRiskRate(final Business business) {
        if (countryRiskRate == null) {
            return 0d;
        }

        return countryRiskRate.getOrDefault(business.getProfile().getCountry().toLowerCase(), 0d);
    }

    public double getSizeRiskRate(final Business business) {
        if (sizeRiskRate == null) {
            return 0d;
        }

        final var marketCap = business.getProfile().getMarketCap();
        if (marketCap > 4000 * M) {
            return valueOrDefault(sizeRiskRate.large, 0d);
        } else if (marketCap > 800 * M) {
            return valueOrDefault(sizeRiskRate.mid, 0d);
        } else if (marketCap > 200 * M) {
            return valueOrDefault(sizeRiskRate.small, 0d);
        }
        return valueOrDefault(sizeRiskRate.micro, 0d);
    }

    public double getGdpGrowthRate(final Business business) {
        return gdpGrowthRate.get(business.getProfile().getCountry().toLowerCase());
    }

    private double valueOrDefault(final Double value, final double defaultValue) {
        return value == null ? defaultValue : value;
    }
}

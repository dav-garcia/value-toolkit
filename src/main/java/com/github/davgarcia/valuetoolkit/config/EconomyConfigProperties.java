package com.github.davgarcia.valuetoolkit.config;

import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Map;

@ConfigurationProperties("economy")
@ConstructorBinding
@Value
@NonFinal
public class EconomyConfigProperties {

    @Value
    public static class SizeRiskRate {

        Double large;
        Double mid;
        Double small;
        Double micro;
    }

    /**
     * Equity Risk Premium (ERP): http://pages.stern.nyu.edu/~adamodar/
     */
    double erp;

    /**
     * Risk-free rates by currency, based on 10Y bond rates: https://www.bloomberg.com/markets/rates-bonds
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
     * Historical (average for a number of years) GDP growth rates by country.
     * <br>
     * List by the World Bank: https://en.wikipedia.org/wiki/List_of_countries_by_real_GDP_growth_rate
     */
    Map<String, Double> gdpGrowthRate;

    public double getRiskFreeRate(final String currency) {
        return riskFreeRate.get(currency.toLowerCase());
    }

    public double getCounrtyRiskRate(final String country) {
        return countryRiskRate.get(country.toLowerCase());
    }

    public double getGdpGrowthRate(final String country) {
        return gdpGrowthRate.get(country.toLowerCase());
    }
}

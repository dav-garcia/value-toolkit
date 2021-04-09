package com.github.davgarcia.valuetoolkit;

import lombok.Builder;
import lombok.Value;

/**
 * Business valuation output, composed of:
 * <ol>
 *     <li>Intrinsic value by the various valuation methods.</li>
 *     <li>Business multiples & average industry multiples.</li>
 *     <li>Liquidity & solvency ratios for the latest period and on average.</li>
 *     <li>Return metrics for the latest period and on average.</li>
 *     <li>Most important CAGR estimates for the future.</li>
 *     <li>Owner's commitment to the company.</li>
 *     <li>Explanations of how the valuation methods have computed an intrinsic value.</li>
 * </ol>
 */
@Value
@Builder
public class BusinessResult {

    @Value
    @Builder
    public static class Intrinsic {

        double dcfValue;
        double evaValue;
        double ownerEarningsValue;
        double realOptionsValue;
        double simpleValue;
    }

    @Value
    @Builder
    public static class Multiples {

        double perTrailing;
        double pegrTrailing;
        double priceToSales;
        double priceToBookValue;
        double priceToFcf;
    }

    @Value
    @Builder
    public static class Health {

        double currentRatio;
        double quickRatio;
        double daysSalesOutstanding;
        double debtToEquityRatio;
    }

    @Value
    @Builder
    public static class Returns {

        double roa;
        double roe;
        double roce;
        double roic;
    }

    @Value
    @Builder
    public static class Estimates {

        double revenueCagr;
        double fcfCagr;
        double pratCagr;
    }

    @Value
    @Builder
    public static class Commitment {

        double sharesRatio;
        double insiderOwnership;
        double insiderBuyRatio;
        double insiderSellRatio;
        double insiderExerciseRatio;
    }

    @Value
    @Builder
    public static class Explanations {

        String dcf;
        String eva;
        String ownerEarnings;
        String realOptions;
        String simple;
    }

    /**
     * Intrinsic value by the various valuation methods.
     */
    Intrinsic intrinsic;

    /**
     * Business multiples.
     */
    Multiples multiples;

    /**
     * Average industry multiples.
     */
    Multiples industryMultiples;

    /**
     * Liquidity & solvency ratios for the latest period.
     */
    Health health;

    /**
     * Liquidity & solvency ratios on average.
     */
    Health averageHealth;

    /**
     * Return metrics for the latest period.
     */
    Returns returns;

    /**
     * Return metrics on average.
     */
    Returns averageReturns;

    /**
     * Most important CAGR estimates for the future.
     */
    Estimates estimates;

    /**
     * Owner's commitment to the company.
     */
    Commitment commitment;

    /**
     * Explanations of how the valuation methods have computed an intrinsic value.
     */
    Explanations explanations;
}

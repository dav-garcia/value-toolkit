package com.github.davgarcia.valuetoolkit.domain;

import lombok.Value;

/**
 * The business identifier used to locate its financial data.
 */
@Value
public class BusinessLocator {

    /**
     * MIC acronym (per ISO 10383), e.g. TSX instead of XTSE code.
     */
    String exchange;

    /**
     * Ticker symbol
     */
    String symbol;
}

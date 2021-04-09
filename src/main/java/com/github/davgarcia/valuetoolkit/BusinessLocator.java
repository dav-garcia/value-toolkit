package com.github.davgarcia.valuetoolkit;

import lombok.Value;

/**
 * The company identifier used to locate its financial data.
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

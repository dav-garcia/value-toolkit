package com.github.davgarcia.valuetoolkit;

/**
 * Computes a period indicator, i.e. a value that only depends and is fully tied to a specific period.
 */
public interface PeriodIndicator {

    double eval(final Period period);
}

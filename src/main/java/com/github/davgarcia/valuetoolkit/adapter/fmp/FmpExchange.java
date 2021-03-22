package com.github.davgarcia.valuetoolkit.adapter.fmp;

public enum FmpExchange {

    LSE("L"),
    TSX("TO"),
    XETRA("DE");

    public static String suffixForValue(final String name) {
        try {
            return FmpExchange.valueOf(name).suffix;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private final String suffix;

    FmpExchange(final String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}

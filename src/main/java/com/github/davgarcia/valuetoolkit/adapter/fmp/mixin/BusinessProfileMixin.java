package com.github.davgarcia.valuetoolkit.adapter.fmp.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("java:S1610")
public abstract class BusinessProfileMixin {

    @JsonProperty("exchangeShortName")
    public abstract String getExchange();

    @JsonProperty("companyName")
    public abstract String getName();

    @JsonProperty("mktCap")
    public abstract String getMarketCap();
}

package com.github.davgarcia.valuetoolkit.adapter.fmp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface BusinessProfileMixin {

    @JsonProperty("companyName")
    public abstract String getName();

    @JsonProperty("mktCap")
    public abstract String getMarketCap();
}

package com.github.davgarcia.valuetoolkit.adapter.fmp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface BusinessProfileMixin {

    @JsonProperty("companyName")
    String getName();

    @JsonProperty("mktCap")
    String getMarketCap();
}

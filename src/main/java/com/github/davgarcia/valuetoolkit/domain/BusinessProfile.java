package com.github.davgarcia.valuetoolkit.domain;

import lombok.Builder;
import lombok.Value;

/**
 * Business profile information.
 */
@Value
@Builder
public class BusinessProfile {

    String name;
    String description;
    String website;
    String ceo;
    String industry;
    String country;
    float price;
    float beta;
    long marketCap;
}

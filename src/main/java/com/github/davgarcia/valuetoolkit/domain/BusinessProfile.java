package com.github.davgarcia.valuetoolkit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Business profile information.
 */
@Value
@AllArgsConstructor
@Builder
public class BusinessProfile {

    String name;
    String description;
    String ceo;
    String industry;
    String country;
    String currency;
    double price;
    double beta;
    double marketCap;
}

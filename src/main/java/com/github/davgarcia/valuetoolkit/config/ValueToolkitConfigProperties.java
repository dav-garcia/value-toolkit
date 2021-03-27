package com.github.davgarcia.valuetoolkit.config;

import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("vt")
@ConstructorBinding
@Value
@NonFinal
public class ValueToolkitConfigProperties {

    EconomicFactors economicFactors;
    ValuationFactors valuationFactors;
}

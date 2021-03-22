package com.github.davgarcia.valuetoolkit.adapter.fmp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.davgarcia.valuetoolkit.adapter.fmp.mixin.BusinessProfileMixin;
import com.github.davgarcia.valuetoolkit.domain.BusinessProfile;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class FmpFeignClientConfig {

    @Bean
    public RequestInterceptor fmpFeignClientInterceptor(@Value("${provider.fmp.api-key}") final String apiKey) {
        return template -> template.query("apikey", apiKey);
    }

    @Bean
    public Decoder fmpFeignClientDecoder() {
        final var jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

    private ObjectMapper customObjectMapper() {
        final var objectMapper = new ObjectMapper();

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.addMixIn(BusinessProfile.class, BusinessProfileMixin.class);

        return objectMapper;
    }
}

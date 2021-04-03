package com.github.davgarcia.valuetoolkit.adapter.fmp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.davgarcia.valuetoolkit.adapter.fmp.dto.CompanyProfileMixin;
import com.github.davgarcia.valuetoolkit.CompanyProfile;
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
        final var jacksonConverter = new MappingJackson2HttpMessageConverter(buildObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

    private ObjectMapper buildObjectMapper() {
        final var result = new ObjectMapper();

        result.registerModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .addMixIn(CompanyProfile.class, CompanyProfileMixin.class);

        return result;
    }
}

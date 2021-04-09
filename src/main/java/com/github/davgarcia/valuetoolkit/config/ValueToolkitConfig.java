package com.github.davgarcia.valuetoolkit.config;

import com.github.davgarcia.valuetoolkit.BusinessDataProvider;
import com.github.davgarcia.valuetoolkit.ValueToolkitService;
import com.github.davgarcia.valuetoolkit.adapter.fmp.FmpAdapter;
import com.github.davgarcia.valuetoolkit.adapter.fmp.FmpFeignClient;
import com.github.davgarcia.valuetoolkit.adapter.local.LocalAdapterWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.nio.file.Path;

@Configuration
public class ValueToolkitConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ValueToolkitConfig.class);

    @Bean
    @ConditionalOnProperty(value = "provider.active", havingValue = "fmp")
    public BusinessDataProvider fmpAdapter(final FmpFeignClient feignClient) {
        return new FmpAdapter(feignClient);
    }

    @Bean
    @Primary
    public BusinessDataProvider localAdapterWrapper(final BusinessDataProvider provider,
                                                    @Value("${provider.local.dir}") final String localDir) {
        LOG.info("Active data provider: {}", provider.getClass().getSimpleName());
        LOG.info("Local directory: {}", localDir);
        return new LocalAdapterWrapper(provider, Path.of(localDir));
    }

    @Bean
    public ValueToolkitService valueToolkitService(final ValueToolkitConfigProperties params,
                                                   final BusinessDataProvider provider) {
        return new ValueToolkitService(params, provider);
    }
}

package com.github.davgarcia.valuetoolkit.adapter.fmp;

import com.github.davgarcia.valuetoolkit.domain.BusinessProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fmpFeignClient", url = "${provider.fmp.url}", configuration = FmpFeignClientConfig.class)
public interface FmpFeignClient {

    @GetMapping("/api/v3/profile/{id}")
    BusinessProfile[] getBusinessProfile(@PathVariable final String id);
}

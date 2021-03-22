package com.github.davgarcia.valuetoolkit;

import com.github.davgarcia.valuetoolkit.domain.BusinessProfile;

public class DomainObjectMother {

    private static final BusinessProfile BUSINESS_PROFILE = BusinessProfile.builder()
            .name("Microsoft Corp")
            .ceo("Mr. Satya Nadella")
            .industry("Software Infrastructure")
            .country("US")
            .price(236.45f)
            .beta(0.85519f)
            .marketCap(1783357900000L)
            .build();

    private DomainObjectMother() {
        // Empty.
    }

    public static BusinessProfile businessProfile() {
        return BUSINESS_PROFILE;
    }
}

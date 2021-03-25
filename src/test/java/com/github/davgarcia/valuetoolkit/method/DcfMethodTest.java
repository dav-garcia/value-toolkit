package com.github.davgarcia.valuetoolkit.method;

import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import com.github.davgarcia.valuetoolkit.TimeMachine;
import com.github.davgarcia.valuetoolkit.adapter.fmp.FmpAdapter;
import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.BusinessIndicators;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.mockserver.model.Parameter;
import org.mockserver.model.Parameters;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.Parameter.param;

// TODO: Refactor this test to avoid Spring context initialization.
@SpringBootTest(properties = {
        "provider.fmp.api-key=12345678"
})
@MockServerTest("provider.fmp.url=http://localhost:${mockServerPort}")
class DcfMethodTest {

    private static final String INCOME_FILENAME = "fmp/MSFT-income.json";
    private static final String BALANCE_FILENAME = "fmp/MSFT-balance.json";
    private static final String CASHFLOW_FILENAME = "fmp/MSFT-cashflow.json";

    private MockServerClient mockServerClient;

    @Autowired
    private FmpAdapter fmpAdapter;

    private final DcfMethod sut = new DcfMethod();

    @Test
    void givenBusinessThenComputeDcf() throws IOException {
        fmpAdapter.setClock(TimeMachine.clockAt(LocalDate.ofYearDay(2020, 1)));
        setupFmpMock(INCOME_FILENAME, "/income-statement/MSFT", param("limit", "5"));
        setupFmpMock(BALANCE_FILENAME, "/balance-sheet-statement/MSFT", param("limit", "5"));
        setupFmpMock(CASHFLOW_FILENAME, "/cash-flow-statement/MSFT", param("limit", "5"));

        final var periods = fmpAdapter.getFiscalYears(DomainObjectMother.businessLocator(),
                LocalDate.ofYearDay(2016, 1), LocalDate.ofYearDay(2020, 1));
        final var economy = DomainObjectMother.economy();
        final var business = Business.builder()
                .locator(DomainObjectMother.businessLocator())
                .profile(DomainObjectMother.businessProfile())
                .periods(periods)
                .estimates(DomainObjectMother.businessEstimates())
                .build();
        business.setIndicators(new BusinessIndicators(economy, business));

        final var result = sut.value(economy, business);

        assertThat(result).isEqualTo(377.98164136856104);
    }

    private void setupFmpMock(final String resourceFilename, final String subpath, final Parameter... param) throws IOException {
        final var params = new Parameters(param);
        params.withEntry("apikey", "12345678");
        mockServerClient
                .when(request()
                        .withMethod("GET")
                        .withPath("/api/v3" + subpath)
                        .withQueryStringParameters(params))
                .respond(response()
                        .withStatusCode(200)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(loadResource(resourceFilename)));
    }

    private String loadResource(final String resourcePath) throws IOException {
        final var path = ResourceUtils.getFile("classpath:" + resourcePath).toPath();
        return StringUtils.stripEnd(Files.readString(path, StandardCharsets.UTF_8), null);
    }
}

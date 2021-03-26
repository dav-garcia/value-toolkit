package com.github.davgarcia.valuetoolkit.adapter.fmp;

import com.github.davgarcia.valuetoolkit.DomainObjectMother;
import com.github.davgarcia.valuetoolkit.TimeMachine;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.mockserver.model.Parameter;
import org.mockserver.model.Parameters;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.Parameter.param;

@SpringBootTest(properties = {
        "provider.fmp.api-key=12345678"
})
@MockServerTest("provider.fmp.url=http://localhost:${mockServerPort}")
class FmpAdapterTest {

    private static final String PROFILE_FILENAME = "/fmp/MSFT-profile.json";
    private static final String INCOME_FILENAME = "/fmp/MSFT-income.json";
    private static final String BALANCE_FILENAME = "/fmp/MSFT-balance.json";
    private static final String CASHFLOW_FILENAME = "/fmp/MSFT-cashflow.json";

    private MockServerClient mockServerClient;

    @Autowired
    private FmpAdapter sut;

    @Test
    void givenLocatorThenDownloadProfile() throws IOException {
        setupFmpMock(PROFILE_FILENAME, "/profile/MSFT");

        final var result = sut.getBusinessProfile(DomainObjectMother.businessLocator());

        assertThat(result).isEqualTo(DomainObjectMother.businessProfile());
    }

    @Test
    void givenLocatorThenDownloadFiscalYears() throws IOException {
        try {
            sut.setClock(TimeMachine.clockAt(LocalDate.ofYearDay(2020, 1)));
            setupFmpMock(INCOME_FILENAME, "/income-statement/MSFT", param("limit", "5"));
            setupFmpMock(BALANCE_FILENAME, "/balance-sheet-statement/MSFT", param("limit", "5"));
            setupFmpMock(CASHFLOW_FILENAME, "/cash-flow-statement/MSFT", param("limit", "5"));

            final var result = sut.getFiscalYears(DomainObjectMother.businessLocator(),
                    LocalDate.ofYearDay(2016, 1), LocalDate.ofYearDay(2020, 1));

            assertThat(result).hasSize(5);
            assertThat(result).allMatch(p -> p.getIncomeStatement() != null && p.getBalanceSheet() != null && p.getCashFlowStatement() != null);
            assertThat(result.get(0).getIncomeStatement()).isEqualTo(DomainObjectMother.incomeStatement2020());
            assertThat(result.get(0).getBalanceSheet()).isEqualTo(DomainObjectMother.balanceSheet2020());
            assertThat(result.get(0).getCashFlowStatement()).isEqualTo(DomainObjectMother.cashFlowStatement2020());
        } finally {
            sut.setClock(null);
        }
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
                        .withBody(IOUtils.resourceToByteArray(resourceFilename)));
    }
}

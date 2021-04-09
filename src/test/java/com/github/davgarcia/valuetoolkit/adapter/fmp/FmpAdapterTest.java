package com.github.davgarcia.valuetoolkit.adapter.fmp;

import com.github.davgarcia.valuetoolkit.support.DomainObjectMother;
import com.github.davgarcia.valuetoolkit.support.TimeMachine;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.mockserver.model.Parameter;
import org.mockserver.model.Parameters;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.threeten.extra.YearQuarter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;

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
    private static final String FY_INCOME_FILENAME = "/fmp/MSFT-fy-income.json";
    private static final String FY_BALANCE_FILENAME = "/fmp/MSFT-fy-balance.json";
    private static final String FY_CASHFLOW_FILENAME = "/fmp/MSFT-fy-cashflow.json";
    private static final String FQ_INCOME_FILENAME = "/fmp/MSFT-fq-income.json";
    private static final String FQ_BALANCE_FILENAME = "/fmp/MSFT-fq-balance.json";
    private static final String FQ_CASHFLOW_FILENAME = "/fmp/MSFT-fq-cashflow.json";

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
            setupFmpMock(FY_INCOME_FILENAME, "/income-statement/MSFT", param("limit", "5"));
            setupFmpMock(FY_BALANCE_FILENAME, "/balance-sheet-statement/MSFT", param("limit", "5"));
            setupFmpMock(FY_CASHFLOW_FILENAME, "/cash-flow-statement/MSFT", param("limit", "5"));

            final var result = sut.getFiscalYears(DomainObjectMother.businessLocator(), Year.of(2016), Year.of(2020));

            assertThat(result).hasSize(5);
            assertThat(result).allMatch(p -> p.getIncomeStatement() != null && p.getBalanceSheet() != null && p.getCashFlowStatement() != null);
            assertThat(result.get(0).getIncomeStatement()).isEqualTo(DomainObjectMother.incomeStatement2020());
            assertThat(result.get(0).getBalanceSheet()).isEqualTo(DomainObjectMother.balanceSheet2020());
            assertThat(result.get(0).getCashFlowStatement()).isEqualTo(DomainObjectMother.cashFlowStatement2020());
        } finally {
            sut.setClock(null);
        }
    }

    @Test
    @Disabled("FMP only returns last 5 quarters :-(")
    void givenLocatorThenDownloadFiscalQuarters() throws IOException {
        try {
            sut.setClock(TimeMachine.clockAt(LocalDate.ofYearDay(2020, 1)));
            setupFmpMock(FQ_INCOME_FILENAME, "/income-statement/MSFT", param("limit", "20"), param("period", "quarter"));
            setupFmpMock(FQ_BALANCE_FILENAME, "/balance-sheet-statement/MSFT", param("limit", "20"), param("period", "quarter"));
            setupFmpMock(FQ_CASHFLOW_FILENAME, "/cash-flow-statement/MSFT", param("limit", "20"), param("period", "quarter"));

            final var result = sut.getQuarters(DomainObjectMother.businessLocator(), YearQuarter.of(2016, 1), YearQuarter.of(2020, 4));

            assertThat(result).hasSize(20);
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

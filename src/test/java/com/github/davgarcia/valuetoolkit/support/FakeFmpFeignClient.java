package com.github.davgarcia.valuetoolkit.support;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.davgarcia.valuetoolkit.CompanyProfile;
import com.github.davgarcia.valuetoolkit.adapter.fmp.FmpFeignClient;
import com.github.davgarcia.valuetoolkit.adapter.fmp.dto.BalanceSheetDto;
import com.github.davgarcia.valuetoolkit.adapter.fmp.dto.CashFlowStatementDto;
import com.github.davgarcia.valuetoolkit.adapter.fmp.dto.IncomeStatementDto;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class FakeFmpFeignClient implements FmpFeignClient {

    private static final String INCOME_FILENAME = "/fmp/MSFT-income.json";
    private static final String BALANCE_FILENAME = "/fmp/MSFT-balance.json";
    private static final String CASHFLOW_FILENAME = "/fmp/MSFT-cashflow.json";

    private final ObjectMapper objectMapper;

    public FakeFmpFeignClient() {
        objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public CompanyProfile[] getCompanyProfile(final String id) {
        return null;
    }

    @Override
    public IncomeStatementDto[] getIncomeStatement(final String id, final int limit, final String period) {
        try {
            return objectMapper.readValue(loadResource(INCOME_FILENAME), IncomeStatementDto[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BalanceSheetDto[] getBalanceSheet(final String id, final int limit, final String period) {
        try {
            return objectMapper.readValue(loadResource(BALANCE_FILENAME), BalanceSheetDto[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CashFlowStatementDto[] getCashFlowStatement(final String id, final int limit, final String period) {
        try {
            return objectMapper.readValue(loadResource(CASHFLOW_FILENAME), CashFlowStatementDto[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] loadResource(final String resourcePath) throws IOException {
        return IOUtils.resourceToByteArray(resourcePath);
    }
}

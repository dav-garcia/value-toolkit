package com.github.davgarcia.valuetoolkit.adapter.fmp;

import com.github.davgarcia.valuetoolkit.adapter.fmp.dto.BalanceSheetDto;
import com.github.davgarcia.valuetoolkit.adapter.fmp.dto.CashFlowStatementDto;
import com.github.davgarcia.valuetoolkit.adapter.fmp.dto.IncomeStatementDto;
import com.github.davgarcia.valuetoolkit.BusinessProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "fmpFeignClient", url = "${provider.fmp.url}", configuration = FmpFeignClientConfig.class)
public interface FmpFeignClient {

    @GetMapping("/api/v3/profile/{id}")
    BusinessProfile[] getBusinessProfile(@PathVariable final String id);

    @GetMapping("/api/v3/income-statement/{id}")
    IncomeStatementDto[] getIncomeStatement(@PathVariable final String id, @RequestParam final int limit, @RequestParam final String period);

    @GetMapping("/api/v3/balance-sheet-statement/{id}")
    BalanceSheetDto[] getBalanceSheet(@PathVariable final String id, @RequestParam final int limit, @RequestParam final String period);

    @GetMapping("/api/v3/cash-flow-statement/{id}")
    CashFlowStatementDto[] getCashFlowStatement(@PathVariable final String id, @RequestParam final int limit, @RequestParam final String period);
}

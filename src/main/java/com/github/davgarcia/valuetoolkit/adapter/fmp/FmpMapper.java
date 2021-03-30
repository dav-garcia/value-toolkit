package com.github.davgarcia.valuetoolkit.adapter.fmp;

import com.github.davgarcia.valuetoolkit.adapter.fmp.dto.BalanceSheetDto;
import com.github.davgarcia.valuetoolkit.adapter.fmp.dto.CashFlowStatementDto;
import com.github.davgarcia.valuetoolkit.adapter.fmp.dto.IncomeStatementDto;
import com.github.davgarcia.valuetoolkit.BalanceSheet;
import com.github.davgarcia.valuetoolkit.CashFlowStatement;
import com.github.davgarcia.valuetoolkit.IncomeStatement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FmpMapper {

    FmpMapper INSTANCE = Mappers.getMapper(FmpMapper.class);

    IncomeStatement toIncomeStatement(final IncomeStatementDto dto);

    BalanceSheet toBalanceSheet(final BalanceSheetDto dto);

    CashFlowStatement toCashFlowStatement(final CashFlowStatementDto dto);
}

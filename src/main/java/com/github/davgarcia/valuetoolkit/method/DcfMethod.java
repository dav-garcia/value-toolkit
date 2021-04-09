package com.github.davgarcia.valuetoolkit.method;

import com.github.davgarcia.valuetoolkit.Business;
import com.github.davgarcia.valuetoolkit.FiscalPeriod;

public class DcfMethod extends Abstract2StageMethod {

    @Override
    protected String getLabel() {
        return "fcf";
    }

    @Override
    protected double getValue0(final Business business, final FiscalPeriod period) {
        return period.getCashFlowStatement().getFreeCashFlow();
    }

    @Override
    protected double getGrowthRate0(final Business business, final FiscalPeriod period) {
        return business.getEstimates().getCashFlowCagr(business.getIndicators().getPratGrowthRate()) / 100d;
    }
}

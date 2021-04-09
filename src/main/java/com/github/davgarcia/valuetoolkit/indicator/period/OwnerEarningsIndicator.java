package com.github.davgarcia.valuetoolkit.indicator.period;

import com.github.davgarcia.valuetoolkit.FiscalPeriod;
import com.github.davgarcia.valuetoolkit.FiscalPeriodIndicator;

public class OwnerEarningsIndicator implements FiscalPeriodIndicator {

    public static final OwnerEarningsIndicator INSTANCE = new OwnerEarningsIndicator();

    private OwnerEarningsIndicator() {
        // Empty.
    }

    @Override
    public double eval(final FiscalPeriod period) {
        final var netIncome = period.getIncomeStatement().getNetIncome();
        final var depreciationAmortization = period.getIncomeStatement().getDepreciationAndAmortization();
        final var otherNonCashItems = period.getCashFlowStatement().getOtherNonCashItems();
        final var maintenanceCapex = period.getCashFlowStatement().getInvestmentsInPropertyPlantAndEquipment();
        final var changeWorkingCapital = period.getCashFlowStatement().getChangeInWorkingCapital();

        return netIncome + depreciationAmortization + otherNonCashItems + maintenanceCapex + changeWorkingCapital;
    }
}

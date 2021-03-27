package com.github.davgarcia.valuetoolkit.indicator;

import com.github.davgarcia.valuetoolkit.BusinessIndicator;
import com.github.davgarcia.valuetoolkit.config.ValueToolkitConfigProperties;
import com.github.davgarcia.valuetoolkit.domain.Business;
import com.github.davgarcia.valuetoolkit.domain.Period;

public class OwnerEarningsIndicator implements BusinessIndicator {

    public static final OwnerEarningsIndicator INSTANCE = new OwnerEarningsIndicator();

    private OwnerEarningsIndicator() {
        // Empty.
    }

    @Override
    public double eval(final ValueToolkitConfigProperties params, final Business business) {
        final var period = business.getNewestPeriod(Period.Status.ACTUAL);

        final var netIncome = period.getIncomeStatement().getNetIncome();
        final var depreciationAmortization = period.getIncomeStatement().getDepreciationAndAmortization();
        final var otherNonCashItems = period.getCashFlowStatement().getOtherNonCashItems();
        final var maintenanceCapex = period.getCashFlowStatement().getInvestmentsInPropertyPlantAndEquipment();
        final var changeWorkingCapital = period.getCashFlowStatement().getChangeInWorkingCapital();

        return netIncome + depreciationAmortization + otherNonCashItems + maintenanceCapex + changeWorkingCapital;
    }
}

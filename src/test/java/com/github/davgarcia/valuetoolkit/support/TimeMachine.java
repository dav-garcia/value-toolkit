package com.github.davgarcia.valuetoolkit.support;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeMachine {

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    public static Clock clockAt(final LocalDate date) {
        return Clock.fixed(date.atStartOfDay(ZONE_ID).toInstant(), ZONE_ID);
    }

    public static Clock clockAt(final LocalDateTime dateTime) {
        return Clock.fixed(dateTime.atZone(ZONE_ID).toInstant(), ZONE_ID);
    }
}

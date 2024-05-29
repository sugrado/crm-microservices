package com.turkcell.crm.order_service.core.utilities.helpers;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateHelper {
    private DateHelper() {
    }

    public static short totalDaysBetween(LocalDateTime from, LocalDateTime to) {
        return (short) Math.abs(Duration.between(to, from).toDays());
    }
}

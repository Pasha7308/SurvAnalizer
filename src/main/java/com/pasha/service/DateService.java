package com.pasha.service;

import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Service;

import org.joda.time.DateTime;
import java.time.LocalDateTime;

@Service
public class DateService {
    public LocalDateTime jodaDateToLocalDate(DateTime dateTime) {
        DateTimeZone zone = DateTimeZone.forID("Asia/Krasnoyarsk");
        dateTime = dateTime.withZone(zone);
        return LocalDateTime.of(dateTime.getYear(),
                dateTime.getMonthOfYear(),
                dateTime.getDayOfMonth(),
                dateTime.getHourOfDay(),
                dateTime.getMinuteOfHour());
    }
}

package com.pasha.service;

import org.springframework.stereotype.Service;

import org.joda.time.DateTime;
import java.time.LocalDateTime;

@Service
public class DateService {
    public LocalDateTime jodaDateToLocalDate(DateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(),
                dateTime.getMonthOfYear(),
                dateTime.getDayOfMonth(),
                dateTime.getHourOfDay(),
                dateTime.getMinuteOfHour()).plusHours(7);
    }
}

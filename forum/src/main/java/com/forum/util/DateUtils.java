package com.forum.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private DateUtils() {
    }

    public static String parseDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.LOCAL_DATETIME_FORMAT);
        return dateTime.format(formatter);
    }

    public static String parseDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.LOCAL_DATE_FORMAT);
        return date.format(formatter);
    }
}
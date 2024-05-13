package com.advitos.machinedatacsvprinter.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Component
public class TimestampConverter {

    private static final long LAB_VIEW_UTC = -2082844800;
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    public Timestamp convertToSQLTimestamp(Long seconds, Long nano) {

        Instant instant = Instant.ofEpochSecond(seconds, nano);
        int nanos = instant.getNano();
        long unixUTC = LAB_VIEW_UTC + seconds;

        Instant i = Instant.ofEpochSecond(unixUTC);

        Timestamp utc = Timestamp.from(i);
        utc.setNanos(nanos);
        return utc;
    }

    public Timestamp convertToSQLTimestamp(Long seconds) {

        long unixUTC = LAB_VIEW_UTC + seconds;

        Instant i = Instant.ofEpochSecond(unixUTC);

        return Timestamp.from(i);
    }

    public Instant convertToUtcInstant(Long seconds) {
        long unixUTC = LAB_VIEW_UTC + seconds;
         return Instant.ofEpochSecond(unixUTC);
    }

    public String convertToLocalDateTime(Long seconds) {
        long unixUTC = LAB_VIEW_UTC + seconds;
        Instant instant = Instant.ofEpochSecond(unixUTC);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return dateTime.format(formatter);
    }

    public LocalDateTime convertToLocalDateTime(Long source, Long nano) {

        long target = LAB_VIEW_UTC + source;
        Instant instant = Instant.ofEpochSecond(source, nano);
        int nanoSeconds = instant.getNano();

        return LocalDateTime.ofEpochSecond(target, nanoSeconds, ZoneOffset.ofHours(1));
//        return LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(1));
    }

}
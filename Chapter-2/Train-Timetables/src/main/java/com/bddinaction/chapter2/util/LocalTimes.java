package com.bddinaction.chapter2.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class LocalTimes {
    public static List<LocalTime> localTimesFrom(String listOfDepartureTimes) {
        return Stream.of(listOfDepartureTimes.split(","))
                .map(String::trim)
                .filter(time -> !time.isEmpty())
                .map(LocalTime::parse)
                .collect(Collectors.toList());
    }

    public static LocalTime at(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
    }
}

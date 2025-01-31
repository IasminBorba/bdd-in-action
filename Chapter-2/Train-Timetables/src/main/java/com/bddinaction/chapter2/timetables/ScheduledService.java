package com.bddinaction.chapter2.timetables;

import java.time.LocalTime;
import java.util.*;

import static com.bddinaction.chapter2.util.LocalTimes.at;

public class ScheduledService {
    private final String departure;
    private final String destination;
    private final List<LocalTime> departureTimes;

    public static ScheduledService NO_SERVICE = new ScheduledService("","", List.of());

    public ScheduledService(String from, String to, List<LocalTime> at) {
        this.departure = from;
        this.destination = to;
        this.departureTimes = at;
    }

    public ScheduledService(String from, String to) {
        this.departure = from;
        this.destination = to;
        this.departureTimes = new ArrayList<>();
    }

    public List<LocalTime> getDepartureTimes() {
        return departureTimes;
    }

    public boolean goesBetween(String from, String to) {
        return departure.equals(from) && destination.equals(to);
    }

    public void addTime(String time) {
        departureTimes.add(at(time));
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }
}

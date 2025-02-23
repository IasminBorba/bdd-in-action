package com.bddinaction.chapter2.timetables;

import com.bddinaction.chapter2.model.Line;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTimeTable implements TimeTable, CanScheduleServices {
    private final Map<Line, ScheduledService> schedules = new HashMap<>();

    @Override
    public void scheduleService(Line line,
                                List<LocalTime> departingAt,
                                String from,
                                String to) {
        schedules.put(line, new ScheduledService(from, to, departingAt));
    }

    public void scheduleService(Line line, ScheduledService scheduled) {
        schedules.put(line, scheduled);
    }

    public Line getLineForScheduledService(ScheduledService scheduled) {
        return schedules.entrySet().stream()
                .filter(entry -> entry.getValue().equals(scheduled))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public Set<Line> lineNames() {
        return  schedules.keySet();
    }

    private boolean lineGoesThrough(Line line, String from, String to) {
        return schedules.getOrDefault(line, ScheduledService.NO_SERVICE)
                .goesBetween(from,to);
    }

    @Override
    public List<Line> findLinesThrough(String from, String to) {
        return lineNames().stream()
                .filter(line  -> lineGoesThrough(line, from,to))
                .collect(Collectors.toList());
    }

    @Override
    public List<LocalTime> getDepartures(Line line, String departingFrom) {
        if (!schedules.containsKey(line))
            throw new UnknownLineException("No line found: " + line.getLine());

        return schedules.get(line).getDepartureTimes();
    }
}


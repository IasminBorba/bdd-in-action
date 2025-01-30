package com.bddinaction.chapter2.itineraries;

import com.bddinaction.chapter2.model.Line;
import com.bddinaction.chapter2.timetables.TimeTable;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ItineraryService {
    private final TimeTable timeTable;

    public ItineraryService(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public List<LocalTime> findNextDepartures(String from, String to, LocalTime departureTime) {
        List<Line> lines = timeTable.findLinesThrough(from, to);

        return lines.stream()
                .flatMap(line -> timeTable.getDepartures(line,from)
                .stream())
                .filter(trainTime -> trainTime.isAfter(departureTime))
                .sorted()
                .limit(3)
                .collect(Collectors.toList());
    }
}

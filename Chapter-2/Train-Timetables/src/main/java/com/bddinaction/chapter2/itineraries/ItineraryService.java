package com.bddinaction.chapter2.itineraries;

import com.bddinaction.chapter2.model.Line;
import com.bddinaction.chapter2.timetables.*;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class ItineraryService {
    private final TimeTable timeTable;
    private static final Map<String, Integer> travelTimes = Map.of(
            "Parramatta-Town Hall-Western", 27,
            "Epping-Central-Northern", 45,
            "Epping-Central-Newcastle", 30,
            "Epping-Central-Epping", 38
    );

    public ItineraryService(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public List<LocalTime> findNextDepartures(String from, String to, LocalTime departureTime) {
        List<Line> lines = timeTable.findLinesThrough(from, to);
        return getArrivalTimesOnLines(lines, from, departureTime);
    }

    public List<LocalTime> getArrivalTimesOnLines(List<Line> lines, String station, LocalTime departureTime) {
        return lines.stream()
                .flatMap(line -> timeTable.getDepartures(line,station)
                .stream())
                .filter(trainTime ->
                        trainTime.isAfter(departureTime) &&
                        !trainTime.isAfter(departureTime.plusMinutes(30))
                )
                .sorted()
                .collect(Collectors.toList());
    }

    public LocalTime estimatedArrivalTime(ScheduledService scheduled) {
        Line line = ((InMemoryTimeTable) timeTable).getLineForScheduledService(scheduled);
        String key = scheduled.getDeparture() + "-" + scheduled.getDestination() + "-" + line.getLine();

        Integer travelTime = travelTimes.get(key);
        if (travelTime == null)
            throw new IllegalArgumentException("Não há tempo de viagem estimado para essa rota: " + key);

        return (scheduled.getDepartureTimes().get(0)).plusMinutes(travelTime);
    }
}

package com.bddinaction.chapter2.itineraries;

import com.bddinaction.chapter2.model.Line;
import com.bddinaction.chapter2.timetables.TimeTable;
import com.google.common.collect.Lists;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ItineraryService {
    private TimeTable timeTable;

    public ItineraryService(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public ItineraryService() {
    }

    public List<LocalTime> findNextDepartures(String from, String to, LocalTime departureTime) {
        List<String> lines = timeTable.findLinesThrough(from, to);

        return lines.stream()
                .flatMap(line -> timeTable.getDepartures(line, from)
                        .stream())
                .filter(trainTime -> !trainTime.isBefore(departureTime))
                .sorted()
                .limit(2)
                .collect(Collectors.toList());

//        List<Line> lines = timeTable.findLinesThrough(from, to);
//        List<LocalTime> allArrivalTimes = getArrivalTimesOnLines(lines, from);
//        List<LocalTime> candidateArrivalTimes = findArrivalTimesAfter(departureTime, allArrivalTimes);
//        return atMost(3, candidateArrivalTimes);
    }

    private List<LocalTime> atMost(int max, List<LocalTime> times) {
        return Lists.partition(times, max).get(0);
    }

    public LocalTime estimatedArrivalTime(String from, String to, TimeTable timeTable) {
        // Obtemos as linhas que passam por ambas as estações.
        List<String> lines = timeTable.findLinesThrough(from, to);

        // Encontramos o próximo horário de partida disponível.
        LocalTime now = LocalTime.now();
        LocalTime nextDeparture = lines.stream()
                .flatMap(line -> timeTable.getDepartures(line, from).stream())
                .filter(trainTime -> !trainTime.isBefore(now)) // Filtra os trens que ainda não partiram.
                .min(LocalTime::compareTo) // Escolhe o horário mais próximo.
                .orElseThrow(() -> new IllegalStateException("No available trains found"));

        int travelDuration = timeTable.getTravelDuration(from, to);

        return nextDeparture.plusMinutes(travelDuration);
    }

}

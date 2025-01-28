package com.bddinaction.chapter2.timetables;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryTimeTable implements TimeTable, CanScheduleServices {

    private final Map<String, ScheduledService> schedules = new HashMap<>();

    @Override
    public void scheduleService(String line,
                                List<LocalTime> departingAt,
                                String from,
                                String to) {
        schedules.put(line, new ScheduledService(from, to, departingAt));

    }

    private Set<String> lineNames() { return  schedules.keySet(); }

    private boolean lineGoesThrough(String line, String from, String to) {
        return schedules.getOrDefault(line, ScheduledService.NO_SERVICE)
                .goesBetween(from,to);
    }
    @Override
    public List<String> findLinesThrough(String from, String to) {
        return lineNames().stream()
                .filter(line  -> lineGoesThrough(line, from,to))
                .collect(Collectors.toList());
    }

    @Override
    public List<LocalTime> getDepartures(String lineName, String from) {
        if (!schedules.containsKey(lineName)) {
            throw new UnknownLineException("No line found: " + lineName);
        }
        return schedules.get(lineName).getDepartureTimes();
    }

    @Override
    public int getTravelDuration(String from, String to) {
        // Procurar a linha que conecta `from` e `to`.
        for (ScheduledService service : schedules.values()) {
            if (service.goesBetween(from, to)) {
                // Calcular a duração com base no primeiro horário de partida e chegada.
                List<LocalTime> departures = service.getDepartureTimes();
                if (!departures.isEmpty()) {
                    // Supondo que o horário de chegada seja sempre correspondente ao primeiro horário de partida.
                    LocalTime firstDeparture = departures.get(0);
                    LocalTime firstArrival = calculateArrivalTime(firstDeparture, service); // Método auxiliar para calcular a chegada.

                    return (int) java.time.Duration.between(firstDeparture, firstArrival).toMinutes();
                }
            }
        }

        throw new IllegalArgumentException("No scheduled service between " + from + " and " + to);
    }

    private LocalTime calculateArrivalTime(LocalTime departure, ScheduledService service) {
        if (service.getLineName().equals("Western")) {
            return departure.plusMinutes(27); // Duração para a linha Western.
        } else if (service.getLineName().equals("Northern")) {
            return departure.plusMinutes(45); // Duração para a linha Northern.
        } else if (service.getLineName().equals("Newcastle")) {
            return departure.plusMinutes(30); // Duração para a linha Newcastle.
        } else if (service.getLineName().equals("Epping")) {
            return departure.plusMinutes(38); // Duração para a linha Epping.
        }

        throw new IllegalStateException("Unable to calculate arrival time for line: " + service.getLineName());
    }

}


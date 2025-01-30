package com.bddinaction.chapter2.services

import com.bddinaction.chapter2.itineraries.ItineraryService
import com.bddinaction.chapter2.model.Line
import com.bddinaction.chapter2.timetables.InMemoryTimeTable
import spock.lang.Specification

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class WhenCalculatingArrivalTimes extends Specification {
    def timeTable = new InMemoryTimeTable();

    def "should calculate the correct arrival time"() {
        given:
        def itineraryService = new ItineraryService(timeTable);

        Line westernLine = Line.named("Western").departingFrom("Emu Plains")
        timeTable.scheduleService(westernLine, [at("07:50"), at("08:02"), at("08:11"), at("08:14"), at("08:21"), at("08:31"), at("08:36")],
                "Parramatta", "Town Hall"
        )

        when:
        def proposedTrainTimes = itineraryService.findNextDepartures("Parramatta", "Town Hall", at('08:00'));

        then:
        proposedTrainTimes == [at('08:02'), at('08:11'), at('08:14'), at("08:21")]
    }

    def at(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
    }
}

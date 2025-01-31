package com.bddinaction.chapter2.services

import spock.lang.Specification
import com.bddinaction.chapter2.model.Line
import com.bddinaction.chapter2.timetables.InMemoryTimeTable

class WhenFindingLinesThroughStation extends Specification {
    def timeTable = new InMemoryTimeTable();

    def "should find the correct lines between two stations"() {
        given:
        timeTable.scheduleService(Line.named(lineName).departingFrom(lineDeparture), List.of(), departure, destination)

        when:
        def lines = timeTable.findLinesThrough(departure, destination)

        then:
        def expectedLine = Line.named(lineName).departingFrom(lineDeparture)

        lines == [expectedLine]

        where:
        departure    | destination   | lineName   | lineDeparture
        "Parramatta" | "Town Hall"   | "Western"  | "Emu Plains"
        "Town Hall"  | "Parramatta"  | "Western"  | "North Richmond"
        "Strathfield"| "Epping"      | "Epping"   | "City"
    }
}

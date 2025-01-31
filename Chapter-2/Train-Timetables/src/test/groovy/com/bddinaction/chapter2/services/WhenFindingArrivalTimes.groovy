package com.bddinaction.chapter2.services

import spock.lang.Specification
import com.bddinaction.chapter2.itineraries.ItineraryService
import com.bddinaction.chapter2.model.Line
import com.bddinaction.chapter2.timetables.*

import static com.bddinaction.chapter2.util.LocalTimes.*;

class WhenFindingArrivalTimes extends Specification {
    def timeTable = new InMemoryTimeTable()

    def "should calculate the correct arrival time at a particular station"() {
        given:
        def itineraryService = new ItineraryService(timeTable)
        def scheduled = new ScheduledService("Epping", "Central");

        scheduled.addTime("08:13")
        timeTable.scheduleService(Line.named("Epping").departingFrom("City"), scheduled);

        when:
        def proposedArrivalTimes = itineraryService.estimatedArrivalTime(scheduled);

        then:
        at("08:51") == proposedArrivalTimes
    }
}

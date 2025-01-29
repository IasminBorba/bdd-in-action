import com.bddinaction.chapter2.itineraries.ItineraryService
import com.bddinaction.chapter2.timetables.InMemoryTimeTable
import spock.lang.Specification

import java.time.LocalTime

class WhenCalculatingArrivalTimes extends Specification {
    ItineraryService itineraryService;
    InMemoryTimeTable timeTable = new InMemoryTimeTable();

    def "should calculate the correct arrival time"() {
        given:
        itineraryService = new ItineraryService(timeTable);
        timeTable.scheduleService("Western Line", [at("7:50"), at("8:02"), at("8:11"), at("8:14")],
                "Parramatta", "Town Hall"
        )

        when:
        def proposedTrainTimes = itineraryService.findNextDepartures("Parramatta", "Town Hall", at('8:00'));

        then:
        proposedTrainTimes == [at('8:02'), at('8:11'), at('8:14')]
    }

    def at(String time) {
        def hour = Integer.valueOf(time.split(':')[0])
        def minute = Integer.valueOf(time.split(':')[1])
        return LocalTime.of(hour, minute)
    }
}

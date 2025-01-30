package com.bddinaction.chapter2.jbehave.steps;

import com.bddinaction.chapter2.itineraries.ItineraryService;
import com.bddinaction.chapter2.model.Line;
import com.bddinaction.chapter2.timetables.InMemoryTimeTable;
import org.assertj.core.api.Assertions;
import org.jbehave.core.annotations.*;

import java.time.LocalTime;
import java.util.List;

import static com.bddinaction.chapter2.util.LocalTimes.*;

public class OptimalItinerarySteps {
    InMemoryTimeTable timeTable = new InMemoryTimeTable();
    List<LocalTime> proposedTrainTimes;

    @Given("$line line trains from $lineStart leave $departure for $destination at $departureTimes")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination, String departureTimes) {
        timeTable.scheduleService(Line.named(line).departingFrom(lineStart), localTimesFrom(departureTimes), departure, destination);
    }

    @When("I want to travel from $departure to $destination at $startTime")
    public void whenIWantToTravel(String departure, String destination, String startTime) {
        ItineraryService itineraryService = new ItineraryService(timeTable);
        proposedTrainTimes = itineraryService.findNextDepartures(departure, destination, at(startTime));
    }

    @Then("I should be told about the trains at: $expectedTimes")
    public void shouldFindTheseTrains(String expectedTimes) {
        Assertions.assertThat(proposedTrainTimes).isEqualTo(localTimesFrom(expectedTimes));
    }
}

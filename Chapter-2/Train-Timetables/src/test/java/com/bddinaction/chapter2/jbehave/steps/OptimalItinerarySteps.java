package com.bddinaction.chapter2.jbehave.steps;

import com.bddinaction.chapter2.itineraries.ItineraryService;
import com.bddinaction.chapter2.timetables.InMemoryTimeTable;
import org.assertj.core.api.Assertions;
import org.jbehave.core.annotations.*;

import java.time.LocalTime;
import java.util.List;

import static com.bddinaction.chapter2.util.LocalTimes.*;

public class OptimalItinerarySteps {
    InMemoryTimeTable timeTable = new InMemoryTimeTable();
    ItineraryService itineraryService = new ItineraryService(timeTable);
    List<LocalTime> proposedTrainTimes;

    @Given("$line line trains from $lineStart leave $departure for $destination at $departureTimes")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination, String departureTimes) {
        itineraryService = new ItineraryService(timeTable);
        timeTable.scheduleService(line, localTimesFrom(departureTimes), departure, destination);
    }

    @When("I want to travel from $departure to $destination at $startTime")
    public void whenIWantToTravel(String departure, String destination, String startTime) {
        proposedTrainTimes = itineraryService.findNextDepartures(departure, destination, at(startTime));
    }

    @Then("I should be told about the trains at: $expectedTimes")
    public void shouldFindTheseTrains(String expectedTimes) {
        List<LocalTime> viableTrainTimes = localTimesFrom(expectedTimes);
        Assertions.assertThat(proposedTrainTimes).isEqualTo(viableTrainTimes);
    }
}

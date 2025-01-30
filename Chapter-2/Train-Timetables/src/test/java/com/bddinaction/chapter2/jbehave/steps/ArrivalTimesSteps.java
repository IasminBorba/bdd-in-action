package com.bddinaction.chapter2.jbehave.steps;

import com.bddinaction.chapter2.itineraries.ItineraryService;
import com.bddinaction.chapter2.model.Line;
import com.bddinaction.chapter2.timetables.*;
import org.assertj.core.api.Assertions;
import org.jbehave.core.annotations.*;

import java.time.LocalTime;

import static com.bddinaction.chapter2.util.LocalTimes.at;

public class ArrivalTimesSteps {
    InMemoryTimeTable timeTable = new InMemoryTimeTable();
    ItineraryService itineraryService = new ItineraryService(timeTable);
    ScheduledService scheduled;
    LocalTime trainTime;

    @Given("I want to go from $departure to $destination")
    public void initializeScheduledService(String departure, String destination) {
        scheduled = new ScheduledService(departure, destination);
    }

    @Given("the next train leaves at $departureTime on $line line")
    public void addTrainDepartureTime(String departureTime, String line) {
        scheduled.addTime(departureTime);
        timeTable.scheduleService(Line.named(line).departingFrom(""), scheduled);
    }

    @When("I ask for my arrival time")
    public void requestEstimatedArrivalTime() {
        trainTime = itineraryService.estimatedArrivalTime(scheduled);
    }

    @Then("the estimated arrival time should be $expectedTrainTime")
    public void verifyEstimatedArrivalTime(String expectedTrainTime) {
        Assertions.assertThat(trainTime).isEqualTo(at(expectedTrainTime));
    }
}
